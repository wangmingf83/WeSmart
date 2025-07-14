package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.WeFissionNotice;
import org.apache.ibatis.annotations.Param;

/**
* @author robin
* @description 针对表【we_fission_detail(裂变明细表)】的数据库操作Mapper
* @createDate 2023-03-14 14:07:21
* @Entity com.GuituAi.WeFissionDetail
*/
public interface WeFissionNoticeMapper extends BaseMapper<WeFissionNotice> {

    void physicalDelete(@Param("fissionId") Long fissionId);
}




