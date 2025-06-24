package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.dto.WeContentSendViewDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeContentSendRecord;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.ContentDetailQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.ContentDataDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface WeContentSendRecordMapper extends BaseMapper<WeContentSendRecord> {

    ContentDataDetailVo getWeMaterialDataCount(ContentDetailQuery contentDetailQuery);

    List<WeContentSendViewDto> selectSendTotalNumGroupByContentId();


    List<ContentDataDetailVo> findContentDataDetailVo(@Param("contentId") Long contentId,@Param("talkId") Long talkId,@Param("startTime") String startTime,@Param("endTime") String endTime);

}
