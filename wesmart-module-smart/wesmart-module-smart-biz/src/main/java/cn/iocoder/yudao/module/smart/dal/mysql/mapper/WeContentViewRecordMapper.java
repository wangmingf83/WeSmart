package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeContentViewRecord;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.ContentDetailQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.ContentDataDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface WeContentViewRecordMapper extends BaseMapper<WeContentViewRecord> {

    /**
     * 获取查看记录
     * <p>
     * 2022-11-15
     * 最开始用external_userid进行查询，后面按照需求用unionid进行查询
     *
     * @param query
     * @return
     */
    List<WeContentViewRecord> getList(ContentDetailQuery query);


    /**
     * 获取轨迹素材客户查看明细
     * @return
     */
    List<ContentDataDetailVo>  findContentDataDetailVos(@Param("contentId") String content_id,@Param("startTime") String startTime,@Param("endTime") String endTime);

}
