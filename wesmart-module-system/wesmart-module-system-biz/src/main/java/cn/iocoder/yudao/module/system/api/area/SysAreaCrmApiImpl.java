package cn.iocoder.yudao.module.system.api.area;

import cn.hutool.core.convert.Convert;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.api.area.dto.AreaRespDto;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.system.service.area.AreaService;
import feign.FeignIgnore;
import jakarta.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class SysAreaCrmApiImpl implements SysAreaCrmApi {

    @Resource
    private AreaService areaService;

    @Override
    public List<AreaRespDto> getChildListById(Integer id) {
        return BeanUtils.toBean(areaService.getChildListById(id), AreaRespDto.class);
    }

    @Override
    public AreaRespDto getAreaById(Integer id) {
        return BeanUtils.toBean(areaService.getAreaById(id), AreaRespDto.class);
    }

}
