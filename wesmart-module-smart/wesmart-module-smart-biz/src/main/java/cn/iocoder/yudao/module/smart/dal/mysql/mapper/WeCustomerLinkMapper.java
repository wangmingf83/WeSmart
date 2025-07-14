package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLink;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLinkCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author robin
* @description 针对表【we_customer_link(获客助手)】的数据库操作Mapper
* @createDate 2023-07-04 17:41:13
* @Entity cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLink
*/
public interface WeCustomerLinkMapper extends BaseMapper<WeCustomerLink> {

    /**
     * 获取相关连接下添加的客户
     * @param weCustomerLinkCount
     * @return
     */
    List<WeCustomerLinkCount> findLinkWeCustomer(@Param("weCustomerLinkCount") WeCustomerLinkCount weCustomerLinkCount);
}




