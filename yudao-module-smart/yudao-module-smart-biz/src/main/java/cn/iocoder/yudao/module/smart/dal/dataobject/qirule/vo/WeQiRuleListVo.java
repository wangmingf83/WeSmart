package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRule;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * 会话质检列表出参
 *
 * @author danmo
 * @date 2023/05/05 18:22
 **/

@Schema
@Data
public class WeQiRuleListVo extends WeQiRule {
    /**
     * 督导人员
     */
    @Schema(description = "督导人员")
    private List<WeQiRuleUserVo> manageUserInfo;

    @Schema(description = "质检规则范围")
    private List<WeQiRuleUserVo> qiRuleScope;
}
