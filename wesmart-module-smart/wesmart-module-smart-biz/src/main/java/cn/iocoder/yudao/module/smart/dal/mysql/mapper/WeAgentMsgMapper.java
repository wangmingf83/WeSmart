package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.query.WeAgentMsgListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.vo.WeAgentMsgListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.vo.WeAgentMsgVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeAgentMsg;

/**
 * 应用消息表(WeAgentMsg)
 *
 * @author danmo
 * @since 2022-11-04 17:08:08
 */
@Repository()
@Mapper
public interface WeAgentMsgMapper extends BaseMapper<WeAgentMsg> {


    WeAgentMsgVo getMsgInfo(@Param("id") Long id);

    List<WeAgentMsgListVo> getMsgList(WeAgentMsgListQuery query);
}

