package cn.iocoder.yudao.module.smart.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.smart.service.IWeSubstituteCustomerOrderCataloguePropertyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.substitute.customer.order.SubstituteCustomerOrderCataloguePropertyTypeEnum;
import cn.iocoder.yudao.module.common.exception.ServiceException;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.entity.WeSubstituteCustomerOrderCatalogueProperty;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyAddRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyMoveRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyUpdateRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.vo.WeSubstituteCustomerOrderCataloguePropertyVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 代客下单分类字段 前端控制器
 * </p>
 *
 * @author WangYX
 * @since 2023-08-03
 */
@RestController
@RequestMapping("/substitute/customer/order/property")
public class WeSubstituteCustomerOrderCataloguePropertyController extends BaseController {

    @Resource
    private IWeSubstituteCustomerOrderCataloguePropertyService weSubstituteCustomerOrderCataloguePropertyService;

    /**
     * 列表
     *
     * @param
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/08/03 9:58
     */
    @Operation(summary = "列表")
    @GetMapping("")
    public TableDataInfo list(@Validated WeSubstituteCustomerOrderCataloguePropertyRequest request) {
        startPage();
        LambdaQueryWrapper<WeSubstituteCustomerOrderCatalogueProperty> queryWrapper = Wrappers.lambdaQuery(WeSubstituteCustomerOrderCatalogueProperty.class);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getDelFlag, Constants.COMMON_STATE);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getCatalogueId, request.getCatalogueId());
        queryWrapper.orderByAsc(WeSubstituteCustomerOrderCatalogueProperty::getSort);
        List<WeSubstituteCustomerOrderCatalogueProperty> list = weSubstituteCustomerOrderCataloguePropertyService.list(queryWrapper);
        TableDataInfo dataTable = getDataTable(list);
        List<WeSubstituteCustomerOrderCataloguePropertyVO> vos = BeanUtil.copyToList(list, WeSubstituteCustomerOrderCataloguePropertyVO.class);
        vos.forEach(i -> i.setTypeStr(SubstituteCustomerOrderCataloguePropertyTypeEnum.byCode(i.getType())));
        dataTable.setRows(vos);
        return dataTable;
    }

    /**
     * 新增
     *
     * @param request 新增请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 10:50
     */
    @Operation(summary = "新增")
    @PostMapping("")
    public AjaxResult add(@Validated @RequestBody WeSubstituteCustomerOrderCataloguePropertyAddRequest request) {
        LambdaQueryWrapper<WeSubstituteCustomerOrderCatalogueProperty> queryWrapper = Wrappers.lambdaQuery(WeSubstituteCustomerOrderCatalogueProperty.class);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getDelFlag, Constants.COMMON_STATE);
        int count = (int)weSubstituteCustomerOrderCataloguePropertyService.count(queryWrapper);

        WeSubstituteCustomerOrderCatalogueProperty property = BeanUtil.copyProperties(request, WeSubstituteCustomerOrderCatalogueProperty.class);
        property.setId(IdUtil.getSnowflakeNextId());
        property.setCode(String.valueOf(IdUtil.getSnowflakeNextId()));
        property.setSort(count);
        property.setFixed(0);
        property.setDelFlag(Constants.COMMON_STATE);
        weSubstituteCustomerOrderCataloguePropertyService.save(property);
        return AjaxResult.success(property.getId());
    }


    /**
     * 修改
     *
     * @param request 修改请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 10:50
     */
    @Operation(summary = "修改")
    @PutMapping("")
    public AjaxResult update(@Validated @RequestBody WeSubstituteCustomerOrderCataloguePropertyUpdateRequest request) {
        WeSubstituteCustomerOrderCatalogueProperty property = weSubstituteCustomerOrderCataloguePropertyService.getById(request.getId());
        if (BeanUtil.isEmpty(property)) {
            return AjaxResult.success();
        }
        LambdaUpdateWrapper<WeSubstituteCustomerOrderCatalogueProperty> updateWrapper = Wrappers.lambdaUpdate(WeSubstituteCustomerOrderCatalogueProperty.class);
        updateWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getId, request.getId());
        updateWrapper.set(request.getCatalogueId() != null, WeSubstituteCustomerOrderCatalogueProperty::getCatalogueId, request.getCatalogueId());
        if (property.getFixed().equals(0)) {
            updateWrapper.set(StrUtil.isNotBlank(request.getName()), WeSubstituteCustomerOrderCatalogueProperty::getName, request.getName());
        }
        updateWrapper.set(request.getRequired() != null, WeSubstituteCustomerOrderCatalogueProperty::getRequired, request.getRequired());
        updateWrapper.set(StrUtil.isNotBlank(request.getExpound()), WeSubstituteCustomerOrderCatalogueProperty::getExpound, request.getExpound());
        updateWrapper.set(StrUtil.isNotBlank(request.getValue()), WeSubstituteCustomerOrderCatalogueProperty::getValue, request.getValue());
        updateWrapper.set(request.getMoney() != null, WeSubstituteCustomerOrderCatalogueProperty::getMoney, request.getMoney());
        updateWrapper.set(request.getToTime() != null, WeSubstituteCustomerOrderCatalogueProperty::getToTime, request.getToTime());
        updateWrapper.set(request.getMultipleChoice() != null, WeSubstituteCustomerOrderCatalogueProperty::getMultipleChoice, request.getMultipleChoice());
        updateWrapper.set(request.getMore() != null, WeSubstituteCustomerOrderCatalogueProperty::getMore, request.getMore());
        weSubstituteCustomerOrderCataloguePropertyService.update(updateWrapper);
        return AjaxResult.success();
    }

    /**
     * 删除
     *
     * @param id 主键Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 11:14
     */
    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        WeSubstituteCustomerOrderCatalogueProperty one = weSubstituteCustomerOrderCataloguePropertyService.getById(id);
        if (BeanUtil.isEmpty(one)) {
            return AjaxResult.success();
        }
        if (one.getFixed().equals(1)) {
            throw new ServiceException("固定字段无法删除！");
        }
        LambdaUpdateWrapper<WeSubstituteCustomerOrderCatalogueProperty> updateWrapper = Wrappers.lambdaUpdate(WeSubstituteCustomerOrderCatalogueProperty.class);
        updateWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getId, id);
        updateWrapper.set(WeSubstituteCustomerOrderCatalogueProperty::getDelFlag, Constants.DELETE_STATE);
        weSubstituteCustomerOrderCataloguePropertyService.update(updateWrapper);
        return AjaxResult.success();
    }

    /**
     * 批量删除
     *
     * @param
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/14 14:33
     */
    @Operation(summary = "批量删除")
    @DeleteMapping("")
    public AjaxResult deleteAll(String ids) {
        String[] idArr = ids.split(",");
        List<WeSubstituteCustomerOrderCatalogueProperty> properties = weSubstituteCustomerOrderCataloguePropertyService.listByIds(Arrays.asList(idArr));
        //过滤掉固定字段，固定字段不可删除
        List<Long> list = properties.stream().filter(i -> i.getFixed().equals(0)).map(WeSubstituteCustomerOrderCatalogueProperty::getId).collect(Collectors.toList());
        //删除
        LambdaUpdateWrapper<WeSubstituteCustomerOrderCatalogueProperty> updateWrapper = Wrappers.lambdaUpdate(WeSubstituteCustomerOrderCatalogueProperty.class);
        updateWrapper.in(WeSubstituteCustomerOrderCatalogueProperty::getId, list);
        updateWrapper.set(WeSubstituteCustomerOrderCatalogueProperty::getDelFlag, Constants.DELETE_STATE);
        weSubstituteCustomerOrderCataloguePropertyService.update(updateWrapper);
        return AjaxResult.success();
    }

    /**
     * 移动
     *
     * @param
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 11:17
     */
    @Operation(summary = "移动")
    @PutMapping("/move")
    public AjaxResult move(@RequestBody WeSubstituteCustomerOrderCataloguePropertyMoveRequest request) {
        weSubstituteCustomerOrderCataloguePropertyService.move(request);
        return AjaxResult.success();
    }

    /**
     * 字段类型列表
     *
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 15:35
     */
    @Operation(summary = "字段类型列表")
    @GetMapping("/type")
    public AjaxResult typeList() {
        SubstituteCustomerOrderCataloguePropertyTypeEnum[] values = SubstituteCustomerOrderCataloguePropertyTypeEnum.values();
        Map map = new HashMap(values.length);
        Arrays.stream(values).forEach(i -> map.put(i.getCode(), i.getType()));
        return AjaxResult.success(map);
    }

    /**
     * 详情
     *
     * @param id 主键Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 16:36
     */
    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Long id) {
        WeSubstituteCustomerOrderCatalogueProperty one = weSubstituteCustomerOrderCataloguePropertyService.getById(id);
        WeSubstituteCustomerOrderCataloguePropertyVO vo = BeanUtil.copyProperties(one, WeSubstituteCustomerOrderCataloguePropertyVO.class);
        vo.setTypeStr(SubstituteCustomerOrderCataloguePropertyTypeEnum.byCode(vo.getType()));
        return AjaxResult.success(vo);
    }

    /**
     * 属性
     *
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 16:59
     */
    @Operation(summary = "属性")
    @GetMapping("/properties")
    public AjaxResult properties() {
        return AjaxResult.success(weSubstituteCustomerOrderCataloguePropertyService.properties());
    }

    /**
     * 获取订单状态 下拉框值
     *
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/07 17:13
     */
    @Operation(summary = "订单状态")
    @GetMapping("/order/status")
    public AjaxResult getOrderStatus() {
        LambdaQueryWrapper<WeSubstituteCustomerOrderCatalogueProperty> queryWrapper = Wrappers.lambdaQuery(WeSubstituteCustomerOrderCatalogueProperty.class);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getDelFlag, Constants.COMMON_STATE);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogueProperty::getCode, "orderStatus");
        WeSubstituteCustomerOrderCatalogueProperty one = weSubstituteCustomerOrderCataloguePropertyService.getOne(queryWrapper);
        if (BeanUtil.isEmpty(one)) {
            logger.error("订单状态不存在！，检查数据库数据是否初始化，或者订单状态的code是否被修改，固定值为orderStatus");
            throw new ServiceException("订单状态不存在！！！");
        }
        String value = one.getValue();
        if (StrUtil.isBlank(value)) {
            logger.error("订单状态值不存在！，检查订单状态值是否存在！");
            throw new ServiceException("订单状态值不存在！！！");
        }
        return AjaxResult.success(value.split(","));
    }

}

