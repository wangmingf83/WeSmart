package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 客服
 * @date 2021/12/13 10:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeAddKfVo extends WeResultVo {

    /**
     * 新创建的客服帐号ID
     */
    private String openKfId;
}
