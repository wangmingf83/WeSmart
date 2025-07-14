package cn.iocoder.yudao.module.system.service.leaveuser;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.system.api.user.dto.LeaveUserDTO;
import cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo.LeaveUserPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo.LeaveUserSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.dict.DictDataDO;
import cn.iocoder.yudao.module.system.dal.dataobject.leaveuser.LeaveUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.leaveuser.LeaveUserMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.LEAVE_USER_NOT_EXISTS;


/**
 * 员工离职 Service 实现类
 *
 * @author 圭图助手
 */
@Service("leaveUserService")
@Validated
public class LeaveUserServiceImpl implements LeaveUserService {

    @Resource
    private LeaveUserMapper leaveUserMapper;

    @Override
    public Long createLeaveUser(LeaveUserSaveReqVO createReqVO) {
        // 插入
        LeaveUserDO leaveUser = BeanUtils.toBean(createReqVO, LeaveUserDO.class);
        leaveUserMapper.insert(leaveUser);
        // 返回
        return leaveUser.getId();
    }

    @Override
    public void updateLeaveUser(LeaveUserSaveReqVO updateReqVO) {
        // 校验存在
        validateLeaveUserExists(updateReqVO.getId());
        // 更新
        LeaveUserDO updateObj = BeanUtils.toBean(updateReqVO, LeaveUserDO.class);
        leaveUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteLeaveUser(Long id) {
        // 校验存在
        validateLeaveUserExists(id);
        // 删除
        leaveUserMapper.deleteById(id);
    }

    private void validateLeaveUserExists(Long id) {
        if (leaveUserMapper.selectById(id) == null) {
            throw exception(LEAVE_USER_NOT_EXISTS);
        }
    }

    @Override
    public LeaveUserDO getLeaveUser(Long id) {
        return leaveUserMapper.selectById(id);
    }

    @Override
    public PageResult<LeaveUserDO> getLeaveUserPage(LeaveUserPageReqVO pageReqVO) {
        return leaveUserMapper.selectPage(pageReqVO);
    }

    public LeaveUserDTO findLeaveUserByWeUserId(String weUserId) {
        LeaveUserDO leaveuser = leaveUserMapper.selectOne(new LambdaQueryWrapperX<LeaveUserDO>()
                .eq(LeaveUserDO::getWeUserId, weUserId));
        return BeanUtils.toBean(leaveuser, LeaveUserDTO.class);
    }

}