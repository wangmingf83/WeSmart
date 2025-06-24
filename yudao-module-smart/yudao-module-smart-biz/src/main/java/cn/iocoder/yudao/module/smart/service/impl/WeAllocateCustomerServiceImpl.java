package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeAllocateCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeAllocateCustomer;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeAllocateCustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 离职分配的客户列Service业务层处理
 *
 * @author ruoyi
 * @date 2020-10-24
 */
@Service
public class WeAllocateCustomerServiceImpl extends ServiceImpl<WeAllocateCustomerMapper,WeAllocateCustomer> implements IWeAllocateCustomerService
{

    @Override
    public void batchAddOrUpdate(List<WeAllocateCustomer> weAllocateCustomer) {
        this.baseMapper.batchAddOrUpdate(weAllocateCustomer);
    }
}

