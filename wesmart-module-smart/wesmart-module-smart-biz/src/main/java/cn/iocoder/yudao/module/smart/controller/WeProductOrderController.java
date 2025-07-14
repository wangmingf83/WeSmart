package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.service.IWeProductOrderService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.common.enums.ProductOrderStateEnum;
import cn.iocoder.yudao.module.common.enums.ProductRefundOrderStateEnum;
import cn.iocoder.yudao.module.common.exception.ServiceException;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeProductOrder;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.order.query.WeProductOrderQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.order.vo.WeProductOrderPayInfoVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.order.vo.WeProductOrderVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.order.vo.WeProductOrderWareVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.refund.vo.WeProductOrderRefundVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 商品订单
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/21 18:22
 */
@RestController
@RequestMapping(value = "product/order")
@Tag(name = "商品订单")
public class WeProductOrderController extends BaseController {

    @Resource
    private IWeProductOrderService weProductOrderService;

    @Operation(summary = "商品订单列表", method = "GET")
    @Log(title = "商品订单列表", businessType = BusinessType.SELECT)
    @GetMapping("/page/list")
    public TableDataInfo<WeProductOrderVo> list(WeProductOrderQuery query) {
        startPage();
        List<WeProductOrderWareVo> list = weProductOrderService.list(query);
        List<WeProductOrderVo> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (WeProductOrderWareVo weProductOrderWareVo : list) {
                WeProductOrderVo weProductOrderVo = BeanUtil.copyProperties(weProductOrderWareVo, WeProductOrderVo.class);
                //订单状态
                weProductOrderVo.setOrderStateStr(ProductOrderStateEnum.of(weProductOrderVo.getOrderState()).getMsg());
                //订单金额
                BigDecimal totalFee = new BigDecimal(weProductOrderVo.getTotalFee()).divide(BigDecimal.valueOf(100L)).setScale(2, BigDecimal.ROUND_HALF_UP);
                weProductOrderVo.setTotalFee(totalFee.toString());
                List<WeProductOrderRefundVo> refunds = weProductOrderWareVo.getRefunds();
                if (refunds != null && refunds.size() > 0) {
                    refunds.sort(Comparator.comparing(WeProductOrderRefundVo::getRefundTime).reversed());
                    WeProductOrderRefundVo weProductOrderRefundVo = refunds.get(0);
                    //退款订单金额
                    BigDecimal refundFee = new BigDecimal(weProductOrderRefundVo.getRefundFee()).divide(BigDecimal.valueOf(100L)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    weProductOrderVo.setRefundFee(refundFee.toString());
                    //退款订单状态
                    weProductOrderVo.setRefundStateStr(ProductRefundOrderStateEnum.of(weProductOrderRefundVo.getRefundState()).getMsg());
                }
                //客户类型
                if(weProductOrderVo.getExternalType()!=null){
                    weProductOrderVo.setExternalTypeStr(weProductOrderVo.getExternalType() == 1 ? "微信" : "企业微信");
                }else{
                    weProductOrderVo.setExternalTypeStr("未知");
                }

                result.add(weProductOrderVo);
            }
        }
        return getDataTable(result);
    }

    @Operation(summary = "查询订单交易状态", method = "GET")
    @Log(title = "订单交易状态", businessType = BusinessType.SELECT)
    @GetMapping("/trading/status/{orderNo}")
    public AjaxResult<WeProductOrderPayInfoVo> orderTradingStatus(@PathVariable("orderNo") String orderNo) {
        LambdaQueryWrapper<WeProductOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(WeProductOrder::getPayTime, WeProductOrder::getOrderNo, WeProductOrder::getMchNo);
        queryWrapper.eq(WeProductOrder::getOrderNo, orderNo);
        queryWrapper.eq(WeProductOrder::getDelFlag, 0);
        WeProductOrder weProductOrder = weProductOrderService.getOne(queryWrapper);
        WeProductOrderPayInfoVo weProductOrderPayInfoVo = null;
        if (ObjectUtil.isNotEmpty(weProductOrder)) {
            weProductOrderPayInfoVo = BeanUtil.copyProperties(weProductOrder, WeProductOrderPayInfoVo.class);
        }
        return AjaxResult.success(weProductOrderPayInfoVo);
    }

    /**
     * 导出Excel，仅导出查询范围内的数据，最多支持导出一年的数据
     *
     * @param query
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2022/11/22 10:16
     */
    @Operation(summary = "导出Excel", method = "GET")
    @Log(title = "导出Excel", businessType = BusinessType.SELECT)
    @GetMapping("/export")
    public void export(WeProductOrderQuery query) throws IOException {
        if (StringUtils.isNotBlank(query.getBeginTime()) && StringUtils.isNotBlank(query.getEndTime())) {
            //判断时间间隔
            long between = DateUtil.between(DateUtil.parse(query.getBeginTime()), DateUtil.parse(query.getEndTime()), DateUnit.DAY);
            long yearOfDays = 365L;
            if (between > yearOfDays) {
                throw new ServiceException("最多支持导出一年的数据");
            }
        } else {
            //默认导出一个月的数据
            DateTime dateTime = DateUtil.offsetMonth(new Date(), -1);
            query.setBeginTime(DateUtil.format(dateTime, "yyyy-MM-dd"));
            query.setEndTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        }
        List<WeProductOrderWareVo> list = weProductOrderService.list(query);
        List<WeProductOrderVo> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (WeProductOrderWareVo weProductOrderWareVo : list) {
                WeProductOrderVo weProductOrderVo = BeanUtil.copyProperties(weProductOrderWareVo, WeProductOrderVo.class);
                //订单状态
                weProductOrderVo.setOrderStateStr(ProductOrderStateEnum.of(weProductOrderVo.getOrderState()).getMsg());
                //订单金额
                BigDecimal totalFee = new BigDecimal(weProductOrderVo.getTotalFee()).divide(BigDecimal.valueOf(100L)).setScale(2, BigDecimal.ROUND_HALF_UP);
                weProductOrderVo.setTotalFee(totalFee.toString());
                List<WeProductOrderRefundVo> refunds = weProductOrderWareVo.getRefunds();
                if (refunds != null && refunds.size() > 0) {
                    refunds.sort(Comparator.comparing(WeProductOrderRefundVo::getRefundTime).reversed());
                    WeProductOrderRefundVo weProductOrderRefundVo = refunds.get(0);
                    //退款订单金额
                    BigDecimal refundFee = new BigDecimal(weProductOrderRefundVo.getRefundFee()).divide(BigDecimal.valueOf(100L)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    weProductOrderVo.setRefundFee(refundFee.toString());
                    //退款订单状态
                    weProductOrderVo.setRefundStateStr(ProductRefundOrderStateEnum.of(weProductOrderRefundVo.getRefundState()).getMsg());
                }
                //客户类型
                weProductOrderVo.setExternalTypeStr(weProductOrderVo.getExternalType() == 1 ? "微信" : "企业微信");
                result.add(weProductOrderVo);
            }
        }
        HttpServletResponse response = ServletUtils.getResponse();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("商品订单", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), WeProductOrderVo.class).sheet("商品订单").doWrite(result);
    }

    @Operation(summary = "同步订单", method = "GET")
    @Log(title = "同步订单", businessType = BusinessType.SELECT)
    @GetMapping("/orderSync")
    public AjaxResult orderSync() {
        weProductOrderService.orderSync();
        return AjaxResult.success();
    }

}
