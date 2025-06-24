package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.sop.WeSopPushTime;
import cn.iocoder.yudao.module.smart.dal.dataobject.sop.dto.WeSopPushTimeDto;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* @author robin
* @description 针对表【we_sop_push_time】的数据库操作Mapper
* @createDate 2022-09-07 15:23:22
* @Entity generator.domain.WeSopPushTime
*/
public interface WeSopPushTimeMapper extends BaseMapper<WeSopPushTime> {

    /**
     * 获取sop下所有需要发送的素材等
     * @param sopBaseId
     * @return
     */
    List<WeSopPushTimeDto> findWeSopPushTimeDto(@Param("sopBaseId") Long sopBaseId);


}
