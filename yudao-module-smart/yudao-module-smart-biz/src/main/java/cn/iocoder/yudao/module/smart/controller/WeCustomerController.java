package cn.iocoder.yudao.module.smart.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.core.page.TableSupport;
import cn.iocoder.yudao.module.smart.service.IWeCustomerService;
import cn.iocoder.yudao.module.smart.service.IWeSynchRecordService;
import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;

import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.common.enums.CustomerAddWay;
import cn.iocoder.yudao.module.common.exception.CustomException;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.poi.LwExcelUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.WeBacthMakeCustomerTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.WeMakeCustomerTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeCustomersQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeOnTheJobCustomerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerDetailInfoVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomersVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 企业微信客户Controller
 *
 * @author ruoyi
 * @date 2020-09-13
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class WeCustomerController extends BaseController {


    @Autowired
    private IWeCustomerService weCustomerService;

    @Autowired
    private IWeSynchRecordService iWeSynchRecordService;


    /**
     * 查询企业微信客户列表(分页),索引优化客户列表，未使用默认分页
     */
    @GetMapping("/findWeCustomerList")
    public TableDataInfo findWeCustomerList(WeCustomersQuery weCustomersQuery) {
        weCustomersQuery.setDelFlag(Constants.COMMON_STATE);
        List<WeCustomersVo> list = weCustomerService.findWeCustomerList(weCustomersQuery, TableSupport.buildPageRequest());
        TableDataInfo dataTable = getDataTable(list);
        //设置总条数
        dataTable.setTotal(
                weCustomerService.countWeCustomerList(weCustomersQuery)
        );
        dataTable.setLastSyncTime(
                iWeSynchRecordService.findUpdateLatestTime(SynchRecordConstants.SYNCH_CUSTOMER)
        );//最近同步时间
        dataTable.setNoRepeatCustomerTotal(
                weCustomerService.noRepeatCountCustomer(weCustomersQuery)
        );//去重客户数

        return dataTable;
    }


    /**
     * 客户数据导出
     *
     * @param query 请求参数
     * @author WangYX
     * @date 2023/08/23 14:00
     */
    @Operation(summary = "客户数据导出")
    @GetMapping("/export")
    public void export(WeCustomersQuery query) {
        query.setDelFlag(Constants.COMMON_STATE);
        List<WeCustomersVo> weCustomersVos = weCustomerService.findWeCustomerList(query, null);

        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(), WeCustomersVo.class,weCustomersVos,"客户信息表_" + System.currentTimeMillis()
        );

    }


    /**
     * 移动应用客户列表(分页)
     *
     * @param weCustomersQuery
     * @return
     */
    @GetMapping("/findWeCustomerListByApp")
    public TableDataInfo<List<WeCustomersVo>> findWeCustomerListByApp(WeCustomersQuery weCustomersQuery) {

        return weCustomerService.findWeCustomerListByApp(
                weCustomersQuery, TableSupport.buildPageRequest()
        );
    }


    /**
     * 查询企业微信客户列表(不分页)
     */
    @GetMapping("/findAllWeCustomerList")
    public AjaxResult findAllWeCustomerList(WeCustomersQuery weCustomersQuery) {
        weCustomersQuery.setDelFlag(Constants.COMMON_STATE);
        return AjaxResult.success(
                weCustomerService.findWeCustomerList(weCustomersQuery, null)
        );
    }


    /**
     * 客户同步接口
     *
     * @return
     */
    @Log(title = "企业微信客户同步接口", businessType = BusinessType.DELETE)
    @GetMapping("/synchWeCustomer")
    public AjaxResult synchWeCustomer() {
        try {
            weCustomerService.synchWeCustomer();
        } catch (CustomException e) {
            log.error("客户同步失败：ex:{}", e.getMessage(), e);
            return AjaxResult.error(e.getMessage());
        }


        return AjaxResult.success(WeConstans.SYNCH_TIP);

    }


    /**
     * 客户打标签
     *
     * @param weMakeCustomerTag
     * @return
     */
    @Log(title = "客户打标签", businessType = BusinessType.UPDATE)
    @PostMapping("/makeLabel")
    public AjaxResult makeLabel(@RequestBody WeMakeCustomerTag weMakeCustomerTag) {
        weMakeCustomerTag.setRecord(false);
        weCustomerService.makeLabel(weMakeCustomerTag);

        return AjaxResult.success();
    }


    /**
     * 在职继承
     *
     * @param weOnTheJobCustomerQuery
     * @return
     */
    @Log(title = "在职继承", businessType = BusinessType.UPDATE)
    @PostMapping("/jobExtends")
    public AjaxResult jobExtends(@RequestBody WeOnTheJobCustomerQuery weOnTheJobCustomerQuery) {

        try {
            weCustomerService.allocateOnTheJobCustomer(
                    weOnTheJobCustomerQuery
            );
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }


        return AjaxResult.success();
    }


    /**********************************************************************************
     **************************************客户详情*************************************
     *********************************************************************************/


    /**
     * 客户详情基础(基础信息+社交关系)
     *
     * @param externalUserid
     * @param userId
     * @param
     * @return
     */
    @GetMapping("/findWeCustomerBaseInfo")
    public AjaxResult<WeCustomerDetailInfoVo> findWeCustomerBaseInfo(String externalUserid, String userId, @RequestParam(defaultValue = "0") Integer delFlag) {

        return AjaxResult.success(
                weCustomerService.findWeCustomerDetail(externalUserid, userId, delFlag)
        );
    }


    /**
     * 客户画像汇总
     *
     * @param externalUserid
     * @return
     */
    @GetMapping("/findWeCustomerInfoSummary")
    public AjaxResult<WeCustomerDetailInfoVo> findWeCustomerInfoSummary(String externalUserid, @RequestParam(defaultValue = "0") Integer delFlag) {


        return AjaxResult.success(
                weCustomerService.findWeCustomerInfoSummary(externalUserid, null, delFlag)
        );
    }


    /**
     * 单个跟进人客户
     *
     * @param externalUserid
     * @param weUserId
     * @return
     */
    @GetMapping("/findWeCustomerInfoByUserId")
    public AjaxResult findWeCustomerInfoByUserId(String externalUserid, String weUserId, @RequestParam(defaultValue = "0") Integer delFlag) {


        return AjaxResult.success(
                weCustomerService.findWeCustomerInfoByUserId(externalUserid, weUserId, delFlag)
        );
    }


    /**
     * 获取客户添加类型
     *
     * @return
     */
    @GetMapping("/findCustomerAddWay")
    public AjaxResult findCustomerAddWay() {

        return AjaxResult.success(
                CustomerAddWay.getType()
        );
    }



    @Operation(summary = "通过条件校验客户数据", method = "POST")
    @PostMapping("/checkByCondition")
    public AjaxResult checkByCondition(@RequestBody WeCustomersQuery query) {
        log.info("checkByCondition 接口入参：query:{}", JSONObject.toJSONString(query));
        List<WeCustomer> customerList = weCustomerService.getCustomerListByCondition(query);
        Set<String> externalUserIdSet = Optional.ofNullable(customerList).orElseGet(ArrayList::new)
                .stream().map(WeCustomer::getExternalUserid).collect(Collectors.toSet());
        Set<String> userNum = Optional.ofNullable(customerList).orElseGet(ArrayList::new)
                .stream().map(WeCustomer::getAddUserId).collect(Collectors.toSet());
        JSONObject result = new JSONObject();
        result.put("customerNum", externalUserIdSet.size());
        result.put("userNum", userNum.size());
        return AjaxResult.success(result);
    }


    /**
     * 批量添加或删除客户标签
     *
     * @param makeCustomerTags
     * @return
     */
    @Log(title = "批量添加或删除客户标签", businessType = BusinessType.UPDATE)
    @PostMapping("/batchMakeLabel")
    public AjaxResult batchMakeLabel(@RequestBody WeBacthMakeCustomerTag makeCustomerTags) {

        weCustomerService.batchMakeLabel(makeCustomerTags);

        return AjaxResult.success();
    }


    /**
     * 客户加入或移除黑名单
     * @return
     */
    @PostMapping("/joinOrRemoveBlackList")
    public AjaxResult joinOrRemoveBlackList(@RequestBody WeCustomersQuery query){
        String customerIds = query.getCustomerIds();
        if(StringUtils.isNotEmpty(customerIds)){

            List<WeCustomer> weCustomers = weCustomerService.listByIds(
                    ListUtil.toList(customerIds.split(","))
            );
            if(CollectionUtil.isNotEmpty(weCustomers)){
                weCustomers.stream().forEach(k->{
                    k.setIsJoinBlacklist(query.getIsJoinBlacklist());
                });

                weCustomerService.updateBatchById(weCustomers);
            }

        }

        return AjaxResult.success();
    }


}
