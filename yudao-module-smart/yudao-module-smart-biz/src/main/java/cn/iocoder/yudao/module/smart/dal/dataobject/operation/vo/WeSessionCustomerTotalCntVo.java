package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.common.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author danmo
 * @description 客户聊天总数
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeSessionCustomerTotalCntVo {

    @Schema(description = "日期")
    @Excel(name = "日期")
    @ExcelProperty("日期")
    private String xTime;

    @Schema(description = "单聊总数")
    @Excel(name = "单聊总数")
    @ExcelProperty("单聊总数")
    private Integer chatCnt;

    @Schema(description = "发送消息数")
    @Excel(name = "发送消息数")
    @ExcelProperty("发送消息数")
    private Integer messageCnt;

    @Schema(description = "回复单聊占比")
    @Excel(name = "回复单聊占比")
    @ExcelProperty("回复单聊占比")
    private Double replyPercentage;

    @Schema(description = "平均首次回复时长")
    @Excel(name = "平均首次回复时长")
    @ExcelProperty("平均首次回复时长")
    private Double avgReplyTime;

    public Double getReplyPercentage() {
        if(replyPercentage == null){
            return 0.0;
        }
        BigDecimal b = new BigDecimal(this.replyPercentage);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public Double getAvgReplyTime() {
        if(this.avgReplyTime == null){
            return 0.0d;
        }
        BigDecimal b = new BigDecimal(this.avgReplyTime);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
