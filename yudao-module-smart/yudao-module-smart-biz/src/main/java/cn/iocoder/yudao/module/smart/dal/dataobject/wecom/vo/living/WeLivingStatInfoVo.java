package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @description 统计信息列表返回对象
 * @date 2022年10月11日 16:09
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeLivingStatInfoVo extends WeResultVo {

    /**
     * 是否结束。0：表示还有更多数据，需要继续拉取，1：表示已经拉取完所有数据。注意只能根据该字段判断是否已经拉完数据
     */
    @Schema(description = "是否结束。0：表示还有更多数据，需要继续拉取，1：表示已经拉取完所有数据。注意只能根据该字段判断是否已经拉完数据")
    private Integer ending;

    /**
     * 当前数据最后一个key值，如果下次调用带上该值则从该key值往后拉，用于实现分页拉取
     */
    @Schema(description = "当前数据最后一个key值，如果下次调用带上该值则从该key值往后拉，用于实现分页拉取")
    private String nextKey;

    /**
     * 统计信息列表
     */
    @Schema(description = "统计信息列表")
    private LivingStatInfo statInfo;

    @Schema
    @Data
    public static class LivingStatInfo{

        @Schema(description = "观看直播的企业成员列表")
        private List<LivingUser> users;

        @Schema(description = "观看直播的外部成员列表")
        private List<LivingExternalUsers> externalUsers;
    }

    @Schema
    @Data
    public static class LivingUser{

        /**
         * 企业成员的userid
         */
        @Schema(description = "企业成员的userid")
        private String userid;

        /**
         * 观看时长，单位为秒
         */
        @Schema(description = "观看时长，单位为秒")
        private Long watchTime;

        /**
         * 是否评论。0-否；1-是
         */
        @Schema(description = "是否评论。0-否；1-是")
        private Integer isComment;

        /**
         * 是否连麦发言。0-否；1-是
         */
        @Schema(description = "是否连麦发言。0-否；1-是")
        private Integer isMic;

        /**
         * 邀请人的userid
         */
        @Schema(description = "邀请人的userid")
        private String invitorUserId;

        /**
         * 邀请人的external_userid
         */
        @Schema(description = "邀请人的external_userid")
        private String invitorExternalUserId;

    }

    @Schema
    @Data
    public static class LivingExternalUsers{

        /**
         * 外部成员的userid
         */
        @Schema(description = "外部成员的userid")
        private String externalUserId;

        /**
         * 外部成员类型，1表示该外部成员是微信用户，2表示该外部成员是企业微信用户
         */
        @Schema(description = "外部成员类型，1表示该外部成员是微信用户，2表示该外部成员是企业微信用户")
        private Integer type;

        /**
         * 外部成员的名称
         */
        @Schema(description = "外部成员的名称")
        private String name;

        /**
         * 观看时长，单位为秒
         */
        @Schema(description = "观看时长，单位为秒")
        private Long watchTime;

        /**
         * 是否评论。0-否；1-是
         */
        @Schema(description = "是否评论。0-否；1-是")
        private Integer isComment;

        /**
         * 是否连麦发言。0-否；1-是
         */
        @Schema(description = "是否连麦发言。0-否；1-是")
        private Integer isMic;

        /**
         * 邀请人的userid，邀请人为企业内部成员时返回（观众首次进入直播时，其使用的直播卡片/二维码所对应的分享人；仅“推广产品”直播支持）
         */
        @Schema(description = "邀请人的userid，邀请人为企业内部成员时返回（观众首次进入直播时，其使用的直播卡片/二维码所对应的分享人；仅“推广产品”直播支持）")
        private String invitor_userid;

        /**
         * 邀请人的external_userid，邀请人为非企业内部成员时返回（观众首次进入直播时，其使用的直播卡片/二维码所对应的分享人；仅“推广产品”直播支持）
         */
        @Schema(description = "邀请人的external_userid，邀请人为非企业内部成员时返回（观众首次进入直播时，其使用的直播卡片/二维码所对应的分享人；仅“推广产品”直播支持）")
        private String invitor_external_userid;
    }
}
