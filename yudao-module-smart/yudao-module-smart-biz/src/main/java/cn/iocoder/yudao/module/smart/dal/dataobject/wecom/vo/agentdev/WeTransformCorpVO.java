package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.agentdev;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author leejoker
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeTransformCorpVO extends WeResultVo {
    /**
     * 仅限第三方服务商，转换已获授权企业的corpid
     */
    private String openCorpId;
}
