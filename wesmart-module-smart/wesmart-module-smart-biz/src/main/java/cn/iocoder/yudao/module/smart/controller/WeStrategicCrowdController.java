package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeStrategicCrowdService;
import cn.iocoder.yudao.module.smart.service.IWeStrategicCrowdStateTagService;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.strategiccrowd.CorpAddStateEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.spring.SpringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query.WeCorpStateTagSourceQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author danmo
 * @description 策略人群
 * @date 2022/07/05 18:22
 **/

@RestController
@RequestMapping(value = "strategic/crowd")
@Tag(name = "策略人群")
public class WeStrategicCrowdController extends BaseController {

    @Autowired
    private IWeStrategicCrowdService weStrategicCrowdService;


    @Operation(summary = "获取策略下拉选项", method = "POST")
    @PostMapping("/getDownOptions")
    public AjaxResult<Map<String, Object>> getDownOptions(@RequestBody List<String> enumNames) {
        return AjaxResult.success(weStrategicCrowdService.getDownOptions(enumNames));
    }

    @Operation(summary = "获取策略渠道来源", method = "GET")
    @GetMapping("/getCorpStateTagSource")
    public TableDataInfo getCorpStateTagSource(WeCorpStateTagSourceQuery query) {
        CorpAddStateEnum corpAddStateEnum = CorpAddStateEnum.parseEnum(query.getCode());
        if(corpAddStateEnum == null){
            throw new WeComException("无效CODE");
        }
        String method = corpAddStateEnum.getMethod();
        startPage();
        List<Map<String,Object>> stateTagSourceList = SpringUtils.getBean(method, IWeStrategicCrowdStateTagService.class).getStateTagSourceList(query);
        return getDataTable(stateTagSourceList);
    }


}
