package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupTagRel;

/**
 * 群标签关系(WeGroupTagRel)
 *
 * @author danmo
 * @since 2022-04-06 11:09:56
 */
@Repository()
@Mapper
public interface WeGroupTagRelMapper extends BaseMapper<WeGroupTagRel> {


}

