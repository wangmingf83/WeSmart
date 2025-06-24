package cn.iocoder.yudao.module.smart.dal.dataobject.envelopes;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 红包
 */
@Schema
@Data
@Builder
@TableName("we_red_envelopes")
@AllArgsConstructor
@NoArgsConstructor
public class WeRedEnvelopes extends BaseEntity {
    /**
     * 主键
     */
    @TableId
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 金额
     */
    @Schema(description = "金额")
    private Integer money;

    /**
     * 1:客户;2:客群;3:客户与客群
     */
    @Schema(description = "1:客户;2:客群;3:客户与客群")
    private Integer sceneType;

    /**
     * 红包名称
     */
    @Schema(description = "红包名称")
    private String name;

    /**
     * 0:启用;1:停用
     */
    @Schema(description = "状态 0:启用;1:停用")
    private Integer status;

    /**
     * 发送次数
     */
    @Schema(description = "发送次数")
    private Integer sendTimes;

    /**
     * 红包类型:0:企业红包;1:个人红包
     */
    @Schema(description = "红包类型:0:企业红包;1:个人红包")
    private Integer redEnvelopesType;

    /**
     * 0:正常;1:删除;
     */
    @TableLogic
    @Schema(description = "删除标识 0:正常;1:删除;")
    private Integer delFlag;

}

