package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.agentdev;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author leejoker
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeTransformExternalUserIdVO extends WeResultVo {
    /**
     * 该服务商第三方应用下的企业的外部联系人ID
     */
    private String externalUserid;
}
