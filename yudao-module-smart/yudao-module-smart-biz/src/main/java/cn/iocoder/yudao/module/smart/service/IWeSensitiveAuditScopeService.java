package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveAuditScope;

import java.util.List;

/**
 * 敏感词审计范围(WeSensitiveAuditScope)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
public interface IWeSensitiveAuditScopeService extends IService<WeSensitiveAuditScope> {

    void deleteAuditScopeBySensitiveId(Long sensitiveId);

    void deleteAuditScopeBySensitiveIds(List<Long> sensitiveIds);
}
