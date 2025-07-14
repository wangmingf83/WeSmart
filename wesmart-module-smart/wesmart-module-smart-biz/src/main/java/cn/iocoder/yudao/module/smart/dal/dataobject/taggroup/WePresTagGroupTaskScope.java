package cn.iocoder.yudao.module.smart.dal.dataobject.taggroup;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 老客户标签建群任务使用人对象
 */
@Schema(description = "老客户标签建群跟进者")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("we_pres_tag_group_scope")
public class WePresTagGroupTaskScope extends BaseEntity {

    private static final long serialVersionUID = 86648978618184060L;
    /**
     * 老客户标签建群任务id
     */
    @Schema(description = "老客户标签建群任务id")
    private Long taskId;

    /**
     * 任务目标员工id
     */
    @Schema(description = "任务目标员工id")
    private String weUserId;


    @Schema(description = "有效标识", hidden = true)
    @JsonIgnore
    private Integer delFlag = 0;
}
