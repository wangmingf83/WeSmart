package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.state;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author danmo
 */
@Data
public class WeUserBehaviorDataVo extends WeResultVo {

    private List<BehaviorData> behaviorData;

    @Data
    public static class BehaviorData {

        /**
         * 数据日期，为当日0点的时间戳
         */
        @JSONField(format="unixtime")
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        private Date statTime;

        /**
         * 聊天总数， 成员有主动发送过消息的单聊总数。
         */
        private Integer chatCnt = 0;
        /**
         * 发送消息数，成员在单聊中发送的消息总数。
         */
        private Integer messageCnt = 0;
        /**
         * 已回复聊天占比，浮点型，客户主动发起聊天后，成员在一个自然日内有回复过消息的聊天数/客户主动发起的聊天数比例，不包括群聊，仅在确有聊天时返回。
         */
        private Float replyPercentage = 0.00f;
        /**
         * 平均首次回复时长，单位为分钟，即客户主动发起聊天后，成员在一个自然日内首次回复的时长间隔为首次回复时长，所有聊天的首次回复总时长/已回复的聊天总数即为平均首次回复时长，不包括群聊，仅在确有聊天时返回。
         */
        private Integer avgReplyTime = 0;
        /**
         * 删除/拉黑成员的客户数，即将成员删除或加入黑名单的客户数。
         */
        private Integer negativeFeedbackCnt = 0;
        /**
         * 主动向客户发起的好友申请数量
         */
        private Integer newApplyCnt = 0;
        /**
         * 新增客户数，成员新添加的客户数量。
         */
        private Integer newContactCnt = 0;

    }
}
