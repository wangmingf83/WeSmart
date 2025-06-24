package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 客服
 * @date 2021/12/13 10:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeKfDetailVo extends WeResultVo {

    /**
     * 新创建的客服帐号ID
     */
    private String openKfId;

    /**
     * 客服链接
     */
    private String url;

    /**
     * 客服头像
     */
    private String avatar;

    /**
     * 名称
     */
    private String name;
}
