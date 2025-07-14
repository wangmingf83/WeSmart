package cn.iocoder.yudao.module.smart.dal.dataobject.form.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author danmo
 * @date 2022年09月20日 18:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeFormSurveyCatalogueQuery {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "名称")
    private String surveyName;

    /**
     * 表单状态;0默认设计状态未发布，1收集中，2已暂停, 3已结束
     */
    @Schema(description = "表单状态 0-未发布，1-收集中，2-已暂停, 3-已结束")
    private Integer surveyState;

    /**
     * 分组id
     */
    @Schema(description = "分组id")
    private Long groupId;

    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    @NotNull(message = "问卷ID不能为空")
    private Long belongId;


    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private Date createTime;
}
