package cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.query;



import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author leejoker <1056650571@qq.com>
 * @version 1.0
 * @date 2021/1/4 21:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeSensitiveHitQuery extends BaseEntity {
    /**
     * 1:组织机构id,2:成员id
     */
    @Schema(description = "审计范围类型, 1 组织机构 2 成员")
    private Integer scopeType;

    /**
     * 审计对象id
     */
    @Schema(description = "审计范围id")
    private String auditScopeId;

    /**
     * 关键词
     */
    @Schema(description = "关键词")
    private String keyword;
}
