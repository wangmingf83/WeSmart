package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.leads.record.entity.WeLeadsFollowRecordContent;
import org.apache.ibatis.annotations.Mapper;

/**
 * 线索跟进记录内容-数据库访问层
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/12 9:46
 */
@Mapper
public interface WeLeadsFollowRecordContentMapper extends BaseMapper<WeLeadsFollowRecordContent> {

}

