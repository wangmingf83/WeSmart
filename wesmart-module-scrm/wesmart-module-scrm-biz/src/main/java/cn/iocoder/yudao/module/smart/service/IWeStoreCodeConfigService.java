package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.entity.WeStoreCodeConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.query.WxStoreCodeQuery;

import java.io.IOException;

public interface IWeStoreCodeConfigService extends IService<WeStoreCodeConfig> {


    /**
     * 创建或更新门店导购码或门店群活码
     * @param storeCodeConfig
     */
    void createOrUpdate(WeStoreCodeConfig storeCodeConfig) throws IOException;


    /**
     * 获取对应的门店相关配置
     * @param wxStoreCodeQuery
     * @return
     */
    WeStoreCodeConfig getWeStoreCodeConfig(WxStoreCodeQuery wxStoreCodeQuery);

}
