package cn.iocoder.yudao.module.system.service.area;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.area.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 行政区划 Service 接口
 *
 * @author 圭图助手
 */
public interface AreaService {

    /**
     * 创建行政区划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createArea(@Valid AreaSaveReqVO createReqVO);

    /**
     * 更新行政区划
     *
     * @param updateReqVO 更新信息
     */
    void updateArea(@Valid AreaSaveReqVO updateReqVO);

    /**
     * 删除行政区划
     *
     * @param id 编号
     */
    void deleteArea(Integer id);

    /**
     * 获得行政区划
     *
     * @param id 编号
     * @return 行政区划
     */
    AreaDO getArea(Integer id);

    /**
     * 获得行政区划分页
     *
     * @param pageReqVO 分页查询
     * @return 行政区划分页
     */
    PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO);

    List<AreaRespVO> getChildListById(Integer id);

    AreaRespVO getAreaById(Integer id);

    List<AreaRespVO> getList(AreaPageReqVO sysArea);
}