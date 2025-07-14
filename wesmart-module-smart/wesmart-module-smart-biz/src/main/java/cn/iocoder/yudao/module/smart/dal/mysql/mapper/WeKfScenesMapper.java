package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfScenes;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.query.WeKfScenesQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo.WeKfScenesListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客服场景信息表(WeKfScenes)
 *
 * @author danmo
 * @since 2022-04-15 15:53:38
 */
@Repository()
@Mapper
public interface WeKfScenesMapper extends BaseMapper<WeKfScenes> {

    @DataScope(type = "2", value = @DataColumn(alias = "wks", name = "create_by_id", userid = "user_id"))
    List<WeKfScenesListVo> getScenesList(WeKfScenesQuery query);
}

