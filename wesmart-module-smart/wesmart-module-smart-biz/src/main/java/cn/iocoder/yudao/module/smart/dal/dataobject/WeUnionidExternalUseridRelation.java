package cn.iocoder.yudao.module.smart.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * unionid与external_userid关联表
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/10/26 17:13
 */
@Schema
@Data
public class WeUnionidExternalUseridRelation {

    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    @Schema(description = "微信客户的openid")
    @TableField("openid")
    private String openid;

    @Schema(description = "微信客户的unionid")
    @TableField("unionid")
    private String unionid;

    @Schema(description = "该授权企业的外部联系人ID")
    @TableField("external_userid")
    private String externalUserid;

    @Schema(description = "临时外部联系人ID")
    @TableField("pending_id")
    private String pendingId;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private Date updateTime;


    @Schema(description = "删除标识 0 未删除 1 已删除")
    @TableField("del_flag")
    private Integer delFlag;

}
