package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupRobotMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotMsgAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotMsgListQuery;

import java.util.List;

/**
 * 群机器人消息表(WeGroupRobotMsg)
 *
 * @author danmo
 * @since 2022-11-08 16:06:01
 */
public interface IWeGroupRobotMsgService extends IService<WeGroupRobotMsg> {

    /**
     * 获取消息列表
     * @param query
     * @return
     */
    List<WeGroupRobotMsg> getMsgList(WeRobotMsgListQuery query);

    /**
     * 发送机器人消息
     * @param query
     */
    void addRobotMsg(WeRobotMsgAddQuery query);
}
