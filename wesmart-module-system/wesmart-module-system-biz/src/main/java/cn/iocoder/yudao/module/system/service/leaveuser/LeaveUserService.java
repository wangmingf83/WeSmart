package cn.iocoder.yudao.module.system.service.leaveuser;

import java.util.*;

import cn.iocoder.yudao.module.system.api.user.dto.LeaveUserDTO;
import cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo.LeaveUserPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo.LeaveUserSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.leaveuser.LeaveUserDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 员工离职 Service 接口
 *
 * @author 圭图助手
 */
public interface LeaveUserService {

    /**
     * 创建员工离职
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLeaveUser(@Valid LeaveUserSaveReqVO createReqVO);

    /**
     * 更新员工离职
     *
     * @param updateReqVO 更新信息
     */
    void updateLeaveUser(@Valid LeaveUserSaveReqVO updateReqVO);

    /**
     * 删除员工离职
     *
     * @param id 编号
     */
    void deleteLeaveUser(Long id);

    /**
     * 获得员工离职
     *
     * @param id 编号
     * @return 员工离职
     */
    LeaveUserDO getLeaveUser(Long id);

    LeaveUserDTO findLeaveUserByWeUserId(String weUserId);

    /**
     * 获得员工离职分页
     *
     * @param pageReqVO 分页查询
     * @return 员工离职分页
     */
    PageResult<LeaveUserDO> getLeaveUserPage(LeaveUserPageReqVO pageReqVO);

}