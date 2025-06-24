package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.tag.vo.WeTagVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 拉新活码详情出参
 * @date 2023/03/07 22:15
 **/
@Schema
@Data
public class WeLxQrCodeDetailVo {


    /**
     * 活码ID
     */
    @Schema(description = "活码ID")
    private Long id;


    /**
     * 活码名称
     */
    @Schema(description = "活码名称")
    private String name;


    /**
     * 拉新方式 1：红包 2：卡券
     */
    @Schema(description = "拉新方式 1：红包 2：卡券")
    private Integer type;


    /**
     * 业务ID
     */
    @Schema(description = "业务ID")
    private Long businessId;

    /**
     * 业务数据
     */
    @Schema(description = "业务数据")
    private String businessData;


    /**
     * 添加渠道
     */
    @Schema(description = "添加渠道")
    private String state;


    /**
     * 扫码次数
     */
    @Schema(description = "扫码次数")
    private Integer scanNum;


    /**
     * 二维码配置id
     */
    @Schema(description = "二维码配置id")
    private String configId;


    /**
     * 二维码地址
     */
    @Schema(description = "二维码地址")
    private String qrCode;

    /**
     * 链接地址
     */
    @Schema(description = "链接地址")
    private String linkPath;

    /**
     * 图片地址
     */
    @Schema(description = "图片地址")
    private String imageUrl;

    @Schema(description = "标签")
    private List<WeTagVo> qrTags;

    @Schema(description = "员工信息")
    private List<WeLxQrScopeUserVo> qrUserInfos;

    @Schema(description = "素材")
    private List<WeQrAttachments> qrAttachments;
}
