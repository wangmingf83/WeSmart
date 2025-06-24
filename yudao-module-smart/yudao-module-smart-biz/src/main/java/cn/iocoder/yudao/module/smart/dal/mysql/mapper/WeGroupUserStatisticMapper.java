package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupUserStatistic;

/**
 * 群聊群主数据统计数据(WeGroupUserStatistic)
 *
 * @author danmo
 * @since 2022-06-13 16:59:10
 */
@Repository()
@Mapper
public interface WeGroupUserStatisticMapper extends BaseMapper<WeGroupUserStatistic> {


}

