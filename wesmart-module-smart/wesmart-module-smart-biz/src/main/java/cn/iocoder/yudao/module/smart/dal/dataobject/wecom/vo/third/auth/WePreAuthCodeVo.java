package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.third.auth;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 获取预授权码
 * @date 2022/3/4 11:34
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class WePreAuthCodeVo extends WeResultVo {
    /**
     * 预授权码,最长为512字节
     */
    private String preAuthCode;

    /**
     * 有效期（秒）
     */
    private Long expiresIn;
}
