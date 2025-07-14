package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerSeas;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.seas.CustomerSeasCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.seas.CustomerSeasRecordVo;

import java.util.List;


public interface IWeCustomerSeasService extends IService<WeCustomerSeas> {


    CustomerSeasCountVo countCustomerSeas();

    List<CustomerSeasRecordVo> findSeasRecord(Integer groupByType);


    void remidUser(List<String> addUserIds,Integer customerNum);

    List<WeCustomerSeas> findWeCustomerSeas(WeCustomerSeas weCustomerSea);


}
