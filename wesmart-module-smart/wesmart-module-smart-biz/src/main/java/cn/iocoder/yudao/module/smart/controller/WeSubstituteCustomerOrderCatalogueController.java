package cn.iocoder.yudao.module.smart.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.smart.service.IWeSubstituteCustomerOrderCatalogueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.common.exception.ServiceException;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.entity.WeSubstituteCustomerOrderCatalogue;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCatalogueAddRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCatalogueMoveRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCatalogueUpdateRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.vo.WeSubstituteCustomerOrderCatalogueVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 代客下单字段分类 前端控制器
 * </p>
 *
 * @author WangYX
 * @since 2023-08-02
 */
@Tag(name = "代客下单字段分类")
@RestController
@RequestMapping("/substitute/customer/order/catalogue")
public class WeSubstituteCustomerOrderCatalogueController extends BaseController {

    @Resource
    private IWeSubstituteCustomerOrderCatalogueService weSubstituteCustomerOrderCatalogueService;

    /**
     * 列表
     *
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/02 17:03
     */
    @Operation(summary = "列表")
    @GetMapping("")
    public AjaxResult list() {
        LambdaQueryWrapper<WeSubstituteCustomerOrderCatalogue> queryWrapper = Wrappers.lambdaQuery(WeSubstituteCustomerOrderCatalogue.class);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogue::getDelFlag, Constants.COMMON_STATE);
        queryWrapper.orderByAsc(WeSubstituteCustomerOrderCatalogue::getSort);
        List<WeSubstituteCustomerOrderCatalogue> list = weSubstituteCustomerOrderCatalogueService.list(queryWrapper);
        return AjaxResult.success(BeanUtil.copyToList(list, WeSubstituteCustomerOrderCatalogueVO.class));
    }

    /**
     * 新增
     *
     * @param request 新增请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/02 17:19
     */
    @Operation(summary = "新增")
    @PostMapping("")
    public AjaxResult add(@Validated @RequestBody WeSubstituteCustomerOrderCatalogueAddRequest request) {
        //检查分类名称是否重复
        LambdaQueryWrapper<WeSubstituteCustomerOrderCatalogue> queryWrapper = Wrappers.lambdaQuery(WeSubstituteCustomerOrderCatalogue.class);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogue::getDelFlag, Constants.COMMON_STATE);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogue::getName, request.getName());
        WeSubstituteCustomerOrderCatalogue one = weSubstituteCustomerOrderCatalogueService.getOne(queryWrapper);
        if (BeanUtil.isNotEmpty(one)) {
            throw new ServiceException("分类名称不能重复！");
        }
        //获取数量
        queryWrapper.clear();
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogue::getDelFlag, Constants.COMMON_STATE);
        long count = weSubstituteCustomerOrderCatalogueService.count(queryWrapper);

        //新增
        WeSubstituteCustomerOrderCatalogue catalogue = new WeSubstituteCustomerOrderCatalogue();
        catalogue.setId(IdUtil.getSnowflakeNextId());
        catalogue.setName(request.getName());
        catalogue.setSort((int)count);
        catalogue.setFixed(0);
        catalogue.setDelFlag(Constants.COMMON_STATE);
        weSubstituteCustomerOrderCatalogueService.save(catalogue);
        return AjaxResult.success(catalogue.getId());
    }

    /**
     * 修改
     *
     * @param request 修改请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/02 17:19
     */
    @Operation(summary = "修改")
    @PutMapping("")
    public AjaxResult update(@Validated @RequestBody WeSubstituteCustomerOrderCatalogueUpdateRequest request) {
        WeSubstituteCustomerOrderCatalogue weSubstituteCustomerOrderCatalogue = weSubstituteCustomerOrderCatalogueService.getById(request.getId());
        if (BeanUtil.isEmpty(weSubstituteCustomerOrderCatalogue)) {
            return AjaxResult.success();
        }
        //检查分类名称是否重复
        LambdaQueryWrapper<WeSubstituteCustomerOrderCatalogue> queryWrapper = Wrappers.lambdaQuery(WeSubstituteCustomerOrderCatalogue.class);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogue::getDelFlag, Constants.COMMON_STATE);
        queryWrapper.eq(WeSubstituteCustomerOrderCatalogue::getName, request.getName());
        queryWrapper.ne(WeSubstituteCustomerOrderCatalogue::getId, request.getId());
        WeSubstituteCustomerOrderCatalogue one = weSubstituteCustomerOrderCatalogueService.getOne(queryWrapper);
        if (BeanUtil.isNotEmpty(one)) {
            throw new ServiceException("分类名称不能重复！");
        }

        //修改
        LambdaUpdateWrapper<WeSubstituteCustomerOrderCatalogue> updateWrapper = Wrappers.lambdaUpdate(WeSubstituteCustomerOrderCatalogue.class);
        updateWrapper.eq(WeSubstituteCustomerOrderCatalogue::getId, request.getId());
        updateWrapper.set(StrUtil.isNotBlank(request.getName()), WeSubstituteCustomerOrderCatalogue::getName, request.getName());
        weSubstituteCustomerOrderCatalogueService.update(updateWrapper);
        return AjaxResult.success();
    }

    /**
     * 删除
     *
     * @param id 主键Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/02 17:20
     */
    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) {
        weSubstituteCustomerOrderCatalogueService.delete(id);
        return AjaxResult.success();
    }

    /**
     * 移动
     *
     * @param request 移动请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/02 18:14
     */
    @Operation(summary = "移动")
    @PutMapping("/move")
    public AjaxResult move(@Validated @RequestBody WeSubstituteCustomerOrderCatalogueMoveRequest request) {
        weSubstituteCustomerOrderCatalogueService.move(request);
        return AjaxResult.success();
    }


}

