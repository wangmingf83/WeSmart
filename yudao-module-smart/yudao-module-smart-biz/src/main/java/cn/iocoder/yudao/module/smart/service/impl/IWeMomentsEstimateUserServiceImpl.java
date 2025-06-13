package cn.iocoder.yudao.module.smart.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.service.IWeMomentsEstimateUserService;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsEstimateUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsStatisticUserRecordRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo.WeMomentsEstimateUserVO;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeMomentsEstimateUserMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 预估朋友圈执行员工 服务实现类
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/26 19:26
 */
@Service
public class IWeMomentsEstimateUserServiceImpl extends ServiceImpl<WeMomentsEstimateUserMapper, WeMomentsEstimateUser> implements IWeMomentsEstimateUserService {

    @Resource
    private ScrmSysUserClient scrmSysUserClient;

    @Override
    public void batchInsert(Long weMomentsTaskId, List<String> weUserIds) {
//        AjaxResult<List<ScrmSysUser>> sysUser = qwSysUserClient.findAllSysUser(CollectionUtil.join(weUserIds, ","), null, null);
        List<AdminUserAllDTO> sysUser = scrmSysUserClient.getUserListByWeUserIds(weUserIds);

        List<WeMomentsEstimateUser> list = new ArrayList<>();
        sysUser.forEach(i -> list.add(
                WeMomentsEstimateUser.builder()
                        .id(IdUtil.getSnowflake().nextId())
                        .userId(i.getId())
                        .weUserId(i.getWeUserId())
                        .userName(i.getUsername())
                        .deptId(i.getDeptId())
                        .deptName(i.getDeptName())
                        .momentsTaskId(weMomentsTaskId)
                        .executeCount(0)
                        .executeStatus(0)
                        .build()));
        if(CollectionUtil.isNotEmpty(list)){
            List<List<WeMomentsEstimateUser>> partitions = Lists.partition(list, 1000);
            for(List<WeMomentsEstimateUser> partition:partitions){

                this.baseMapper.insertBatchSomeColumn(partition);
            }
        }



    }

    @Override
    public List<WeMomentsEstimateUser> getNonExecuteUser(Long weMomentsTaskId) {
        return this.baseMapper.getNonExecuteUser(weMomentsTaskId);
    }

    @Override
    public List<WeMomentsEstimateUserVO> getExecuteUsers(WeMomentsStatisticUserRecordRequest request) {
        return this.baseMapper.getExecuteUsers(request);
    }
}
