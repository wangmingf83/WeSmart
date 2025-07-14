package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.WePresTagGroupTask;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.query.WePresTagGroupTaskQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.vo.*;

import java.util.List;

/**
 * 社区运营 老客户标签建群 相关逻辑
 */
public interface IWePresTagGroupTaskService extends IService<WePresTagGroupTask> {

    /**
     * 添加新标签建群任务
     *
     * @param task 建群任务本体信息
     * @return 结果
     */
    void add(WePresTagGroupTask task);

    /**
     * 根据条件查询任务列表
     *
     * @param groupTask  任务名称
     * @return 结果
     */
    List<WePresTagGroupTask> selectTaskList(WePresTagGroupTask groupTask);

    /**
     * 通过id获取老客标签建群任务
     *
     * @param taskId 任务id
     * @return 结果
     */
    WePresTagGroupTask getTaskById(Long taskId);

    /**
     * 批量删除老客标签建群任务
     *
     * @param idList 任务id列表
     * @return 删除的行数
     */
    void batchRemoveTaskByIds(Long[] idList);

    /**
     * 更新老客户标签建群任务
     *
     * @param task 待更新任务
     * @return 更新条数
     */
    void updateTask(WePresTagGroupTask task);


    /**
     * 获取头部统计
     * @param id
     * @return
     */
    WePresTagGroupTaskTabCountVo countTab(String id);



    /**
     * 获取头部统计
     * @param task
     * @return
     */
    List<WePresTagGroupTaskTrendCountVo> findTrendCountVo(WePresTagGroupTask task);



    /**
     * 获取相关客户
     * @param wePresTagGroupTaskQuery
     * @return
     */
    List<WePresTagGroupTaskTableVo> findWePresTagGroupTaskTable(WePresTagGroupTaskQuery wePresTagGroupTaskQuery);


    /**
     * 同步发送结果
     * @param id
     */
     void synchExecuteResult(String id);


//    void updateTaskAndSendMsg(WePresTagGroupTask task) throws WeComException;
//
//    /**
//     * 根据查询条件获取任务的统计结果
//     *
//     * @param id           任务id
//     * @param customerName 客户名
//     * @param isInGroup    是否在群
//     * @param isSent       是否送达
//     * @param sendType     发送类型 0 企业群发 1 个人群发
//     * @return 统计结果
//     */
//    List<WePresTagGroupTaskStat> getTaskStat(Long id, String customerName, Integer isInGroup, Integer isSent,
//                                             Integer sendType);
//
//    /**
//     * 根据任务id获取对应员工信息列表
//     *
//     * @param taskId 任务id
//     * @return 结果
//     */
//    List<WeCommunityTaskEmplVo> getScopeListByTaskId(Long taskId);
//
//    /**
//     * 获取员工建群任务信息
//     *
//     * @param emplId 员工id
//     * @param isDone 是否已处理
//     * @return 结果
//     */
//    List<WePresTagGroupTaskVo> getFollowerTaskList(String emplId, Integer isDone);
//
//    /**
//     * 员工在H5任务页面发送信息后，变更其任务状态为 "完成"
//     *
//     * @param taskId     任务id
//     * @param followerId 跟进者id
//     * @return 结果
//     */
//    int updateFollowerTaskStatus(Long taskId, String followerId);
//
//    /**
//     * 根据标签建群任务信息发送消息
//     *
//     * @param task 标签建群任务id
//     */
//    void sendMessage(WePresTagGroupTask task) throws WeComException;
//
//    /**
//     * 任务名是否已占用
//     *
//     * @param task 任务信息
//     * @return 名称是否占用
//     */
//    boolean isNameOccupied(WePresTagGroupTask task);
//
//    /**
//     * 获取外部联系人id, 用于企业群发的 external_userid参数
//     *
//     * @param taskId 任务id
//     * @return 外部联系人id
//     */
//    List<String> selectExternalIds(Long taskId);
//
//    /**
//     * 获取某个任务选择范围内客户对应的员工id， 用于个人群发的touser参数
//     *
//     * @param taskId 任务id
//     * @return 员工id列表
//     */
//    List<String> selectFollowerIdByTaskId(Long taskId);


}
