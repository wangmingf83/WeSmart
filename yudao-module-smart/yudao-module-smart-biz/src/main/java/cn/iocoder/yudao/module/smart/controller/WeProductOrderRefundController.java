package cn.iocoder.yudao.module.smart.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.iocoder.yudao.module.smart.service.IWeProductOrderRefundService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.common.enums.ProductRefundOrderStateEnum;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeProductOrderRefund;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.refund.vo.WeProductOrderRefundVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品订单退款表 前端控制器
 * </p>
 *
 * @author WangYX
 * @since 2022-11-22
 */
@Tag(name = "商品订单退款")
@RestController
@RequestMapping("/product/refund")
public class WeProductOrderRefundController extends BaseController {

    @Resource
    private IWeProductOrderRefundService weProductOrderRefundService;

    @Operation(summary = "查询退款订单交易状态", method = "GET")
    @Log(title = "退款订单交易状态", businessType = BusinessType.SELECT)
    @GetMapping("/refund/status/{orderNo}")
    public AjaxResult<List<WeProductOrderRefundVo>> refundStatus(@PathVariable("orderNo") String orderNo) {
        LambdaQueryWrapper<WeProductOrderRefund> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WeProductOrderRefund::getOrderNo, orderNo);
        queryWrapper.eq(WeProductOrderRefund::getDelFlag, 0);
        List<WeProductOrderRefund> list = weProductOrderRefundService.list(queryWrapper);
        List<WeProductOrderRefundVo> weProductOrderRefundVos = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (WeProductOrderRefund one : list) {
                WeProductOrderRefundVo weProductOrderRefundVo = BeanUtil.copyProperties(one, WeProductOrderRefundVo.class);
                BigDecimal bigDecimal = new BigDecimal(weProductOrderRefundVo.getRefundFee()).divide(BigDecimal.valueOf(100L)).setScale(2, BigDecimal.ROUND_HALF_UP);
                weProductOrderRefundVo.setRefundFee(bigDecimal.toString());
                weProductOrderRefundVo.setRefundStateStr(ProductRefundOrderStateEnum.of(weProductOrderRefundVo.getRefundState()).getMsg());
                weProductOrderRefundVos.add(weProductOrderRefundVo);
            }
        }
        return AjaxResult.success(weProductOrderRefundVos);
    }
}

