package cn.iocoder.yudao.module.system.api.area;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.api.area.dto.AreaRespDto;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import com.fhs.core.trans.anno.AutoTrans;
import com.fhs.trans.service.AutoTransable;

import feign.FeignIgnore;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 地区管理内部公开接口")
@AutoTrans(namespace = SysAreaCrmApi.PREFIX, fields = {"nickname"})
public interface SysAreaCrmApi extends AutoTransable<AreaRespDto> {

    String PREFIX = ApiConstants.PREFIX + "/sysarea";

    @GetMapping("/system/areadb/getChildListById")
    List<AreaRespDto> getChildListById(@RequestParam("id") Integer id);


    /**
     * 根据区域Id，获取区域数据
     *
     * @param id
     * @return {@link CommonResult< AreaRespDto>}
     * @author WangYX
     * @date 2022/10/17 14:39
     */
    @GetMapping("/system/areadb/getAreaById/{id}")
    AreaRespDto getAreaById(@PathVariable("id") Integer id);

    @Override
    @FeignIgnore
    default AreaRespDto selectById(Object primaryValue) {
        return BeanUtils.toBean(getAreaById((int)primaryValue), AreaRespDto.class);
    }
}
