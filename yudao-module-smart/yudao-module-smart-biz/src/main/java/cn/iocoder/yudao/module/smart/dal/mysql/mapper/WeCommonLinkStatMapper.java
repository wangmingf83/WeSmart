package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeCommonLinkStat;

/**
 * 短链通用统计表(WeCommonLinkStat)
 *
 * @author danmo
 * @since 2023-07-18 13:34:19
 */
@Repository()
@Mapper
public interface WeCommonLinkStatMapper extends BaseMapper<WeCommonLinkStat> {


}

