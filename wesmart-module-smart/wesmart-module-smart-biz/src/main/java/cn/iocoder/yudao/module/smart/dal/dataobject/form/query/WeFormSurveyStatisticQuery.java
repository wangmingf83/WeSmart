package cn.iocoder.yudao.module.smart.dal.dataobject.form.query;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author danmo
 * @date 2022年09月20日 18:24
 */
@Data
public class WeFormSurveyStatisticQuery extends BaseEntity {

    /**
     * 查询时间类型
     * customization 自定义 当为自定义类型时，开始时间和结束时间为必填
     * week 周
     * month 月
     */
    @Schema(description = "查询时间类型")
    private String type;

    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    //@NotNull(message = "问卷ID不能为空")
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

    @Schema(description = "问卷答案")
    private String answer;

    @Schema(description = "数据来源")
    private String dataSource;
}
