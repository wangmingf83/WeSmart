package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeGroupMessageTaskService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupMessageTaskMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageTask;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 群发消息成员发送任务表(WeGroupMessageTask)
 *
 * @author danmo
 * @since 2022-04-06 22:29:05
 */
@Slf4j
@Service
public class WeGroupMessageTaskServiceImpl extends ServiceImpl<WeGroupMessageTaskMapper, WeGroupMessageTask> implements IWeGroupMessageTaskService {


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOrUpdateBatchByCondition(List<WeGroupMessageTask> taskList) {
        if (CollectionUtil.isNotEmpty(taskList)) {
            taskList.forEach(item -> {
                LambdaQueryWrapper<WeGroupMessageTask> wrapper = new LambdaQueryWrapper<>();
                if(item.getMsgTemplateId() != null){
                    wrapper.eq(WeGroupMessageTask::getMsgTemplateId,item.getMsgTemplateId());
                }
                wrapper.eq(WeGroupMessageTask::getUserId, item.getUserId());
                if (!this.update(item,wrapper)){
                    this.save(item);
                }
            });
        }
    }

    @Override
    public List<WeGroupMessageTask> groupMsgTaskList(WeGroupMessageTask task) {
        return this.baseMapper.groupMsgTaskList(task);
    }
}
