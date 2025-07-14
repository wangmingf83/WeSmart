package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnionidToExternalUserIdQuery extends WeBaseQuery {

    /**
     * 微信用户的openid
     */
    private String openid;

    /**
     * 微信用户的unionid
     */
    private String unionid;


    public UnionidToExternalUserIdQuery(String corpId, String openid, String unionid) {
        super.corpid = corpId;
        this.openid = openid;
        this.unionid = unionid;
    }
}
