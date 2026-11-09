package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.leads.leads.entity.WeLeadsAutoRecovery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 线索自动回收
 *
 * @author 圭图智能
 * @version 1.0.0
 * @date 2024/07/12 10:51
 */
@Mapper
public interface WeLeadsAutoRecoveryMapper extends BaseMapper<WeLeadsAutoRecovery> {
}
