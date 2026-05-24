package cn.iocoder.yudao.module.smart.service;

import cn.iocoder.yudao.module.smart.api.vo.WeCorpAccountVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;

import java.util.List;

/**
 * 企业id相关配置(WeCorpAccount)
 *
 * @author 圭图智能
 * @since 2022-03-08 19:01:14
 */
public interface IWeCorpAccountService extends IService<WeCorpAccount> {

    List<WeCorpAccount> getAllCorpAccountInfo();

    List<WeCorpAccount> getAllCorpAccountInfoOnlyOne();

    WeCorpAccount getCorpAccountByCorpId(String corpId);

    WeCorpAccount getCorpAccountByTenantId(Long tenantId);

    WeCorpAccountVo rpcCorpAccountByCorpId(String corpId);

    void startCustomerChurnNoticeSwitch(String status);

    String getCustomerChurnNoticeSwitch();

    void addOrUpdate(WeCorpAccount weCorpAccount);
}
