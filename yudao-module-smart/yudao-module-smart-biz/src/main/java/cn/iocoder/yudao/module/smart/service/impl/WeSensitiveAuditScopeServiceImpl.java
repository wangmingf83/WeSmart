package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeSensitiveAuditScopeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveAuditScope;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeSensitiveAuditScopeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 敏感词审计范围(WeSensitiveAuditScope)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
@Service
public class WeSensitiveAuditScopeServiceImpl extends ServiceImpl<WeSensitiveAuditScopeMapper, WeSensitiveAuditScope> implements IWeSensitiveAuditScopeService {


    @Override
    public void deleteAuditScopeBySensitiveId(Long sensitiveId) {
        deleteAuditScopeBySensitiveIds(Lists.newArrayList(sensitiveId));
    }

    @Override
    public void deleteAuditScopeBySensitiveIds(List<Long> sensitiveIds) {
        WeSensitiveAuditScope sensitiveAuditScope = new WeSensitiveAuditScope();
        sensitiveAuditScope.setDelFlag(1);
        update(sensitiveAuditScope,new LambdaQueryWrapper<WeSensitiveAuditScope>()
                .in(WeSensitiveAuditScope::getSensitiveId,sensitiveIds));
    }
}
