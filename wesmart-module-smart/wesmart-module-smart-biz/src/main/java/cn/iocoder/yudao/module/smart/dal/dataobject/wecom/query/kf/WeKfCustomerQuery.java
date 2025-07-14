package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @Description 客户数据入参
 * @date 2021/12/13 14:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeKfCustomerQuery extends WeBaseQuery {

    /**
     * external_userid列表 可填充个数：1 ~ 100。超过100个需分批调用
     */
    private List<String> external_userid_list;
}
