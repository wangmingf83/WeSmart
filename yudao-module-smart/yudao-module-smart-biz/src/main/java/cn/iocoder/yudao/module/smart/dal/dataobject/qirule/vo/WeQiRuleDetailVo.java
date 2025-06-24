package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * 会话质检详情出参
 *
 * @author danmo
 * @date 2023/05/05 18:22
 **/

@Schema
@Data
public class WeQiRuleDetailVo {

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    private Long id;


    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String name;


    /**
     * 超时时间
     */
    @Schema(description = "超时时间")
    private Integer timeOut;


    /**
     * 会话类型 1-全部 2-客户会话 3-客群会话
     */
    @Schema(description = "会话类型 1-全部 2-客户会话 3-客群会话")
    private Integer chatType;

    @Schema(hidden = true)
    private String manageUser;
    /**
     * 督导人员
     */
    @Schema(description = "督导人员")
    private List<WeQiRuleUserVo> manageUserInfo;

    @Schema(description = "质检规则范围")
    private List<WeQiRuleScopeVo> qiRuleScope;
}
