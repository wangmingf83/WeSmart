package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 拉新活码列表出参
 * @date 2023/03/07 22:15
 **/
@Schema
@Data
public class WeLxQrCodeListVo extends BaseEntity {


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
     * 扫码次数
     */
    @Schema(description = "扫码次数")
    private Integer scanNum;


    @Schema(description = "新客领取数")
    private Integer receiveNum = 0;

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

    /** 创建者 */
    @Schema(description = "创建者")
    private String createBy;

    /** 创建者ID */
    @Schema(description = "创建者ID")
    private Long createById;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private Date createTime;

    /** 更新者 */
    @Schema(description = "更新者")
    private String updateBy;

    @Schema(description = "更新者ID")
    private Long updateById;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private Date updateTime;

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        super.setCreateTime(createTime);
    }
}
