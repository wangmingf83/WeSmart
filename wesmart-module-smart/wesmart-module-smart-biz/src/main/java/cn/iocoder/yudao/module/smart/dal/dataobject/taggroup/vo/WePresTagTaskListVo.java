package cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Schema(description = "老客标签建群列表接口简略信息")
@Data
public class WePresTagTaskListVo {
    @Schema(description = "任务id")
    private Long taskId;

    @Schema(description = "任务名")
    private String taskName;

    @Schema(description = "标签名列表")
    private List<String> tagList;

    @Schema(description = "发送类型 0: 企业群发 1: 个人群发")
    private Integer sendType;

    @Schema(description = "当前群人数")
    private Integer totalMember;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @Schema(description = "创建者")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createBy;

    @JsonIgnore
    private String tagListStr;
}
