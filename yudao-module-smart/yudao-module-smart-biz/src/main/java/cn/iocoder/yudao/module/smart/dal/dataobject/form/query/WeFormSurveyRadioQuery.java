package cn.iocoder.yudao.module.smart.dal.dataobject.form.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author danmo
 * @date 2022年09月20日 18:24
 */
@Data
public class WeFormSurveyRadioQuery {

    @Schema(description = "表单Id")
    private String formId;

    /** 开始时间 */
    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /** 结束时间 */
    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Schema(description = "数据来源")
    private String dataSource;

    /**
     * 题号
     */
    @Schema(description = "题号")
    private String questionNumber;

    @Schema(description = "控件名称")
    private String label;

    @Schema(description = "选择内容")
    private String defaultValue;

    @Schema(description = "上级地区id")
    private Integer parentCode = 0;

    @Schema(description = "当前地区id",required = false)
    private Integer code;
}
