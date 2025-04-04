package cn.iocoder.yudao.module.system.service.area;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.system.controller.admin.area.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.area.AreaMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 行政区划 Service 实现类
 *
 * @author 圭图助手
 */
@Service
@Validated
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public Integer createArea(AreaSaveReqVO createReqVO) {
        // 插入
        AreaDO area = BeanUtils.toBean(createReqVO, AreaDO.class);
        areaMapper.insert(area);
        // 返回
        return area.getId();
    }

    @Override
    public void updateArea(AreaSaveReqVO updateReqVO) {
        // 校验存在
        validateAreaExists(updateReqVO.getId());
        // 更新
        AreaDO updateObj = BeanUtils.toBean(updateReqVO, AreaDO.class);
        areaMapper.updateById(updateObj);
    }

    @Override
    public void deleteArea(Integer id) {
        // 校验存在
        validateAreaExists(id);
        // 删除
        areaMapper.deleteById(id);
    }

    private void validateAreaExists(Integer id) {
        if (areaMapper.selectById(id) == null) {
            throw exception(AREA_NOT_EXISTS);
        }
    }

    @Override
    public AreaDO getArea(Integer id) {
        return areaMapper.selectById(id);
    }

    @Override
    public PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO) {
        return areaMapper.selectPage(pageReqVO);
    }

    @Override
    public AreaRespVO getAreaById(Integer id) {
        AreaDO area = areaMapper.selectById(id);
        return BeanUtils.toBean(area, AreaRespVO.class);
    }

    @Override
    public List<AreaRespVO> getChildListById(Integer id) {
        LambdaQueryWrapper query = new LambdaQueryWrapper<AreaDO>()
                .eq(AreaDO::getParentId, id)
                .eq(AreaDO::getDeleted, 0)
                .orderByAsc(AreaDO::getExtId);

        List<AreaDO> list = areaMapper.selectList(query);

        return list.stream().map(sysArea -> {
            AreaRespVO sysAreaVo = new AreaRespVO();
            BeanUtils.copyProperties(sysArea, sysAreaVo);
            return sysAreaVo;
        }).collect(Collectors.toList());
    }

    public List<AreaRespVO> getList(AreaPageReqVO sysArea) {
        LambdaQueryWrapper<AreaDO> wrapper = new LambdaQueryWrapper<>();
        if(sysArea.getId() != null){
            wrapper.eq(AreaDO::getId,sysArea.getId());
        }
        if(sysArea.getParentId() != null){
            wrapper.eq(AreaDO::getParentId,sysArea.getParentId());
        }
        if(sysArea.getName() != null){
            wrapper.like(AreaDO::getName,sysArea.getName());
        }
        if(sysArea.getEPrefix() != null){
            wrapper.eq(AreaDO::getEPrefix,sysArea.getEPrefix());
        }
        wrapper.eq(AreaDO::getDeleted,0);

        List<AreaDO> list = areaMapper.selectList(wrapper);

        return BeanUtils.toBean(list, AreaRespVO.class);
    }
}