package cn.iocoder.yudao.module.smart.dal.dataobject.system.user.query;


import cn.iocoder.yudao.module.smart.common.ScrmSysUser;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author danmo
 * @date 2022年11月29日 12:13
 */
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserQuery {

    private String corpId;

    @Schema(description = "员工ID")
    private Long userId;

    @Schema(description = "企微员工ID")
    private String weUserId;

    @Schema(description = "员工ID")
    private List<Long> userIds;


    @Schema(description = "员工名称")
    private String userName;


    @Schema(description = "企微员工ID")
    private List<String> weUserIds;

    @Schema(description = "部门ID")
    private List<Long> deptIds;

    @Schema(description = "角色ID")
    private List<Long> roleIds;

    @Schema(description = "职位")
    private List<String> positions;

    @Schema(description = "员工状态 1=已激活，2=已禁用，4=未激活，5=退出企业")
    private Integer weUserStatus;

    @Schema(description = "职状态员工，数据分配状态:0:未分配;1:已分配")
    private Integer isAllocate;

    @Schema(description = "是否开启会话存档 0-未开启 1-开启")
    private Integer isOpenChat;

    @Schema(description = "客服接待状态。1:接待中,2:停止接待")
    private Integer kfStatus;

    @Schema(description = "性别（0男 1女 2未知）")
    private Integer sex;


    private Integer isUserLeave;

    private List<ScrmSysUser> sysUsers;

}
