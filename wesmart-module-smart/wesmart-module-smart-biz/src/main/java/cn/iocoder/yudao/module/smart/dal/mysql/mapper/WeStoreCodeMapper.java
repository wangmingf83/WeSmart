package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.entity.WeStoreCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeStoreCodeMapper extends BaseMapper<WeStoreCode> {


    List<WeStoreCode> findStoreCode(@Param("currentLng") String currentLng,
                                    @Param("currentLat") String currentLat,
                                    @Param("area") String area, @Param("raidus") String raidus);

    void batchUpdateState(@Param("storeState") Integer storeState,@Param("ids") List<Long> ids);
}
