package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.groupchat;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import lombok.*;

/**
 * 获取客户群进群方式配置参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeGroupChatJoinWayQuery extends WeBaseQuery {
    //联系方式的配置id
    private String config_id;
}
