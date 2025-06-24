package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeAllocateGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeAllocateGroup;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeAllocateGroupMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeAllocateGroupServiceImpl extends ServiceImpl<WeAllocateGroupMapper, WeAllocateGroup> implements IWeAllocateGroupService {
    @Override
    public void batchAddOrUpdate(List<WeAllocateGroup> weAllocateGroups) {
        this.baseMapper.batchAddOrUpdate(weAllocateGroups);
    }
}
