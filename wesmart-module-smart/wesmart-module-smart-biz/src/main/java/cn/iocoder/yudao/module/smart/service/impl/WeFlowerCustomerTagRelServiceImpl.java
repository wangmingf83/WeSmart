package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeFlowerCustomerTagRelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeFlowerCustomerTagRel;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeFlowerCustomerTagRelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户标签关系Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-19
 */
@Service
public class WeFlowerCustomerTagRelServiceImpl extends ServiceImpl<WeFlowerCustomerTagRelMapper, WeFlowerCustomerTagRel> implements IWeFlowerCustomerTagRelService {


    @Override
    public void batchAddOrUpdate(List<WeFlowerCustomerTagRel> tagRels) {
        this.baseMapper.batchAddOrUpdate(tagRels);
    }

    @Override
    public List<WeFlowerCustomerTagRel> findNowAddWeFlowerCustomerTagRel(String externalUserId, String userId) {
        return this.baseMapper.findNowAddWeFlowerCustomerTagRel(externalUserId, userId);
    }

    @Override
    public List<WeFlowerCustomerTagRel> findRemoveWeFlowerCustomerTagRel(String externalUserId, String userId) {
        return this.baseMapper.findRemoveWeFlowerCustomerTagRel(externalUserId, userId);
    }

    @Override
    public List<String> getCountByTagIdAndUserId(List<String> tagIds, List<String> userIds) {
        return this.baseMapper.getCountByTagIdAndUserId(tagIds, userIds);
    }

    @Override
    public List<WeFlowerCustomerTagRel> getListByTagIdAndUserId(List<String> tagIds, List<String> userIds) {
        return this.baseMapper.getListByTagIdAndUserId(tagIds, userIds);
    }

    @Override
    public List<WeFlowerCustomerTagRel> findConcatNowAddWeFlowerCustomerTagRel(List<WeCustomer> weCustomers) {
        return this.baseMapper.findConcatNowAddWeFlowerCustomerTagRel(weCustomers);
    }

    @Override
    public void removeConcatNowAddWeFlowerCustomerTagRel(List<WeCustomer> weCustomers) {
        this.baseMapper.removeConcatNowAddWeFlowerCustomerTagRel(weCustomers);
    }
}
