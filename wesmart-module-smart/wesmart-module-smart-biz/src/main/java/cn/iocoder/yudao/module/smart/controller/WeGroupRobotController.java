package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeGroupRobotInfoService;
import cn.iocoder.yudao.module.smart.service.IWeGroupRobotMsgService;
import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupRobotInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupRobotMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotMsgAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotMsgListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.robot.query.WeRobotQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author danmo
 * @description 群机器人管理
 * @date 2022/11/03 18:22
 **/
@Slf4j
@RestController
@RequestMapping(value = "group/robot")
@Tag(name = "群机器人管理")
public class WeGroupRobotController extends BaseController {

    @Autowired
    private IWeGroupRobotInfoService weGroupRobotInfoService;

    @Autowired
    private IWeGroupRobotMsgService weGroupRobotMsgService;


    @Operation(summary = "新增群机器人", method = "POST")
    @PostMapping("/add")
    public AjaxResult addRobot(@RequestBody @Validated WeRobotAddQuery query) {
        log.info("新增群机器人入参query:{}", JSONObject.toJSONString(query));
        Long id = weGroupRobotInfoService.addGroupRobot(query);
        return AjaxResult.success(id);
    }

    @Operation(summary = "编辑群机器人", method = "PUT")
    @PutMapping("/update/{id}")
    public AjaxResult updateRobot(@PathVariable("id") Long id, @Validated @RequestBody WeRobotAddQuery query) {
        query.setId(id);
        log.info("编辑群机器人入参query:{}", JSONObject.toJSONString(query));
        weGroupRobotInfoService.updateRobot(query);
        return AjaxResult.success();
    }

    @Operation(summary = "获取机器人列表", method = "GET")
    @GetMapping("/list")
    public TableDataInfo<WeGroupRobotInfo> getList(WeRobotQuery query) {
        log.info("获取机器人列表接口");
        super.startPage();
        List<WeGroupRobotInfo> list = weGroupRobotInfoService.getList(query);
        return getDataTable(list);
    }


    @Operation(summary = "删除机器人", method = "DELETE")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteGroupRobot(@PathVariable("id") Long id) {
        log.info("删除机器人入参query:{}", id);
        weGroupRobotInfoService.deleteGroupRobot(id);
        return AjaxResult.success();
    }

    @Operation(summary = "获取机器人历史消息列表", method = "GET")
    @GetMapping("/msg/list")
    public TableDataInfo<WeGroupRobotMsg> getMsgList(WeRobotMsgListQuery query) {
        log.info("获取机器人历史消息列表入参query:{}", JSONObject.toJSONString(query));
        super.startPage();
        List<WeGroupRobotMsg> list = weGroupRobotMsgService.getMsgList(query);
        return getDataTable(list);
    }

    @Operation(summary = "发送群机器人消息", method = "POST")
    @PostMapping("/msg/add")
    public AjaxResult addRobotMsg(@Validated @RequestBody WeRobotMsgAddQuery query) {
        log.info("发送群机器人消息入参query:{}", JSONObject.toJSONString(query));
        weGroupRobotMsgService.addRobotMsg(query);
        return AjaxResult.success();
    }

}
