package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageTask;

/**
 * 群发消息成员发送任务表(WeGroupMessageTask)
 *
 * @author danmo
 * @since 2022-04-06 22:29:05
 */
@Repository()
@Mapper
public interface WeGroupMessageTaskMapper extends BaseMapper<WeGroupMessageTask> {

    List<WeGroupMessageTask> groupMsgTaskList(WeGroupMessageTask task);
}

