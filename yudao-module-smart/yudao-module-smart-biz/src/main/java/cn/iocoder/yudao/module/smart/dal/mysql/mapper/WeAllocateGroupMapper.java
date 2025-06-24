package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeAllocateGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 分配的群租Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-24
 */
public interface WeAllocateGroupMapper extends BaseMapper<WeAllocateGroup>
{
    void batchAddOrUpdate(@Param("weAllocateGroups") List<WeAllocateGroup> weAllocateGroups);
}
