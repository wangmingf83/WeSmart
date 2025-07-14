package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeTagMapper extends BaseMapper<WeTag> {

    //   @SqlParser(filter=true)
//   void batchAddOrUpdate(@Param("weTags") List<WeTag> weTags);
    @InterceptorIgnore(tenantLine = "true") // 跳过租户SQL解析（等效原@SqlParser(filter=true)）
    void batchAddOrUpdate(@Param("weTags") List<WeTag> weTags);

}
