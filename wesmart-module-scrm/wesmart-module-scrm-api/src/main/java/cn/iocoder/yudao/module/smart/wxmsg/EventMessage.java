package cn.iocoder.yudao.module.smart.wxmsg;

import lombok.Data;

/**
 * 事件消息
 */
@Data
public class EventMessage {
    // 用户进入会话事件
    private String event_type; // 事件类型。此处固定为：enter_session
    private String open_kfid; // 客服账号ID
    private String external_userid; // 客户UserID
    private String scene; // 进入会话的场景值，获取客服账号链接开发者自定义的场景值
    private String scene_param; // 进入会话的自定义参数，获取客服账号链接返回的url，开发者按规范拼接的scene_param参数
    private String welcome_code; // 如果满足发送欢迎语条件（条件为：用户在过去48小时里未收过欢迎语，且未向客服发过消息），会返回该字段。可用该welcome_code调用发送事件响应消息接口给客户发送欢迎语。
    private WechatChannels wechatChannels; // 进入会话的视频号信息，从视频号进入会话才有值
    // 消息发送失败事件。此处事件类型固定为：msg_send_fail
    private String fail_msgid; // 发送失败的消息msgid
    private Integer fail_type; // 失败类型。0-未知原因 1-客服账号已删除 2-应用已关闭 4-会话已过期，超过48小时 5-会话已关闭 6-超过5条限制 8-主体未验证 10-用户拒收 11-企业未有成员登录企业微信App（排查方法：企业至少一个成员通过手机号验证/微信授权登录企业微信App即可）12-发送的消息为客服组件禁发的消息类型 13-安全限制

    // 接待人员接待状态变更事件 此处事件类型固定为：servicer_status_change
    private String servicer_userid; // 接待人员userid
    private Integer status; // 状态类型。1-接待中 2-停止接待
    private Integer stop_type; // 接待人员的状态为「停止接待」的子类型。0:停止接待,1:暂时挂起

    // 会话状态变更事件 此处事件类型固定为：session_status_change
    private Integer change_type; // 变更类型，均为接待人员在企业微信客户端操作触发。1-从接待池接入会话 2-转接会话 3-结束会话 4-重新接入已结束/已转接会话
    private String old_servicer_userid; // 老的接待人员userid。仅change_type为2、3和4有值
    private String new_servicer_userid; // 新的接待人员userid。仅change_type为1、2和4有值
    private String msg_code; // 用于发送事件响应消息的code，仅change_type为1和3时，会返回该字段。可用该msg_code调用发送事件响应消息接口给客户发送回复语或结束语。

    // 用户撤回消息事件 此处事件类型固定为：user_recall_msg
    private String recall_msgid; // 撤回的消息msgid

    // 拒收客户消息变更事件 此处事件类型固定为：reject_customer_msg_switch_change
    private Integer rejectSwitch; // 拒收客户消息，1表示接待人员拒收了客户消息，0表示接待人员取消拒收客户消息

    @Data
    private static class WechatChannels {
        private String nickname; // 视频号名称，视频号场景值为1、2、3时返回此项
        private String shop_nickname; // 视频号小店名称，视频号场景值为4、5时返回此项
        private Integer scene; // 视频号场景值。1：视频号主页，2：视频号直播间商品列表页，3：视频号商品橱窗页，4：视频号小店商品详情页，5：视频号小店订单页
    }


}
