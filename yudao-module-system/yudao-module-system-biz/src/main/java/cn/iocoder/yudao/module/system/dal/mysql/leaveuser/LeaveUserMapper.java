package cn.iocoder.yudao.module.system.dal.mysql.leaveuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo.LeaveUserPageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.leaveuser.LeaveUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工离职 Mapper
 *
 * @author 圭图助手
 */
@Mapper
public interface LeaveUserMapper extends BaseMapperX<LeaveUserDO> {

    default PageResult<LeaveUserDO> selectPage(LeaveUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LeaveUserDO>()
                .likeIfPresent(LeaveUserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(LeaveUserDO::getDeptNames, reqVO.getDeptNames())
                .eqIfPresent(LeaveUserDO::getWeUserId, reqVO.getWeUserId())
                .eqIfPresent(LeaveUserDO::getAllocateCustomerNum, reqVO.getAllocateCustomerNum())
                .eqIfPresent(LeaveUserDO::getAllocateGroupNum, reqVO.getAllocateGroupNum())
                .betweenIfPresent(LeaveUserDO::getDimissionTime, reqVO.getDimissionTime())
                .eqIfPresent(LeaveUserDO::getIsAllocate, reqVO.getIsAllocate())
                .eqIfPresent(LeaveUserDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(LeaveUserDO::getCreateById, reqVO.getCreateById())
                .betweenIfPresent(LeaveUserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(LeaveUserDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(LeaveUserDO::getUpdateById, reqVO.getUpdateById())
                .eqIfPresent(LeaveUserDO::getDelFlag, reqVO.getDelFlag())
                .orderByDesc(LeaveUserDO::getId));
    }

}