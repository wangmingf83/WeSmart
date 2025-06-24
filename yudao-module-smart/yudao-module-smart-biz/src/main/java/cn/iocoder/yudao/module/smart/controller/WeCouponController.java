package cn.iocoder.yudao.module.smart.controller;


import cn.iocoder.yudao.module.smart.service.IWeCouponService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wx.coupon.WxCouponListQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付卡券
 *
 * @author danmo
 * @date 2023年03月11日 16:57
 */

@Tag(name = "微信支付卡券")
@Slf4j
@RestController
@RequestMapping("/coupon")
public class WeCouponController extends BaseController {

    @Autowired
    private IWeCouponService weCouponService;


    @Operation(summary = "条件查询卡券列表", method = "GET")
    @GetMapping("/getCouponList")
    public TableDataInfo getCouponList(WxCouponListQuery query) {
        String couponList = weCouponService.getCouponList(query);
        JSONObject jsonObject = JSONObject.parseObject(couponList);
        JSONArray list = jsonObject.getJSONArray("data");
        TableDataInfo dataTable = getDataTable(list);
        dataTable.setTotal(jsonObject.getLong("total_count"));
        return dataTable;
    }

}
