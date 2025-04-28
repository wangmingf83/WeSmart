package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;

import java.util.List;

/**
 * 企业id相关配置(WeCorpAccount)
 *
 * @author danmo
 * @since 2022-03-08 19:01:14
 */
public interface IWeCorpAccountService extends IService<WeCorpAccount> {

    List<WeCorpAccount> getAllCorpAccountInfo();

    WeCorpAccount getCorpAccountByCorpId(String corpId);

    void startCustomerChurnNoticeSwitch(String status);

    String getCustomerChurnNoticeSwitch();

    void addOrUpdate(WeCorpAccount weCorpAccount);
}
