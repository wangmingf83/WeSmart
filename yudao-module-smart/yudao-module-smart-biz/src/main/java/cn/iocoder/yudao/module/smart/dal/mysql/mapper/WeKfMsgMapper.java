package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.query.WeKfRecordQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo.WeKfRecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客服消息表(WeKfMsg)
 *
 * @author danmo
 * @since 2022-04-15 15:53:36
 */
@Repository()
@Mapper
public interface WeKfMsgMapper extends BaseMapper<WeKfMsg> {

    /**
     * 会话记录详情
     * @param query
     * @return
     */
    List<WeKfRecordVo> getRecordDetail(WeKfRecordQuery query);
}

