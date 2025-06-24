package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.entity.WeStoreCodeConfig;
import org.apache.ibatis.annotations.Param;

public interface WeStoreCodeConfigMapper extends BaseMapper<WeStoreCodeConfig> {

    
    WeStoreCodeConfig getWeStoreCodeConfig(@Param("storeCodeType") Integer storeCodeType);

}
