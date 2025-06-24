package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeGroupRobotInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;

import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupRobotInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotQuery;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupRobotInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 群机器人信息表(WeGroupRobotInfo)
 *
 * @author danmo
 * @since 2022-11-08 16:06:01
 */
@Service
public class WeGroupRobotInfoServiceImpl extends ServiceImpl<WeGroupRobotInfoMapper, WeGroupRobotInfo> implements IWeGroupRobotInfoService {

    @Override
    public Long addGroupRobot(WeRobotAddQuery query) {
        WeGroupRobotInfo robotInfo = new WeGroupRobotInfo();
        robotInfo.setGroupName(query.getGroupName());
        robotInfo.setWebHookUrl(query.getWebHookUrl());
        save(robotInfo);
        return robotInfo.getId();
    }

    @Override
    public void updateRobot(WeRobotAddQuery query) {
        update(new LambdaUpdateWrapper<WeGroupRobotInfo>()
                .set(StringUtils.isNotEmpty(query.getGroupName()),WeGroupRobotInfo::getGroupName,query.getGroupName())
                .set(StringUtils.isNotEmpty(query.getWebHookUrl()),WeGroupRobotInfo::getWebHookUrl,query.getWebHookUrl())
                .eq(WeGroupRobotInfo::getId,query.getId()));
    }

    @Override
    public List<WeGroupRobotInfo> getList(WeRobotQuery query) {
        return list(new LambdaQueryWrapper<WeGroupRobotInfo>()
                .like(StringUtils.isNotEmpty(query.getGroupName()),WeGroupRobotInfo::getGroupName, query.getGroupName())
                .ge(Objects.nonNull(query.getStartTime()),BaseEntity::getCreateTime,query.getStartTime())
                .le(Objects.nonNull(query.getEndTime()),BaseEntity::getCreateTime,query.getEndTime())
                .eq(WeGroupRobotInfo::getDelFlag,0));
    }

    @Override
    public void deleteGroupRobot(Long id) {
        update(new LambdaUpdateWrapper<WeGroupRobotInfo>()
                .set(WeGroupRobotInfo::getDelFlag,1)
                .eq(WeGroupRobotInfo::getId,id));
    }
}
