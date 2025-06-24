package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.kf.WeKfUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.WeKfWelcomeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 客服详情
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfInfoVo {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "企业ID")
    private String corpId;

    @Schema(description = "客服账号Id")
    private String openKfId;

    @Schema(description = "客服名称")
    private String name;

    @Schema(description = "客服头像")
    private String avatar;

    @Schema(description = "接待方式: 1-人工客服 2-智能助手")
    private Integer receptionType;

    @Schema(description = "是否分时段: 1-否 2-是")
    private Integer splitTime;

    @Schema(description = "分配方式: 1-轮流 2-空闲")
    private Integer allocationWay;

    @Schema(description = "是否优先分配: 1-否 2-是")
    private Integer isPriority;

    @Schema(description = "接待限制")
    private Integer receiveLimit;

    @Schema(description = "排队提醒: 1-开启 2-关闭")
    private Integer queueNotice;

    @Schema(description = "排队提醒内容")
    private String queueNoticeContent;

    @Schema(description = "超时未回复提醒: 1-开启 2-关闭")
    private Integer timeOutNotice;

    @Schema(description = "超时时间")
    private Integer timeOut;

    @Schema(description = "超时时间类型 1-分钟 2-小时")
    private Integer timeOutType;

    @Schema(description = "超时未回复提醒内容")
    private String timeOutContent;

    @Schema(description = "客服超时未回复提醒: 1-开启 2-关闭")
    private Integer kfTimeOutNotice;

    @Schema(description = "客户超时时间类型 1-分钟 2-小时")
    private Integer kfTimeOutType;

    @Schema(description = "客服超时时间")
    private Integer kfTimeOut;

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

    @Schema(description = "员工列表")
    private List<WeKfUser> userIdList;

    @Schema(description = "欢迎语")
    private List<WeKfWelcomeInfo> welcome;
}
