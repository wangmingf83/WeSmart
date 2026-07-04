package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.tenant.core.util.TenantUtils;
import cn.iocoder.yudao.module.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.common.RedisService;

import cn.iocoder.yudao.module.smart.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeCorpAccountMapper;
import cn.iocoder.yudao.module.smart.dal.redis.RedisKeyConstants;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountCacheService;
import cn.iocoder.yudao.module.wecom.core.QwServiceFactory;
import cn.iocoder.yudao.module.wecom.service.IQwCorpService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.getMaxValue;


/**
 * 企业微信配置缓存服务，用于服务多租户下企业微信账号服务
 * 使用方式见企业微信回接
 * @author 圭图智能
 * @since 2025-03-08 19:01:14
 */
@Slf4j
@Service
public class WeCorpAccountCacheServiceImpl extends ServiceImpl<WeCorpAccountMapper, WeCorpAccount> implements IWeCorpAccountCacheService {

    @Autowired
    private RedisService redisService;

    @Resource
    private IQwCorpService qwCorpService;

    @Autowired
    private ScrmSysUserClient scrmSysUserClient;

    @Resource
    private WeCorpAccountMapper weCorpAccountMapper;

    @Resource
    @Lazy // 延迟加载，解决循环依赖的问题
    private QwServiceFactory qwServiceFactory;



    @Override
    @PostConstruct
    public void initLocalCache() {
        log.info("[initLocalCache][开始初始化企业微信账号缓存]");
        refreshCache();
    }

    /**
     * 刷新缓存到 Redis
     */
    private void refreshCache() {
        // 注意：忽略自动多租户，因为要全局初始化缓存
        TenantUtils.executeIgnore(() -> {
            // 第一步：查询数据
            List<WeCorpAccount> accounts = Collections.emptyList();
            try {
                accounts = weCorpAccountMapper.selectList(new LambdaQueryWrapperX<WeCorpAccount>()
                        .eq(WeCorpAccount::getDelFlag, 0));
            } catch (Throwable ex) {
                if (!ex.getMessage().contains("doesn't exist")) {
                    throw ex;
                }
                log.error("[企业微信账号 - 表结构未导入]");
            }

            if (CollUtil.isEmpty(accounts)) {
                log.warn("[企业微信账号 - 未查询到有效数据]");
                // 清空缓存
                redisService.deleteObject(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY);
                redisService.deleteObject(RedisKeyConstants.TENANT_ACCOUNT_CACHE_KEY);
                return;
            }

            log.info("[initLocalCacheIfUpdate][企业微信账号，数量为:{}]", accounts.size());

            // 第二步：构建缓存并存储到 Redis
            Map<String, WeCorpAccount> corpAccountMap = accounts.stream()
                    .collect(Collectors.toMap(WeCorpAccount::getCorpId, account -> account));

//            Map<Long, WeCorpAccount> tenantAccountMap = accounts.stream()
//                    .collect(Collectors.toMap(WeCorpAccount::getTenantId, account -> account));
            Map<String, WeCorpAccount> tenantAccountMap = accounts.stream()
                    .collect(Collectors.toMap(
                            account -> String.valueOf(account.getTenantId()),  // 避免redis反序列化时的冲突造成取不到值
                            account -> account
                    ));

            // 存储到 Redis
            redisService.setCacheObject(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY, corpAccountMap,
                    (int) RedisKeyConstants.CACHE_EXPIRE_HOURS, TimeUnit.HOURS);

            redisService.setCacheObject(RedisKeyConstants.TENANT_ACCOUNT_CACHE_KEY, tenantAccountMap,
                    (int) RedisKeyConstants.CACHE_EXPIRE_HOURS, TimeUnit.HOURS);

            log.info("[refreshCache][企业微信账号缓存已刷新，corpId映射数量:{}，tenantId映射数量:{}]",
                    corpAccountMap.size(), tenantAccountMap.size());
        });
    }

    /**
     * 通过定时任务轮询，刷新缓存
     *
     * 目的：多节点部署时，通过轮询"通知"所有节点，进行刷新
     */
    @Scheduled(initialDelay = 60, fixedRate = 3600, timeUnit = TimeUnit.SECONDS)
    public void refreshLocalCache() {
        log.info("[refreshLocalCache][开始刷新企业微信账号缓存]");

        try {
            // 获取缓存中的最大更新时间
            Map<String, WeCorpAccount> cachedCorpMap = redisService.getCacheObject(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY);

            if (CollUtil.isEmpty(cachedCorpMap)) {
                // 缓存为空，直接刷新
                refreshCache();
                return;
            }

            // 从缓存中提取所有账号，获取最大更新时间
            Date maxTime = getMaxValue(cachedCorpMap.values(), WeCorpAccount::getUpdateTime);

            // 查询数据库是否有更新的数据
            TenantUtils.executeIgnore(() -> {
                if (weCorpAccountMapper.selectCountByUpdateTimeGt(maxTime) > 0) {
                    refreshCache();
                    log.info("[refreshLocalCache][检测到数据更新，已刷新缓存]");
                } else {
                    // 延长缓存过期时间
                    redisService.expire(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY, RedisKeyConstants.CACHE_EXPIRE_HOURS, TimeUnit.HOURS);
                    redisService.expire(RedisKeyConstants.TENANT_ACCOUNT_CACHE_KEY, RedisKeyConstants.CACHE_EXPIRE_HOURS, TimeUnit.HOURS);
                    log.debug("[refreshLocalCache][数据无更新，延长缓存过期时间]");
                }
            });
        } catch (Exception e) {
            log.error("[refreshLocalCache][刷新缓存异常]", e);
            // 异常情况下重新刷新缓存
            refreshCache();
        }
    }

    @Override
    public WeCorpAccount getAccountFromCache(String corpId) {
        if (StringUtils.isEmpty(corpId)) {
            return null;
        }

        try {
            Map<String, WeCorpAccount> corpAccountMap = redisService.getCacheObject(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY);
            if (CollUtil.isEmpty(corpAccountMap)) {
                // 缓存为空，尝试刷新
                refreshCache();
                corpAccountMap = redisService.getCacheObject(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY);
            }
            return corpAccountMap != null ? corpAccountMap.get(corpId) : null;
        } catch (Exception e) {
            log.error("[getAccountFromCache][获取企业微信账号缓存异常] corpId: {}", corpId, e);
            return null;
        }
    }

    @Override
    public WeCorpAccount getAccountCacheForTenantId(Long tenantId) {
        if (tenantId == null) {
            return null;
        }

        try {
            Map<Long, WeCorpAccount> tenantAccountMap = redisService.getCacheObject(RedisKeyConstants.TENANT_ACCOUNT_CACHE_KEY);
            if (CollUtil.isEmpty(tenantAccountMap)) {
                // 缓存为空，尝试刷新
                refreshCache();
                tenantAccountMap = redisService.getCacheObject(RedisKeyConstants.TENANT_ACCOUNT_CACHE_KEY);
            }
            return tenantAccountMap != null ? tenantAccountMap.get(String.valueOf(tenantId)) : null;
        } catch (Exception e) {
            log.error("[getAccountCacheForTenantId][获取租户企业微信账号缓存异常] tenantId: {}", tenantId, e);
            return null;
        }
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        redisService.deleteObject(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY);
        redisService.deleteObject(RedisKeyConstants.TENANT_ACCOUNT_CACHE_KEY);
        log.info("[clearCache][企业微信账号缓存已清除]");
    }

    /**
     * 获取所有企业微信账号
     */
    public List<WeCorpAccount> getAllAccounts() {
        try {
            Map<String, WeCorpAccount> corpAccountMap = redisService.getCacheObject(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY);
            if (CollUtil.isEmpty(corpAccountMap)) {
                refreshCache();
                corpAccountMap = redisService.getCacheObject(RedisKeyConstants.CORP_ACCOUNT_CACHE_KEY);
            }
            return corpAccountMap != null ? new ArrayList<>(corpAccountMap.values()) : Collections.emptyList();
        } catch (Exception e) {
            log.error("[getAllAccounts][获取所有企业微信账号异常]", e);
            return Collections.emptyList();
        }
    }
}
