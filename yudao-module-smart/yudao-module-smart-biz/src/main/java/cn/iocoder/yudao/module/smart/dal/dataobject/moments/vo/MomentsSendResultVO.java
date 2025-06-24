package cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;

import java.util.List;

/**
 * 获取客户朋友圈发表后的可见客户列表
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/12 10:28
 */
@Data
public class MomentsSendResultVO extends WeResultVo {

    /**
     * 成员发送成功客户列表
     */
    private List<ExternalUserid> customer_list;

    @Data
    public static class ExternalUserid {
        /**
         * 成员发送成功的外部联系人userid
         */
        private String external_userid;
    }
}
