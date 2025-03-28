package cn.iocoder.yudao.module.system.dal.dataobject.user;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import cn.iocoder.yudao.module.system.enums.common.SexEnum;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

/**
 * 管理后台的用户 DO
 *
 * @author 芋道源码
 */
@TableName(value = "system_users", autoResultMap = true) // 由于 SQL Server 的 system_user 是关键字，所以使用 system_users
@KeySequence("system_users_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDO extends TenantBaseDO {

    /**
     * 用户ID
     */
    @TableId
    private Long id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 加密后的密码
     *
     * 因为目前使用 {@link BCryptPasswordEncoder} 加密器，所以无需自己处理 salt 盐
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 备注
     */
    private String remark;
    /**
     * 部门 ID
     */
    private Long deptId;
    /**
     * 岗位编号数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Set<Long> postIds;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户性别
     *
     * 枚举类 {@link SexEnum}
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 帐号状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 当前微信用户对应微信端的id
     */
    private String weUserId;

    private String corpId;

    /**
     * 直属上级
     */
    private String leader;

    /**
     * 企微用户激活状态(1=已激活，2=已禁用，4=未激活，5=退出企业)
     */
    private Integer weUserStatus;

    /**
     * 是否离职1:是；0:否
     */
    private Integer isUserLeave;

    /**
     * 是否开启会话存档 0-未开启 1-开启
     */
    private Integer isOpenChat;

    /**
     * 离职时间
     */
    private Date dimissionTime;

    /**
     * 离职状态员工，数据分配状态:0:未分配;1:已分配
     */
    private Integer isAllocate;

    /**
     * 是否开启动态日报 0开启，1关闭 默认开启0
     */
    private Integer openDaily;

    /**
     * 客服接待状态。1:接待中,2:停止接待
     */
    private Integer kfStatus;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 企业邮箱
     */
    private String bizMail;

    /**
     * 座机号码
     */
    private String telephone;

    /**
     * 员工个人二维码
     */
    private String qrCode;

    /**
     * 成员对外属性
     */
    private String externalProfile;

    /**
     * 对外职务
     */
    private String externalPosition;

    /**
     * 地址
     */
    private String address;

    /**
     * open_userid
     */
    private String openUserid;

    /**
     * 对应mainDepartmentName
     */
    @TableField(exist = false)
    private String deptName;

    private String thumbAvatar;

    private String extAttr;

}
