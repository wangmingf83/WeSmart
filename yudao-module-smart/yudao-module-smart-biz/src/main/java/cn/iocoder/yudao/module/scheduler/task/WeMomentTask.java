package cn.iocoder.yudao.module.scheduler.task;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeMomentsTaskRelationService;
import cn.iocoder.yudao.module.smart.service.IWeMomentsTaskService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.enums.LockEnums;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.dto.MomentsListDetailParamDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.dto.MomentsListDetailResultDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsTask;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsTaskRelation;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsJobIdToMomentsIdRequest;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 朋友圈相关定时任务
 *
 * @author danmo
 * @date 2023/04/18 11:22
 **/
@Component
@Slf4j
public class WeMomentTask {

    @Autowired
    private IWeMomentsTaskService weMomentsTaskService;

    @Autowired
    private IWeMomentsTaskRelationService iWeMomentsTaskRelationService;

     @Autowired
     private RedissonClient redissonClient;

    /**
     * 发送朋友圈
     */
    @XxlJob("sendWeMomentHandle")
    public void sendWeMomentHandle(){
        RLock lock = redissonClient.getLock(LockEnums.WE_MOMENTS_SEND_LOCK.getCode());
        if(lock.tryLock()){
            try {
                //需要指定的朋友圈
                List<WeMomentsTask> weMomentsTasks = weMomentsTaskService.list(new LambdaQueryWrapper<WeMomentsTask>()
                        .and(i->i.isNull(WeMomentsTask::getExecuteTime).or()
                                .apply("date_format (execute_time,'%Y-%m-%d %H:%i') <= date_format ({0},'%Y-%m-%d %H:%i')",new Date()))
                        .and(i->i.eq(WeMomentsTask::getStatus,1)));
                if(CollectionUtil.isNotEmpty(weMomentsTasks)){
                    weMomentsTasks.stream().forEach(k->{
                        weMomentsTaskService.immediatelySendMoments(k);
                    });
                }

                //通过jobId换取momentsId
                List<WeMomentsTaskRelation> weMomentsTaskRelations = iWeMomentsTaskRelationService.list(new LambdaQueryWrapper<WeMomentsTaskRelation>()
                        .isNotNull(WeMomentsTaskRelation::getJobId)
                        .isNull(WeMomentsTaskRelation::getMomentId));
                if(CollectionUtil.isNotEmpty(weMomentsTaskRelations)){
                    weMomentsTaskService.jobIdToMomentsId(
                            weMomentsTaskRelations
                    );
                }

                //到期结束任务
//             weMomentsTaskService.update(WeMomentsTask.builder().status(3).build(),new LambdaQueryWrapper<WeMomentsTask>()
//                        .apply("date_format (execute_end_time,'%Y-%m-%d %H:%i') <= date_format ({0},'%Y-%m-%d %H:%i')",new Date()));
                List<WeMomentsTask> expiredWeMomentsTasks = weMomentsTaskService.list(new LambdaQueryWrapper<WeMomentsTask>()
                        .eq(WeMomentsTask::getStatus,2)
                        .apply("date_format (execute_end_time,'%Y-%m-%d %H:%i') <= date_format ({0},'%Y-%m-%d %H:%i')", new Date()));

                if(CollectionUtil.isNotEmpty(expiredWeMomentsTasks)){
                    expiredWeMomentsTasks.stream().forEach(k->{

                        weMomentsTaskService.cancelSendMoments(k.getId());
                    });

                }



            }finally {
               lock.unlock();
            }
        }
    }



}
