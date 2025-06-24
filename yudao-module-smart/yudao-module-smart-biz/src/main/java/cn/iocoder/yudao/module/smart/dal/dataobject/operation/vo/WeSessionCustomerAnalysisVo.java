package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author danmo
 * @description 客户联系
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeSessionCustomerAnalysisVo {

    @Schema(description = "昨日单聊总数")
    private int ydChatCnt;

    @Schema(description = "前日单聊总数")
    private int bydChatCnt;

    @Schema(description = "昨日发送消息数")
    private int ydMessageCnt;

    @Schema(description = "前日发送消息数")
    private int bydMessageCnt;

    @Schema(description = "昨日回复单聊占比")
    private double ydReplyPercentage;

    @Schema(description = "前日回复单聊占比")
    private double bydReplyPercentage;

    @Schema(description = "昨日平均首次回复时长(分)")
    private double ydAvgReplyTime;

    @Schema(description = "前日平均首次回复时长(分)")
    private double bydAvgReplyTime;

    public int getBydChatCnt() {
        return this.ydChatCnt - this.bydChatCnt;
    }

    public int getBydMessageCnt() {
        return this.ydMessageCnt - this.bydMessageCnt;
    }

    public double getYdAvgReplyTime() {
        BigDecimal b = new BigDecimal(this.ydAvgReplyTime);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getBydAvgReplyTime() {
        BigDecimal b = new BigDecimal(this.ydAvgReplyTime - this.bydAvgReplyTime);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getYdReplyPercentage() {
        BigDecimal b = new BigDecimal(this.ydReplyPercentage);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public double getBydReplyPercentage() {
        BigDecimal b = new BigDecimal(this.ydReplyPercentage - this.bydReplyPercentage);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
