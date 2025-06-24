package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author leejoker
 * @date 2022/4/1 21:13
 */
public interface WeCategoryMapper extends BaseMapper<WeCategory> {

    //@DataScope(type = "2", value = @DataColumn(alias = "wc", name = "create_by_id", userid = "user_id"))
    List<WeCategory> categoryList(@Param("mediaType") String mediaType);
}
