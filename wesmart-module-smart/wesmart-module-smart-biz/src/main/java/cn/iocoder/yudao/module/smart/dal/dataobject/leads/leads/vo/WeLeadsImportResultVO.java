package cn.iocoder.yudao.module.smart.dal.dataobject.leads.leads.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * excel导入结果
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/14 16:33
 */
@Data
public class WeLeadsImportResultVO {

    @Schema(description = "成功数")
    private Integer succeedCount;

    @Schema(description = "失败数")
    private Integer failCount;

    @Schema(description = "重复数")
    private Integer repetitionCount;

    @Schema(description = "导入结果反馈")
    private String feedbackMessage;

    public void buildFeedbackMessage() {
        this.feedbackMessage = "成功导入" + succeedCount + "条线索，重复线索" + repetitionCount + "条，格式错误或无效线索" + failCount + "条未导入";
    }

    public void combined(WeLeadsImportResultVO other) {
        this.succeedCount = this.succeedCount + other.succeedCount;
        this.failCount = this.failCount + other.failCount;
    }

}
