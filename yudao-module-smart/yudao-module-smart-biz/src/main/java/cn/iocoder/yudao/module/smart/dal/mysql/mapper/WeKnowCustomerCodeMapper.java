package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.know.WeKnowCustomerCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author robin
* @description 针对表【we_know_customer_code(识客码)】的数据库操作Mapper
* @createDate 2023-01-09 17:13:50
* @Entity generator.domain.WeKnowCustomerCode
*/
public interface WeKnowCustomerCodeMapper extends BaseMapper<WeKnowCustomerCode> {

    /**
     * 查询识客码列表
     * @param knowCustomerName
     * @return
     */
    List<WeKnowCustomerCode> findWeKnowCustomerCodes(@Param("knowCustomerName") String knowCustomerName);

}
