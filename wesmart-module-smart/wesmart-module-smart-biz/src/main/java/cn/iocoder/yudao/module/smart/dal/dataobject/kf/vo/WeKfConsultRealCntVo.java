package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.text.NumberFormat;

/**
 * @author danmo
 * @description 客服客户场景实时数据
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeKfConsultRealCntVo {

    @Schema(description = "时间")
    @ExcelProperty("日期")
    private String xTime;

    @Schema(description = "咨询客户总数")
    @ExcelProperty("咨询客户总数")
    private Integer consultTotalCnt;

    @Schema(description = "接待客户总数")
    @ExcelProperty("接待客户总数")
    private Integer receptionTotalCnt;

    @Schema(description = "回复客户总数")
    @ExcelProperty("回复客户总数")
    private Integer replyTotalCnt;

    @Schema(description = "回复聊天占比")
    @ExcelProperty("回复聊天占比")
    private String replyProportionCnt;

    @Schema(description = "平均咨询响应时长")
    @ExcelProperty("平均咨询响应时长")
    private String avgConsultReplyDuration = "0";

    @Schema(description = "平均咨询时长")
    @ExcelProperty("平均咨询时长")
    private String avgConsultDuration = "0";

    public String getReplyProportionCnt() {
        if(replyTotalCnt == null || replyTotalCnt == 0){
            return "0%";
        }
        if(consultTotalCnt == null){
            return "-";
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format((float)replyTotalCnt/(float)consultTotalCnt*100) + "%";
    }

}
