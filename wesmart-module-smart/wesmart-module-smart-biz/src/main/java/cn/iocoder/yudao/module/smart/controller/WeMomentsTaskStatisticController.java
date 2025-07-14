package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import cn.iocoder.yudao.module.smart.common.ScrmSysDeptClient;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.service.*;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.constant.HttpStatus;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsInteracte;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsTask;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsStatisticCustomerRecordRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsStatisticInteractRecordRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsStatisticUserRecordRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.system.dept.query.SysDeptQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.system.dept.vo.SysDeptVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.system.user.query.SysUserQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.system.user.vo.SysUserVo;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 朋友圈统计
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/14 16:36
 */
@Tag(name = "朋友圈统计")
@RestController
@RequestMapping("/moments/statistic")
public class WeMomentsTaskStatisticController extends BaseController {

    @Resource
    private IWeMomentsUserService weMomentsUserService;
    @Resource
    private IWeMomentsCustomerService weMomentsCustomerService;
    @Resource
    private IWeMomentsInteracteService weMomentsInteracteService;
    @Resource
    private ScrmSysDeptClient scrmSysDeptClient;
    @Resource
    private ScrmSysUserClient scrmSysUserClient;
    @Resource
    private IWeMomentsTaskStatisticService weMomentsTaskStatisticService;
    @Resource
    private IWeCustomerService weCustomerService;
    @Resource
    private IWeMomentsTaskService weMomentsTaskService;
    @Resource
    private IWeMomentsEstimateUserService weMomentsEstimateUserService;
    @Resource
    private IWeMomentsEstimateCustomerService weMomentsEstimateCustomerService;


    //=============================员工start=============================//

    /**
     * 员工统计
     *
     * @param weMomentsTaskId 朋友圈任务Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/14 17:37
     */
    @Operation(summary = "员工统计")
    @GetMapping("/user/{weMomentsTaskId}")
    public AjaxResult userStatistic(@PathVariable("weMomentsTaskId") Long weMomentsTaskId) {
        return AjaxResult.success(weMomentsTaskStatisticService.userStatistic(weMomentsTaskId));
    }

    /**
     * 员工记录
     *
     * @param request 用户记录统计请求参数
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/06/14 18:11
     */
    @Operation(summary = "员工记录")
    @GetMapping("/user/record")
    public TableDataInfo userRecord(@Validated WeMomentsStatisticUserRecordRequest request) {
        WeMomentsTask weMomentsTask = weMomentsTaskService.getById(request.getWeMomentsTaskId());
        if (BeanUtil.isEmpty(weMomentsTask)) {
            return getDataTable(CollectionUtil.newArrayList());
        }
        startPage();
        if (weMomentsTask.getSendType().equals(2)) {
            //成员群发
            List<WeMomentsEstimateUserVO> executeUsers = weMomentsEstimateUserService.getExecuteUsers(request);
            TableDataInfo dataTable = getDataTable(executeUsers);
            List<WeMomentsUser> list = BeanUtil.copyToList(executeUsers, WeMomentsUser.class);
            //转换
            List<WeMomentsUserVO> vos = BeanUtil.copyToList(list, WeMomentsUserVO.class);
            //获取执行员工的部门数据
            getDeptData(vos);
            dataTable.setRows(vos);
            return dataTable;

        } else {
            LambdaQueryWrapper<WeMomentsUser> queryWrapper = Wrappers.lambdaQuery(WeMomentsUser.class);
            queryWrapper.eq(WeMomentsUser::getMomentsTaskId, request.getWeMomentsTaskId());
            if (StrUtil.isNotBlank(request.getWeUserIds())) {
                queryWrapper.in(WeMomentsUser::getWeUserId, request.getWeUserIds().split(","));
            }
            if (StrUtil.isNotBlank(request.getDeptIds())) {
                queryWrapper.in(WeMomentsUser::getDeptId, request.getDeptIds().split(","));
            }
            queryWrapper.eq(WeMomentsUser::getDelFlag, Constants.COMMON_STATE);
            queryWrapper.eq(request.getStatus() != null, WeMomentsUser::getExecuteStatus, request.getStatus());
            List<WeMomentsUser> list = weMomentsUserService.list(queryWrapper);
            //返回结果
            TableDataInfo dataTable = getDataTable(list);
            //转换
            List<WeMomentsUserVO> vos = BeanUtil.copyToList(list, WeMomentsUserVO.class);
            //获取执行员工的部门数据
            getDeptData(vos);
            dataTable.setRows(vos);
            return dataTable;
        }
    }


    /**
     * 员工记录导出
     *
     * @param request 员工记录导出请求参数，
     * @author WangYX
     * @date 2023/06/14 19:00
     */
    @Operation(summary = "员工记录导出")
    @GetMapping("/user/export")
    public void userExport(@Validated WeMomentsStatisticUserRecordRequest request) {
        WeMomentsTask weMomentsTask = weMomentsTaskService.getById(request.getWeMomentsTaskId());
        if (BeanUtil.isEmpty(weMomentsTask)) {
            return;
        }
        List<WeMomentsUser> list = getWeMomentsUsers(request);
        List<WeMomentsUserVO> vos = BeanUtil.copyToList(list, WeMomentsUserVO.class);
        //获取执行员工的部门数据
        getDeptData(vos);

        HttpServletResponse response = ServletUtils.getResponse();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            String fileName = URLEncoder.encode(DateUtil.today() + weMomentsTask.getName() + "员工统计", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), WeMomentsUserVO.class).sheet().doWrite(vos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取执行员工列表
     *
     * @param request 请求参数
     * @return {@link List<WeMomentsUser>}
     * @author WangYX
     * @date 2023/06/20 11:07
     */
    private List<WeMomentsUser> getWeMomentsUsers(@Validated WeMomentsStatisticUserRecordRequest request) {
        WeMomentsTask weMomentsTask = weMomentsTaskService.getById(request.getWeMomentsTaskId());
        if (BeanUtil.isEmpty(weMomentsTask)) {
            return CollectionUtil.newArrayList();
        }
        if (weMomentsTask.getSendType().equals(2)) {
            //成员群发
            List<WeMomentsEstimateUserVO> executeUsers = weMomentsEstimateUserService.getExecuteUsers(request);
            return BeanUtil.copyToList(executeUsers, WeMomentsUser.class);
        } else {
            LambdaQueryWrapper<WeMomentsUser> queryWrapper = Wrappers.lambdaQuery(WeMomentsUser.class);
            queryWrapper.eq(WeMomentsUser::getMomentsTaskId, request.getWeMomentsTaskId());
            if (StrUtil.isNotBlank(request.getWeUserIds())) {
                queryWrapper.in(WeMomentsUser::getWeUserId, request.getWeUserIds().split(","));
            }
            if (StrUtil.isNotBlank(request.getDeptIds())) {
                queryWrapper.in(WeMomentsUser::getDeptId, request.getDeptIds().split(","));
            }
            queryWrapper.eq(WeMomentsUser::getDelFlag, Constants.COMMON_STATE);
            queryWrapper.eq(request.getStatus() != null, WeMomentsUser::getExecuteStatus, request.getStatus());
            return weMomentsUserService.list(queryWrapper);
        }
    }

    /**
     * 获取执行员工的部门数据
     *
     * @param vos 执行员工列表
     * @author WangYX
     * @date 2023/06/20 11:09
     */
    private void getDeptData(List<WeMomentsUserVO> vos) {
        if (CollectionUtil.isNotEmpty(vos)) {
            //获取部门数据
            List<Long> deptIds = vos.stream().filter(i -> i.getDeptId() != null).map(i -> i.getDeptId().longValue()).distinct().collect(Collectors.toList());

            if (CollectionUtil.isEmpty(deptIds)) {
                return;
            }

            SysDeptQuery query = new SysDeptQuery();
            query.setDeptIds(deptIds);
            List<DeptRespDTO> deptResult = scrmSysDeptClient.getListByDeptIds(deptIds);
            if (deptResult!=null) {
//                List<SysDeptVo> data = deptResult.getData();
//                Map<Long, String> map = data.stream().collect(Collectors.toMap(SysDeptVo::getDeptId, SysDeptVo::getDeptName));
//                vos.forEach(i -> i.setDeptName(map.get(i.getDeptId())));
                Map<Long, String> map = deptResult.stream().collect(Collectors.toMap(DeptRespDTO::getId, DeptRespDTO::getName));
                vos.forEach(i -> i.setDeptName(map.get(i.getDeptId())));
            }
        }
    }


    //=============================员工end=============================//


    //=============================客户start=============================//

    /**
     * 客户统计
     *
     * @param weMomentsTaskId 朋友圈任务Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/20 13:52
     */
    @Operation(summary = "客户统计")
    @GetMapping("/customer/{weMomentsTaskId}")
    public AjaxResult customerStatistic(@PathVariable("weMomentsTaskId") Long weMomentsTaskId) {
        return AjaxResult.success(weMomentsTaskStatisticService.customerStatistic(weMomentsTaskId));
    }

    /**
     * 客户记录列表
     *
     * @param request 朋友圈统计-客户记录列表
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/06/20 15:07
     */
    @Operation(summary = "客户记录列表")
    @GetMapping("/customer/record")
    public TableDataInfo customerRecord(@Validated WeMomentsStatisticCustomerRecordRequest request) {
        //朋友圈任务
        WeMomentsTask weMomentsTask = weMomentsTaskService.getById(request.getWeMomentsTaskId());
        if (BeanUtil.isEmpty(weMomentsTask)) {
            return new TableDataInfo();
        }

        if (weMomentsTask.getSendType().equals(2)) {
            startPage();
            List<WeMomentsEstimateCustomerVO> estimateCustomer = weMomentsEstimateCustomerService.getEstimateCustomer(request);
            TableDataInfo dataTable = getDataTable(estimateCustomer);
            List<WeMomentsCustomerVO> vos = BeanUtil.copyToList(estimateCustomer, WeMomentsCustomerVO.class);
            dataTable.setRows(vos);
            return dataTable;
        } else {
            startPage();
            LambdaQueryWrapper<WeMomentsCustomer> queryWrapper = Wrappers.lambdaQuery(WeMomentsCustomer.class);
            queryWrapper.eq(WeMomentsCustomer::getMomentsTaskId, request.getWeMomentsTaskId());
            if (StrUtil.isNotBlank(request.getWeUserIds())) {
                queryWrapper.in(WeMomentsCustomer::getWeUserId, request.getWeUserIds().split(","));
            }
            queryWrapper.eq(request.getDeliveryStatus() != null, WeMomentsCustomer::getDeliveryStatus, request.getDeliveryStatus());
            queryWrapper.eq(WeMomentsCustomer::getDelFlag, Constants.COMMON_STATE);
            List<WeMomentsCustomer> list = weMomentsCustomerService.list(queryWrapper);
            TableDataInfo dataTable = getDataTable(list);

            if (CollectionUtil.isNotEmpty(list)) {
                List<String> externalUserIds = list.stream().map(WeMomentsCustomer::getExternalUserid).collect(Collectors.toList());
                LambdaQueryWrapper<WeCustomer> wrapper = Wrappers.lambdaQuery(WeCustomer.class);
                wrapper.select(WeCustomer::getExternalUserid, WeCustomer::getCustomerName);
                wrapper.in(WeCustomer::getExternalUserid, externalUserIds);
                List<WeCustomer> weCustomers = weCustomerService.list(wrapper);
                Map<String, String> map = weCustomers.stream().collect(Collectors.toMap(WeCustomer::getExternalUserid, WeCustomer::getCustomerName, (k1, k2) -> k2));
                list.forEach(i -> i.setCustomerName(map.get(i.getExternalUserid())));
            }

            List<WeMomentsCustomerVO> vos = BeanUtil.copyToList(list, WeMomentsCustomerVO.class);
            dataTable.setRows(vos);
            return dataTable;
        }
    }

    /**
     * 客户记录导出
     *
     * @param request 朋友圈统计-客户记录列表
     * @author WangYX
     * @date 2023/06/20 15:33
     */
    @Operation(summary = "客户记录导出")
    @GetMapping("/customer/export")
    public void customerExport(@Validated WeMomentsStatisticCustomerRecordRequest request) {
        WeMomentsTask weMomentsTask = weMomentsTaskService.getById(request.getWeMomentsTaskId());
        if (BeanUtil.isEmpty(weMomentsTask)) {
            return;
        }
        List<WeMomentsCustomerVO> vos = BeanUtil.copyToList(getMomentsCustomers(request), WeMomentsCustomerVO.class);
        HttpServletResponse response = ServletUtils.getResponse();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            String fileName = URLEncoder.encode(DateUtil.today() + weMomentsTask.getName() + "客户统计", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            ExcelWriter build = EasyExcel.write(response.getOutputStream(), WeMomentsCustomerVO.class).build();

            List<List<WeMomentsCustomerVO>> split = CollectionUtil.split(vos, 10000);
            for (int i = 0; i < split.size(); i++) {
                //多sheet，1w条数据一个sheet
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "朋友圈客户统计" + i + 1).build();
                List<WeMomentsCustomerVO> data = split.get(i);
                build.write(data, writeSheet);
            }

            if (split.size() == 0) {
                //多sheet，1w条数据一个sheet
                WriteSheet writeSheet = EasyExcel.writerSheet("朋友圈客户统计").build();
                List<WeMomentsCustomerVO> data = new ArrayList<>();
                build.write(data, writeSheet);
            }
            build.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取客户记录
     *
     * @param request 朋友圈统计-客户记录列表
     * @return {@link List<WeMomentsCustomer>}
     * @author WangYX
     * @date 2023/06/20 15:34
     */
    private List<WeMomentsCustomer> getMomentsCustomers(WeMomentsStatisticCustomerRecordRequest request) {
        //朋友圈任务
        WeMomentsTask weMomentsTask = weMomentsTaskService.getById(request.getWeMomentsTaskId());
        if (BeanUtil.isEmpty(weMomentsTask)) {
            return CollectionUtil.newArrayList();
        }

        if (weMomentsTask.getSendType().equals(2)) {
            List<WeMomentsEstimateCustomerVO> estimateCustomer = weMomentsEstimateCustomerService.getEstimateCustomer(request);
            return BeanUtil.copyToList(estimateCustomer, WeMomentsCustomer.class);
        } else {
            LambdaQueryWrapper<WeMomentsCustomer> queryWrapper = Wrappers.lambdaQuery(WeMomentsCustomer.class);
            queryWrapper.eq(WeMomentsCustomer::getMomentsTaskId, request.getWeMomentsTaskId());
            if (StrUtil.isNotBlank(request.getWeUserIds())) {
                queryWrapper.in(WeMomentsCustomer::getWeUserId, request.getWeUserIds().split(","));
            }
            queryWrapper.eq(request.getDeliveryStatus() != null, WeMomentsCustomer::getDeliveryStatus, request.getDeliveryStatus());
            queryWrapper.eq(WeMomentsCustomer::getDelFlag, Constants.COMMON_STATE);
            List<WeMomentsCustomer> list = weMomentsCustomerService.list(queryWrapper);

            if (CollectionUtil.isNotEmpty(list)) {
                List<String> externalUserIds = list.stream().map(WeMomentsCustomer::getExternalUserid).collect(Collectors.toList());
                LambdaQueryWrapper<WeCustomer> wrapper = Wrappers.lambdaQuery(WeCustomer.class);
                wrapper.select(WeCustomer::getExternalUserid, WeCustomer::getCustomerName);
                wrapper.in(WeCustomer::getExternalUserid, externalUserIds);
                List<WeCustomer> weCustomers = weCustomerService.list(wrapper);
                Map<String, String> map = weCustomers.stream().collect(Collectors.toMap(WeCustomer::getExternalUserid, WeCustomer::getCustomerName, (k1, k2) -> k2));
                list.forEach(i -> i.setCustomerName(map.get(i.getExternalUserid())));
            }
            return list;
        }
    }


    //=============================客户end=============================//


    //=============================互动start=============================//

    /**
     * 互动数据统计
     *
     * @param weMomentsTaskId 朋友圈任务Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/20 16:08
     */
    @Operation(summary = "互动数据统计")
    @GetMapping("/interact/{weMomentsTaskId}")
    public AjaxResult interactStatistic(@PathVariable("weMomentsTaskId") Long weMomentsTaskId) {
        return AjaxResult.success(weMomentsTaskStatisticService.interactStatistic(weMomentsTaskId));
    }

    /**
     * 互动数据记录
     *
     * @param request 互动数据记录列表参数
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/06/20 16:29
     */
    @Operation(summary = "互动数据记录")
    @GetMapping("/interact/record")
    public TableDataInfo interactRecord(@Validated WeMomentsStatisticInteractRecordRequest request) {
        startPage();
        List<WeMomentsInteracte> list = getMomentsInteracts(request);
        List<WeMomentsInteractVO> rows = getRows(list);
        TableDataInfo dataTable = getDataTable(list);
        dataTable.setRows(rows);
        return dataTable;
    }

    /**
     * 互动数据导出
     *
     * @param request 互动数据导出参数
     * @author WangYX
     * @date 2023/06/20 17:08
     */
    @Operation(summary = "互动数据导出")
    @GetMapping("/interact/export")
    public void interactExport(@Validated WeMomentsStatisticInteractRecordRequest request) {
        WeMomentsTask weMomentsTask = weMomentsTaskService.getById(request.getWeMomentsTaskId());
        if (BeanUtil.isEmpty(weMomentsTask)) {
            return;
        }
        List<WeMomentsInteracte> list = getMomentsInteracts(request);
        List<WeMomentsInteractVO> vos = getRows(list);

        HttpServletResponse response = ServletUtils.getResponse();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        try {
            String fileName = URLEncoder.encode(DateUtil.today() + weMomentsTask.getName() + "互动统计", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            ExcelWriter build = EasyExcel.write(response.getOutputStream(), WeMomentsInteractVO.class).build();

            List<List<WeMomentsInteractVO>> split = CollectionUtil.split(vos, 10000);
            for (int i = 0; i < split.size(); i++) {
                //多sheet，1w条数据一个sheet
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "互动统计" + i + 1).build();
                List<WeMomentsInteractVO> data = split.get(i);
                build.write(data, writeSheet);
            }

            if (split.size() == 0) {
                //多sheet，1w条数据一个sheet
                WriteSheet writeSheet = EasyExcel.writerSheet("互动统计").build();
                List<WeMomentsInteractVO> data = new ArrayList<>();
                build.write(data, writeSheet);
            }

            build.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取列表行数据
     *
     * @param list 互动数据
     * @return {@link List<WeMomentsInteractVO>}
     * @author WangYX
     * @date 2023/06/20 17:07
     */
    private List<WeMomentsInteractVO> getRows(List<WeMomentsInteracte> list) {
        List<WeMomentsInteractVO> rows = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(list)) {
            List<String> weUserIds = list.stream().map(WeMomentsInteracte::getWeUserId).distinct().collect(Collectors.toList());
            SysUserQuery sysUserQuery = new SysUserQuery();
            sysUserQuery.setWeUserIds(weUserIds);

            List<AdminUserAllDTO> userList = scrmSysUserClient.getUserListByWeUserIds(weUserIds);

            Map<String, String> userMap = userList.stream().collect(Collectors.toMap(AdminUserAllDTO::getWeUserId, AdminUserAllDTO::getUsername, (k1, k2) -> k2));


            List<String> externalUserIds = list.stream().map(WeMomentsInteracte::getInteracteUserId).distinct().collect(Collectors.toList());
            LambdaQueryWrapper<WeCustomer> wrapper = Wrappers.lambdaQuery(WeCustomer.class);
            wrapper.in(WeCustomer::getExternalUserid, externalUserIds);
            wrapper.eq(WeCustomer::getDelFlag, Constants.COMMON_STATE);
            List<WeCustomer> weCustomers = weCustomerService.list(wrapper);
            Map<String, String> customerMap = weCustomers.stream().collect(Collectors.toMap(WeCustomer::getExternalUserid, WeCustomer::getCustomerName, (k1, k2) -> k2));

            for (WeMomentsInteracte weMomentsInteracte : list) {
                WeMomentsInteractVO weMomentsInteractVO = new WeMomentsInteractVO();
                weMomentsInteractVO.setCustomerName(customerMap.get(weMomentsInteracte.getInteracteUserId()));
                weMomentsInteractVO.setUserName(userMap.get(weMomentsInteracte.getWeUserId()));
                weMomentsInteractVO.setType(weMomentsInteracte.getInteracteType());
                weMomentsInteractVO.setInteractTime(DateUtil.date(weMomentsInteracte.getInteracteTime()).toLocalDateTime());
                rows.add(weMomentsInteractVO);
            }
        }
        return rows;
    }

    /**
     * 获取互动数据列表
     *
     * @param request 互动数据列表请求参数
     * @return {@link List<WeMomentsInteracte>}
     * @author WangYX
     * @date 2023/06/20 17:05
     */
    private List<WeMomentsInteracte> getMomentsInteracts(WeMomentsStatisticInteractRecordRequest request) {
        LambdaQueryWrapper<WeMomentsInteracte> queryWrapper = Wrappers.lambdaQuery(WeMomentsInteracte.class);
        queryWrapper.eq(WeMomentsInteracte::getMomentsTaskId, request.getWeMomentsTaskId());
        queryWrapper.eq(request.getInteractType() != null, WeMomentsInteracte::getInteracteType, request.getInteractType());
        if (StrUtil.isNotBlank(request.getWeUserIds())) {
            queryWrapper.in(WeMomentsInteracte::getWeUserId, request.getWeUserIds().split(","));
        }
        queryWrapper.between(request.getBeginTime() != null && request.getEndTime() != null, WeMomentsInteracte::getInteracteTime, request.getBeginTime(), request.getEndTime());
        queryWrapper.eq(WeMomentsInteracte::getInteracteUserType, 1);
        queryWrapper.eq(WeMomentsInteracte::getDelFlag, 0);
        return weMomentsInteracteService.list(queryWrapper);
    }


    //=============================互动end=============================//


}
