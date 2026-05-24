package cn.iocoder.yudao.module.smart.service;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 企业微信配置缓存服务，用于服务多租户下企业微信账号服务
 * 使用方式见企业微信回接
 * @author 圭图智能
 * @since 2025-03-08 19:01:14
 */
public interface IWeCorpAccountCacheService extends IService<WeCorpAccount> {

    /**
     * 初始化缓存
     */
    void initLocalCache();


    /**
     * 通过定时任务轮询，刷新缓存
     *
     * 目的：多节点部署时，通过轮询”通知“所有节点，进行刷新
     */
    void refreshLocalCache();

    public WeCorpAccount getAccountFromCache(String corpId);

    public WeCorpAccount getAccountCacheForTenantId(Long tenantId);
}
