package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiUserInfoQuery;

import java.util.List;

/**
 * 质检规则范围表(WeQiRuleScope)
 *
 * @author danmo
 * @since 2023-05-05 16:57:31
 */
public interface IWeQiRuleScopeService extends IService<WeQiRuleScope> {

    void saveBatchByQiId(Long qiId, List<WeQiUserInfoQuery> qiUserInfos);

    Boolean delBatchByQiIds(List<Long> qiIds);

    void updateBatchByQiId(Long qiId, List<WeQiUserInfoQuery> qiUserInfos);

    List<WeQiRuleScope> getQiRuleScopeByQiIds(List<Long> qiIds);
}
