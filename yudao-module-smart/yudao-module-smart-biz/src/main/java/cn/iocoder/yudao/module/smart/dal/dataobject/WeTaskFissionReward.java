package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;


import lombok.Data;

/**
 * 任务裂变奖励(WeTaskFissionReward)
 *
 * @author danmo
 * @since 2022-06-28 13:48:55
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_task_fission_reward")
public class WeTaskFissionReward extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 活动奖励主键
     */
    @Schema(description = "活动奖励主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 任务裂变id
     */
    @Schema(description = "任务裂变id")
    @TableField("task_fission_id")
    private Long taskFissionId;


    /**
     * 兑奖码
     */
    @Schema(description = "兑奖码")
    @TableField("reward_code")
    private String rewardCode;


    /**
     * 兑奖码状态，0 未使用 1 已使用
     */
    @Schema(description = "兑奖码状态，0 未使用 1 已使用")
    @TableField("reward_code_status")
    private Integer rewardCodeStatus;


    /**
     * 兑奖用户id
     */
    @Schema(description = "兑奖用户id")
    @TableField("reward_user_id")
    private String rewardUserId;


    /**
     * 兑奖人姓名
     */
    @Schema(description = "兑奖人姓名")
    @TableField("reward_user")
    private String rewardUser;



    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
