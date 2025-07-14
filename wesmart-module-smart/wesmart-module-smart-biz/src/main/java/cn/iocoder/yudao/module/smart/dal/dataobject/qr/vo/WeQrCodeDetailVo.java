package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.tag.vo.WeTagVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 活码详情出参
 * @date 2021/11/8 22:15
 **/
@Schema
@Data
public class WeQrCodeDetailVo extends WeQrCode {

    @Schema(description = "分组名称")
    private String qrGroupName;

    @Schema(description = "标签")
    private List<WeTagVo> qrTags;

    @Schema(description = "适用范围")
    private List<WeQrScopeVo> qrUserInfos;

    @Schema(description = "素材")
    private List<WeQrAttachments> qrAttachments;


    @Schema(description = "活码短链")
    private String qrShortLink;
}
