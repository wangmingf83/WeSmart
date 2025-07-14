package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerSeas;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.seas.CustomerSeasCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.seas.CustomerSeasRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeCustomerSeasMapper  extends BaseMapper<WeCustomerSeas> {

    CustomerSeasCountVo countCustomerSeas();

    List<CustomerSeasRecordVo> findSeasRecord(@Param("groupByType") Integer groupByType);

}
