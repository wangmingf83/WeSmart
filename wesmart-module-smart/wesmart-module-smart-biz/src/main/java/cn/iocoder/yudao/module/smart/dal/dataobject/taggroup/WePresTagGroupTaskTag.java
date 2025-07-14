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
 * 老客标签建群任务标签关联对象
 */
@Schema(description = "老客标签建群标签")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("we_pres_tag_group_tag")
public class WePresTagGroupTaskTag extends BaseEntity {

    private static final long serialVersionUID = 4859443233276436220L;
    /**
     * 老客建群任务id
     */
    @Schema(description = "老客建群任务id")
    private Long taskId;

    /**
     * 标签id
     */
    @Schema(description = "标签id")
    private String tagId;

    /**
     * 有效标识
     */
    @Schema(description = "有效标识")
    @JsonIgnore
    private Integer delFlag = 0;

}
