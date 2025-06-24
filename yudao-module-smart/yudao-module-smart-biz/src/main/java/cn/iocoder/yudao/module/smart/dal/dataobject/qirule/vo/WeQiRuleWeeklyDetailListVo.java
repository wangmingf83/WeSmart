package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 会话质检周报详情列表出参
 *
 * @author danmo
 * @date 2023/05/05 18:22
 **/

@Schema
@Data
public class WeQiRuleWeeklyDetailListVo {

    @Schema(description = "成员ID")
    @ExcelIgnore
    private String userId;

    @Schema(description = "成员名称")
    @ExcelProperty("成员名称")
    private String userName;

    @Schema(description = "所属部门")
    @ExcelProperty("所属部门")
    private String deptName;

    @Schema(description = "客户会话数")
    @ExcelIgnore
    private String chatNum;

    @Schema(description = "客群会话数")
    @ExcelIgnore
    private String groupChatNum;

    @Schema(description = "成员回复次数")
    @ExcelIgnore
    private String replyNum;

    @Schema(description = "成员超时次数")
    @ExcelProperty("超时次数")
    private String timeOutNum;

    @Schema(description = "成员超时率")
    @ExcelProperty("超时率")
    private String timeOutRate;

    @Schema(description = "客户会话超时率")
    @ExcelProperty("客户会话超时率")
    private String chatTimeOutRate;

    @Schema(description = "客群会话超时率")
    @ExcelProperty("客群会话超时率")
    private String groupChatTimeOutRate;
}
