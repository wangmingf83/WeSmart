package cn.iocoder.yudao.module.smart.dal.dataobject.form.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author danmo
 * @date 2022年09月20日 18:24
 */
@Data
public class WeFormSurveyAnswerQuery {


    @Schema(description = "id")
    private Long id;

    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    @NotNull(message = "问卷ID不能为空")
    private Long belongId;

    /**
     * 数据来源
     */
    @Schema(description = "数据来源")
    private String dataSource;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String mobile;

    /**
     * 微信openId
     */
    @Schema(description = "微信OpenId")
    private String openId;


    /**
     * ip地址
     */
    @Schema(description = "ip地址")
    private String ipAddr;
}
