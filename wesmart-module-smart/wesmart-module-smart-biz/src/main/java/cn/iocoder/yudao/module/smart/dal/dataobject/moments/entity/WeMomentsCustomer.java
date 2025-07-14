package cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 朋友圈可见客户
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/07 10:00
 */
@Schema(description = "朋友圈可见客户")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("we_moments_customer")
public class WeMomentsCustomer extends BaseEntity {

    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 朋友圈任务id
     */
    private Long momentsTaskId;

    /**
     * 朋友圈id
     */
    @TableField(insertStrategy = FieldStrategy.IGNORED)
    private String momentsId;

    /**
     * 员工id
     */
    @TableField(insertStrategy = FieldStrategy.IGNORED)
    private Long userId;

    /**
     * 企微员工id
     */
    private String weUserId;

    /**
     * 员工名称
     */
    private String userName;

    /**
     * 客户id
     */
    private String externalUserid;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 送达状态 0已送达 1未送达
     */
    private Integer deliveryStatus;

    /**
     * 删除标识 0:正常 1:删除
     */
    @TableField(insertStrategy = FieldStrategy.IGNORED)
    private Integer delFlag;

}
