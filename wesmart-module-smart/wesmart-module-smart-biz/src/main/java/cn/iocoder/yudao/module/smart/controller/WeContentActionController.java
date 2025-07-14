package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeContentSendRecordService;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.WeContentSendRecordQuery;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;


/**
 * 素材发送和查看行为
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/10/27 10:34
 */
@Slf4j
@RestController
@RequestMapping("/material/action")
public class WeContentActionController {



    @Resource
    private IWeContentSendRecordService weContentSendRecordService;

    @Operation(summary = "保存单个素材发送详情")
    @PostMapping("/addSend")
    public AjaxResult addSend(@RequestBody WeContentSendRecordQuery weContentSendRecordQuery) {
        log.info("保存单个素材发送详情：素材Id{}，发送者：{},素材类型：{}",
                weContentSendRecordQuery.getContentId(),
                weContentSendRecordQuery.getSendById(),
                weContentSendRecordQuery.getResourceType());
        weContentSendRecordService.singleSend(weContentSendRecordQuery);
        return AjaxResult.success();
    }

    @Operation(summary = "保存一键发送详情")
    @PostMapping("/addAllSend")
    public AjaxResult addAllSend(@RequestBody WeContentSendRecordQuery weContentSendRecordQuery) {
        log.info("保存一键发送发送详情：会话Id{}，发送者：{},素材类型：{}",
                weContentSendRecordQuery.getTalkId(),
                weContentSendRecordQuery.getSendById(),
                weContentSendRecordQuery.getResourceType());
        weContentSendRecordService.withOneTouchSend(weContentSendRecordQuery);
        return AjaxResult.success();
    }


}
