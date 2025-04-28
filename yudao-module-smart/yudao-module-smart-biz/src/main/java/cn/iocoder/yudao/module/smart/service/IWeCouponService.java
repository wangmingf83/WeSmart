package cn.iocoder.yudao.module.smart.service;

import com.alibaba.fastjson.JSONObject;

import cn.iocoder.yudao.module.smart.dal.dataobject.wx.coupon.WxCouponListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wx.coupon.WxSendCouponQuery;

/**
 * 卡券业务接口
 *
 * @author danmo
 * @since 2023-03-07 14:59:35
 */
public interface IWeCouponService {

    /**
     * 查询卡券批次列表
     * @param query
     * @return
     */
    String getCouponList(WxCouponListQuery query);

    /**
     * 发放卡券
     */
    JSONObject sendCoupon(WxSendCouponQuery query);
}
