package cn.iocoder.yudao.module.smart.controller;


import cn.iocoder.yudao.module.smart.service.IWeSubstituteCustomerOrderService;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderAddRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderUpdateRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.vo.WeSubstituteCustomerOrderVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 代客下单-订单 前端控制器
 * </p>
 *
 * @author WangYX
 * @since 2023-08-03
 */
@RestController
@RequestMapping("/weSubstitute/customer/order")
public class WeSubstituteCustomerOrderController extends BaseController {

    @Resource
    private IWeSubstituteCustomerOrderService weSubstituteCustomerOrderService;

    /**
     * 列表-分页
     *
     * @param request 列表请求参数
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/08/03 17:37
     */
    @Operation(summary = "列表-分页")
    @GetMapping("")
    public TableDataInfo page(WeSubstituteCustomerOrderRequest request) {
        startPage();
        List<WeSubstituteCustomerOrderVO> list = weSubstituteCustomerOrderService.selectList(request);
        return getDataTable(list);
    }

    /**
     * 新增
     *
     * @param request 新增请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/03 17:54
     */
    @Operation(summary = "新增")
    @PostMapping("")
    public AjaxResult add(@RequestBody WeSubstituteCustomerOrderAddRequest request) {
        return AjaxResult.success(weSubstituteCustomerOrderService.add(request));
    }

    /**
     * 修改
     *
     * @param request 修改请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/08 15:49
     */
    @Operation(summary = "修改")
    @PutMapping("")
    public AjaxResult update(@Validated @RequestBody WeSubstituteCustomerOrderUpdateRequest request) {
        weSubstituteCustomerOrderService.update(request);
        return AjaxResult.success();
    }

    /**
     * 详情
     *
     * @param id 主键Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/08/07 17:02
     */
    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Long id) {
        return AjaxResult.success(weSubstituteCustomerOrderService.get(id));
    }


}

