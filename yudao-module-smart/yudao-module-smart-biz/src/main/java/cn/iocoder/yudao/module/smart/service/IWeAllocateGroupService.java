package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeAllocateGroup;
import java.util.List;

public interface IWeAllocateGroupService extends IService<WeAllocateGroup> {

    void batchAddOrUpdate(List<WeAllocateGroup> weAllocateGroups);
}
