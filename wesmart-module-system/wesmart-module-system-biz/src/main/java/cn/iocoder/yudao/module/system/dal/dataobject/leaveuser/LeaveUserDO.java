package cn.iocoder.yudao.module.system.dal.dataobject.leaveuser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 员工离职 DO
 *
 * @author 圭图助手
 */
@TableName("sys_leave_user")
@KeySequence("sys_leave_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveUserDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 离职员工名称
     */
    private String userName;
    /**
     * 离职员工所在部门名称
     */
    private String deptNames;
    /**
     * 员工id
     */
    private String weUserId;
    /**
     * 分配客户数
     */
    private Integer allocateCustomerNum;
    /**
     * 分配群数
     */
    private Integer allocateGroupNum;
    /**
     * 离职时间
     */
    private LocalDateTime dimissionTime;
    /**
     * 是否已分配:1:是;0:否
     */
    private Integer isAllocate;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建人id
     */
    private Long createById;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新人id
     */
    private Long updateById;
    /**
     * 0:正常;1:删除;
     */
    private Boolean delFlag;

}