package cn.iocoder.yudao.module.system.dal.mysql.area;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.area.AreaDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.area.vo.*;

/**
 * 行政区划 Mapper
 *
 * @author 圭图助手
 */
@Mapper
public interface AreaMapper extends BaseMapperX<AreaDO> {

    default PageResult<AreaDO> selectPage(AreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AreaDO>()
                .eqIfPresent(AreaDO::getParentId, reqVO.getParentId())
                .eqIfPresent(AreaDO::getLevel, reqVO.getLevel())
                .likeIfPresent(AreaDO::getName, reqVO.getName())
                .eqIfPresent(AreaDO::getEPrefix, reqVO.getEPrefix())
                .likeIfPresent(AreaDO::getEName, reqVO.getEName())
                .eqIfPresent(AreaDO::getExtId, reqVO.getExtId())
                .likeIfPresent(AreaDO::getExtName, reqVO.getExtName())
                .betweenIfPresent(AreaDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(AreaDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(AreaDO::getCreateById, reqVO.getCreateById())
                .eqIfPresent(AreaDO::getUpdateBy, reqVO.getUpdateBy())
                .eqIfPresent(AreaDO::getUpdateById, reqVO.getUpdateById())
                .eqIfPresent(AreaDO::getDeleted, reqVO.getDeleted())
                .orderByDesc(AreaDO::getId));
    }

}