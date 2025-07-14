package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.agentdev;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author leejoker
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeTransformUserIdVO extends WeResultVo {
    /**
     * 该服务商第三方应用下的成员ID
     */
    private List<WeTransformUser> openUseridList;

    /**
     * 不可用userid list
     */
    private List<String> invalidUseridList;
}
