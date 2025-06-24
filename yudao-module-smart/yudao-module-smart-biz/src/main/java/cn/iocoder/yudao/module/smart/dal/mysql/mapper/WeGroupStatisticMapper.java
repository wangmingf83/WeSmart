package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupStatistic;

/**
 * 群聊数据统计数据
 * (WeGroupStatistic)
 *
 * @author danmo
 * @since 2022-04-30 23:28:18
 */

@Repository()
@Mapper
public interface WeGroupStatisticMapper extends BaseMapper<WeGroupStatistic> {


}

