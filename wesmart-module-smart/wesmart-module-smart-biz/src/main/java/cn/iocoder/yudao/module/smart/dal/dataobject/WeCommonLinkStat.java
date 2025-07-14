package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;


import lombok.Data;

/**
 * 短链通用统计表(WeCommonLinkStat)
 *
 * @author danmo
 * @since 2023-07-18 13:34:19
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_common_link_stat")
public class WeCommonLinkStat extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 短链ID
     */
    @Schema(description = "短链ID")
    @TableField("short_id")
    private Long shortId;


    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "日期")
    @TableField("date_time")
    private Date dateTime;


    /**
     * 链接渠道
     */
    @Schema(description = "链接渠道")
    @TableField("type")
    private String type;


    /**
     * PV数量
     */
    @Schema(description = "PV数量")
    @TableField("pv_num")
    private Integer pvNum;


    /**
     * UV数量
     */
    @Schema(description = "UV数量")
    @TableField("uv_num")
    private Integer uvNum;


    /**
     * 打开小程序数量
     */
    @Schema(description = "打开小程序数量")
    @TableField("open_num")
    private Integer openNum;


    /**
     * 备用
     */
    @Schema(description = "备用")
    @TableField("remark")
    private String remark;

    /**
     * 删除标识 0正常 1 删除
     */
    @Schema(description = "删除标识 0正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
