package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageSendResult;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageTask;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query.WeAddGroupMessageQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.vo.WeGroupMessageDetailVo;

import java.util.List;

/**
 * 群发消息模板(WeGroupMessageTemplate)
 *
 * @author danmo
 * @since 2022-04-06 22:29:06
 */
public interface IWeGroupMessageTemplateService extends IService<WeGroupMessageTemplate> {

    /**
     * 查询列表
     */
    List<WeGroupMessageTemplate> queryList(WeGroupMessageTemplate weGroupMessageTemplate);

    /**
     * 获取群发消息详情
     * @param id 模板id
     * @return
     */
    WeGroupMessageDetailVo getGroupMsgTemplateDetail(Long id);

    /**
     * 创建群发消息
     * @param query
     */
    void addGroupMsgTemplate(WeAddGroupMessageQuery query);

    /**
     * 取消定时发送
     * @param asList
     */
    void cancelByIds(List<Long> asList);

    /**
     * 同步消息发送结果
     * @param asList
     */
    void syncGroupMsgSendResultByIds(List<Long> asList);



    /**
     * 群发成员发送任务列表
     * @param task
     */
    List<WeGroupMessageTask> groupMsgTaskList(WeGroupMessageTask task);

    /**
     * 企业群发成员执行结果
     * @param sendResult
     */
    List<WeGroupMessageSendResult> groupMsgSendResultList(WeGroupMessageSendResult sendResult);

    /**
     * 群发消息处理
     * @param query
     */
    void groupMessageTaskHandler(WeAddGroupMessageQuery query);


    /**
     * 根据群发id获取群发内容明细数据
     * @param id
     * @return
     */
    WeAddGroupMessageQuery findGroupMessageDetail(Long id);

}
