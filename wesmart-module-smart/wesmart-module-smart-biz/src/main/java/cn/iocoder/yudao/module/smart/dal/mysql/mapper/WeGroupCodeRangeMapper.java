package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupCodeRange;

/**
 * 客户群活码范围(WeGroupCodeRange)
 *
 * @author danmo
 * @since 2023-06-26 17:47:12
 */
@Repository()
@Mapper
public interface WeGroupCodeRangeMapper extends BaseMapper<WeGroupCodeRange> {


}

