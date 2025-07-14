package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.iocoder.yudao.module.smart.service.IWeProductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.protobuf.ServiceException;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeProduct;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.query.WeAddProductQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.query.WeProductLineChartQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.query.WeProductQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.vo.WeProductListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.vo.WeProductStatisticsVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.vo.WeProductVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.vo.WeUserOrderTop5Vo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author danmo
 * @description 商品图册管理
 * @date 2022/9/12 18:22
 **/

@RestController
@RequestMapping(value = "product")
@Tag(name = "商品图册管理")
public class WeProductController extends BaseController {

    @Autowired
    private IWeProductService weProductService;

    @Operation(summary = "新增商品", method = "POST")
    @Log(title = "新增商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addProduct(@RequestBody @Validated WeAddProductQuery query) throws ServiceException {
        weProductService.addProduct(query);
        return AjaxResult.success();
    }

    @Operation(summary = "修改商品", method = "PUT")
    @Log(title = "修改商品", businessType = BusinessType.UPDATE)
    @PutMapping("/update/{id}")
    public AjaxResult updateProduct(@PathVariable("id") Long id, @RequestBody @Validated WeAddProductQuery query) throws ServiceException {
        weProductService.updateProduct(id, query);
        return AjaxResult.success();
    }

    @Operation(summary = "删除商品", method = "DELETE")
    @Log(title = "删除商品", businessType = BusinessType.UPDATE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult delProduct(@PathVariable("ids") List<Long> ids) {
        weProductService.delProduct(ids);
        return AjaxResult.success();
    }

    @Operation(summary = "商品详情", method = "GET")
    @Log(title = "商品详情", businessType = BusinessType.SELECT)
    @GetMapping("/get/{id}")
    public AjaxResult<WeProductVo> getProduct(@PathVariable("id") Long id) {
        WeProductVo product = weProductService.getProduct(id);
        return AjaxResult.success(product);
    }

    @Operation(summary = "商品列表", method = "GET")
    @Log(title = "商品列表", businessType = BusinessType.SELECT)
    @GetMapping("/page/list")
    public TableDataInfo<WeProductListVo> productPageList(WeProductQuery query) {
        startPage();
        List<WeProductListVo> list = weProductService.productList(query);
        list.forEach(o -> {
            o.setPrice(new BigDecimal(o.getPrice()).divide(BigDecimal.valueOf(100L)).toString());
            if (!o.getOrderTotalAmount().equals("0.0")) {
                BigDecimal bigDecimal = new BigDecimal(o.getOrderTotalAmount());
                bigDecimal = bigDecimal.divide(BigDecimal.valueOf(100L)).setScale(2, BigDecimal.ROUND_HALF_UP);
                o.setOrderTotalAmount(bigDecimal.toString());
            }
        });
        return getDataTable(list);
    }

    @Operation(summary = "商品列表同步", method = "GET")
    @Log(title = "商品列表同步", businessType = BusinessType.OTHER)
    @GetMapping("/list/sync")
    public AjaxResult syncProductList() {
        weProductService.syncProductList();
        return AjaxResult.success();
    }


    @Operation(summary = "商品详情统计", method = "GET")
    @Log(title = "商品详情统计", businessType = BusinessType.SELECT)
    @GetMapping("/statistics/{id}")
    public AjaxResult statistics(@PathVariable("id") Long id) {
        WeProductStatisticsVo statistics = weProductService.statistics(id);
        return AjaxResult.success(statistics);
    }


    @Operation(summary = "商品详情折线图", method = "POST")
    @Log(title = "商品详情折线图", businessType = BusinessType.SELECT)
    @PostMapping("/lineChart")
    public AjaxResult lineChart(@RequestBody WeProductLineChartQuery query) {
        Map<String, Object> map = weProductService.lineChart(query);
        return AjaxResult.success(map);
    }

    @Operation(summary = "员工订单Top5", method = "GET")
    @Log(title = "员工订单Top5", businessType = BusinessType.SELECT)
    @GetMapping("/top5/{id}")
    public AjaxResult<List<WeUserOrderTop5Vo>> top5(@PathVariable("id") Long id) {
        List<WeUserOrderTop5Vo> weUserOrderTop5Vos = weProductService.userOrderTop5(id);
        return AjaxResult.success(weUserOrderTop5Vos);
    }

    /**
     * 商品列表（移动端列表-分页）
     *
     * @param
     * @return {@link AjaxResult< WeProductListVo>}
     * @author WangYX
     * @date 2023/08/08 10:54
     */
    @Operation(summary = "商品列表（移动端列表-分页）")
    @GetMapping("/mobile/list")
    public TableDataInfo<WeProductVo> list() {
        startPage();
        LambdaQueryWrapper<WeProduct> queryWrapper = Wrappers.lambdaQuery(WeProduct.class);
        queryWrapper.eq(WeProduct::getDelFlag, Constants.COMMON_STATE);
        List<WeProduct> list = weProductService.list(queryWrapper);
        TableDataInfo dataTable = getDataTable(list);
        List<WeProductVo> weProductVos = BeanUtil.copyToList(list, WeProductVo.class);

        weProductVos.forEach(o -> {
            o.setPrice(new BigDecimal(o.getPrice()).divide(BigDecimal.valueOf(100L)).toString());
        });

        dataTable.setRows(weProductVos);
        return dataTable;
    }


}
