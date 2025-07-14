package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 会话质检新增入参
 *
 * @author danmo
 * @date 2023/05/05 18:22
 **/
@Schema
@Data
public class WeQiRuleAddQuery {

    @Schema(description = "活码Id", hidden = true)
    private Long qiId;

    @Schema(description = "名称")
    @NotEmpty(message = "规则名称不能为空")
    private String name;

    /**
     * 超时时间
     */
    @Schema(description = "超时时间")
    @NotNull(message = "超时时间不能为空")
    private Integer timeOut;


    /**
     * 会话类型 1-全部 2-客户会话 3-客群会话
     */
    @Schema(description = "会话类型 1-全部 2-客户会话 3-客群会话")
    private Integer chatType;

    /**
     * 督导人员（逗号相隔）
     */
    @Schema(description = "督导人员（逗号相隔）")
    private String manageUser;


    @Schema(description = "员工列表")
    private List<WeQiUserInfoQuery> qiUserInfos;
}
