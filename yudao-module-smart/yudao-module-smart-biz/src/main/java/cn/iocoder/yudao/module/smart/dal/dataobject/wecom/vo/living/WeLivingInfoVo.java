package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 创建直播返回对象
 * @date 2022年10月11日 16:09
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeLivingInfoVo extends WeResultVo {

    /**
     * 直播ID列表
     */
    @Schema(description = "直播ID列表")
    private LivingInfo livingInfo;


    @Schema
    @Data
    public static class LivingInfo {

        /**
         * 直播主题
         */
        @Schema(description = "直播主题")
        private String theme;

        /**
         * 直播开始时间戳
         */
        @Schema(description = "直播开始时间戳")
        private Long livingStart;

        /**
         * 直播时长，单位为秒
         */
        @Schema(description = "直播时长，单位为秒")
        private Integer livingDuration;

        /**
         * 直播的状态，0：预约中，1：直播中，2：已结束，3：已过期，4：已取消
         */
        @Schema(description = "直播的状态，0：预约中，1：直播中，2：已结束，3：已过期，4：已取消")
        private Integer status;

        /**
         * 直播预约的开始时间戳
         */
        @Schema(description = "直播预约的开始时间戳")
        private Long reserveStart;

        /**
         * 直播预约时长，单位为秒
         */
        @Schema(description = "直播预约时长，单位为秒")
        private Long reserveLivingDuration;

        /**
         * 直播的描述，最多支持100个汉字
         */
        @Schema(description = "直播的描述，最多支持100个汉字")
        private String description;

        /**
         * 	主播的userid
         */
        @Schema(description = "主播的userid")
        private String anchorUserId;

        /**
         * 	主播所在主部门id
         */
        @Schema(description = "主播所在主部门id")
        private Integer mainDepartment;

        /**
         * 	观看直播总人数
         */
        @Schema(description = "观看直播总人数")
        private Integer viewerNum;

        /**
         * 	评论数
         */
        @Schema(description = "评论数")
        private Integer commentNum;

        /**
         * 	连麦发言人数
         */
        @Schema(description = "连麦发言人数")
        private Integer micNum;

        /**
         * 	是否开启回放，1表示开启，0表示关闭
         */
        @Schema(description = "是否开启回放，1表示开启，0表示关闭")
        private Integer openReplay;

        /**
         * 	open_replay为1时才返回该字段。0表示生成成功，1表示生成中，2表示回放已删除，3表示生成失败
         */
        @Schema(description = "open_replay为1时才返回该字段。0表示生成成功，1表示生成中，2表示回放已删除，3表示生成失败")
        private Integer replayStatus;

        /**
         * 	直播的类型，0：通用直播，1：小班课，2：大班课，3：企业培训，4：活动直播
         */
        @Schema(description = "直播的类型，0：通用直播，1：小班课，2：大班课，3：企业培训，4：活动直播")
        private Integer type;

        /**
         * 	推流地址，仅直播类型为活动直播并且直播状态是待开播返回该字段
         */
        @Schema(description = "推流地址，仅直播类型为活动直播并且直播状态是待开播返回该字段")
        private String pushStreamUrl;

        /**
         * 当前在线观看人数
         */
        @Schema(description = "当前在线观看人数")
        private Integer onlineCount;

        /**
         * 直播预约人数
         */
        @Schema(description = "直播预约人数")
        private Integer subscribeCount;
    }
}
