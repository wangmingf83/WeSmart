package cn.iocoder.yudao.module.smart.dal.dataobject.material.query;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 轨迹素材隐私政策客户授权
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/10/26 15:40
 */
@Data
public class WeTrackMaterialPrivacyAuthQuery {
    /**
     * 查看人openid
     */
    @NotBlank
    private String openid;

    /**
     * 查看人uniodid
     */
    @NotBlank
    private String unionid;

}
