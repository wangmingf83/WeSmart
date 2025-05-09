package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageSendResult;

import java.util.List;

/**
 * 群发消息成员执行结果表(WeGroupMessageSendResult)
 *
 * @author danmo
 * @since 2022-04-06 22:29:04
 */
public interface IWeGroupMessageSendResultService extends IService<WeGroupMessageSendResult> {

    /**
     * 查询列表
     */
    List<WeGroupMessageSendResult> queryList(WeGroupMessageSendResult weGroupMessageSendResult);

    void addOrUpdateBatchByCondition(List<WeGroupMessageSendResult> taskList);

    List<WeGroupMessageSendResult> groupMsgSendResultList(WeGroupMessageSendResult sendResult);
} 
