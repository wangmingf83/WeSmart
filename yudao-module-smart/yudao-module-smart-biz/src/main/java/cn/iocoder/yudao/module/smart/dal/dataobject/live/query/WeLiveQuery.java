package cn.iocoder.yudao.module.smart.dal.dataobject.live.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.wx.WxBaseQuery;
import lombok.Data;

@Data
public class WeLiveQuery extends WxBaseQuery {

    String livingId;
    String openid;
}
