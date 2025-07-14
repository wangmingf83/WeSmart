package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfEventMsg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 客服事件消息表(WeKfEventMsg)
 *
 * @author danmo
 * @since 2022-04-15 15:53:34
 */
@Repository()
@Mapper
public interface WeKfEventMsgMapper extends BaseMapper<WeKfEventMsg> {


}

