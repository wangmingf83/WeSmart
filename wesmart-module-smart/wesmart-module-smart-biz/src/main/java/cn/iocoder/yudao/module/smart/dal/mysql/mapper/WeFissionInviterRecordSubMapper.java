package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.WeFissionInviterRecordSub;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author robin
* @description 针对表【we_fission_detail_sub(裂变明细子表)】的数据库操作Mapper
* @createDate 2023-03-14 14:07:21
* @Entity com.GuituAi.WeFissionDetailSub
*/
public interface WeFissionInviterRecordSubMapper extends BaseMapper<WeFissionInviterRecordSub> {
    void batchSaveOrUpdate(@Param("weFissionInviterRecordSubList") List<WeFissionInviterRecordSub> weFissionInviterRecordSubList);

}




