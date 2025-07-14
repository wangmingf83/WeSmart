package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author danmo
 * @description 质检列表入参
 * @date 2021/11/9 13:58
 **/
@Schema
@Data
public class WeQiRuleListQuery {

    @Schema(description = "质检ID",hidden = true)
    private List<Long> qiRuleIds;

    @Schema(description = "规则名称")
    private String name;

    @Schema(description = "会话类型 不传-全部 2-客户会话 3-客群会话")
    private List<Integer> chatType;

    @Schema(description = "员工ID")
    private String userIds;

    @Schema(description = "督导ID")
    private String manageUserId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "开始时间")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "排期查询",hidden = true)
    private Integer workCycle;

    @Schema(description = "排期时间",hidden = true)
    private Date formatTime;

    @Schema(description = "是否展示名称",hidden = true)
    private Boolean isShow = true;

}
