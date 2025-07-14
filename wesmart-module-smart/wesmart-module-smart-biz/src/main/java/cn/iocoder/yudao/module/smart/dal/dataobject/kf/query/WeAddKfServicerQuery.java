package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * @author danmo
 * @description 新增客服入参
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeAddKfServicerQuery {

    @NotNull(message = "客服ID不能为空")
    private Long id;

    @Schema(description = "是否分时段: 1-否 2-是")
    private Integer splitTime;

    @Schema(description = "分配方式: 1-轮流 2-空闲")
    private Integer allocationWay;

    @Schema(description = "是否有限分配: 1-否 2-是")
    private Integer isPriority;

    @Schema(description = "接待限制")
    private Integer receiveLimit;

    @Schema(description = "排队提醒: 1-开启 2-关闭")
    private Integer queueNotice;

    @Schema(description = "排队提醒内容")
    private String queueNoticeContent;

    @Schema(description = "客户超时未回复提醒: 1-开启 2-关闭")
    private Integer timeOutNotice;

    @Schema(description = "客户超时时间")
    private Integer timeOut;

    @Schema(description = "客户超时时间类型 1-分钟 2-小时")
    private Integer timeOutType;

    @Schema(description = "客户超时未回复提醒内容")
    private String timeOutContent;


    @Schema(description = "客服超时未回复提醒: 1-开启 2-关闭")
    private Integer kfTimeOutNotice;

    @Schema(description = "客服超时时间")
    private Integer kfTimeOut;

    @Schema(description = "客户超时时间类型 1-分钟 2-小时")
    private Integer kfTimeOutType;


    @Schema(description = "自动结束提醒: 1-开启 2-关闭")
    private Integer endNotice;

    @Schema(description = "自动结束时间")
    private Integer endNoticeTime;

    @Schema(description = "自动结束时间类型 1-分钟 2-小时")
    private Integer endTimeType;

    @Schema(description = "自动结束提醒内容类型 1-会话质量评价 2-会话结束语")
    private Integer endContentType;

    @Schema(description = "自动结束提醒内容")
    private String endContent;

    @Size(message = "员工列表超出限制", max = 100)
    @Schema(description = "员工列表")
    private List<String> userIdList;

    @Size(message = "部门列表超出限制", max = 20)
    @Schema(description = "接待人员部门id列表")
    private List<Long> departmentIdList;
}
