package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.query.WeKfListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo.QwKfListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客服信息表(WeKfInfo)
 *
 * @author danmo
 * @since 2022-04-15 15:53:35
 */
@Repository()
@Mapper
public interface WeKfInfoMapper extends BaseMapper<WeKfInfo> {

    /**
     * 查询客服列表
     * @param query
     * @return
     */
    @DataScope(type = "2", value = @DataColumn(alias = "wki", name = "create_by_id", userid = "user_id"))
    List<QwKfListVo> getKfList(WeKfListQuery query);

    @DataScope(type = "2", value = @DataColumn(alias = "wki", name = "create_by_id", userid = "user_id"))
    List<Long> getKfIdList(WeKfListQuery query);
}

