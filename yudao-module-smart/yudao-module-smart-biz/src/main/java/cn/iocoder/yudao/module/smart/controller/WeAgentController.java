package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.collection.CollectionUtil;

import cn.iocoder.yudao.module.smart.service.IWeAgentInfoService;
import cn.iocoder.yudao.module.smart.service.IWeAgentMsgService;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import cn.iocoder.yudao.module.wecom.service.IQwAgentService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeAgentInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.query.WeAgentAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.query.WeAgentEditQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.query.WeAgentMsgAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.query.WeAgentMsgListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.vo.LwAgentListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.vo.WeAgentMsgListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.vo.WeAgentMsgVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author danmo
 * @description 应用管理
 * @date 2022/11/03 18:22
 **/
@Slf4j
@RestController
@RequestMapping(value = "agent")
@Tag(name = "应用管理")
public class WeAgentController extends BaseController {

    @Autowired
    private IWeAgentInfoService weAgentInfoService;

    @Autowired
    private IWeAgentMsgService weAgentMsgService;

    @Autowired
    private IWeCorpAccountService iWeCorpAccountService;

    @Autowired
    private IQwAgentService qwAgentService;

    @Operation(summary = "新增应用信息", method = "POST")
    @PostMapping("/add")
    public AjaxResult addAgent(@RequestBody @Validated WeAgentAddQuery query) {
//        log.info("新增应用信息入参query:{}", JSONObject.toJSONString(query));
//        //校验新增的应用是否存在
//        WeAgentQuery weAgentQuery = new WeAgentQuery();
//        weAgentQuery.setAgentid(String.valueOf(query.getAgentId()));
//        weAgentQuery.setCorpid(ScrmSecurityUtils.getCorpId());
//        WeAgentDetailVo weAgentDetailVo = qwAgentClient.getAgentDetail(weAgentQuery).getData();
//
//
//        if(null == weAgentDetailVo){
//            return AjaxResult.error("当前应用不存在");
//        }

        Integer id = weAgentInfoService.addAgent(query);
        return AjaxResult.success(id);
    }

    @Operation(summary = "拉取应用信息", method = "GET")
    @GetMapping("/pull/{id}")
    public AjaxResult pullAgent(@PathVariable("id") Integer id) {
        log.info("拉取应用信息入参query:{}", id);
        weAgentInfoService.pullAgent(id);
        return AjaxResult.success();
    }

    @Operation(summary = "编辑应用信息", method = "PUT")
    @PutMapping("/update/{id}")
    public AjaxResult update(@PathVariable("id") Integer id, @RequestBody WeAgentEditQuery query) {
        query.setId(id);
        log.info("编辑应用信息入参query:{}", JSONObject.toJSONString(query));
        WeAgentInfo weAgentInfo
                = weAgentInfoService.getById(id);

        if(null != weAgentInfo){
            List<WeCorpAccount> weCorpAccounts = iWeCorpAccountService.list(new LambdaQueryWrapper<WeCorpAccount>()
                    .eq(WeCorpAccount::getAgentId, weAgentInfo.getAgentId())
                    .eq(WeCorpAccount::getAgentSecret,weAgentInfo.getSecret()));
            if(CollectionUtil.isNotEmpty(weCorpAccounts)){
                return AjaxResult.error("当前为默认应用不可修改");
            }
        }
        weAgentInfoService.update(query);
        return AjaxResult.success();
    }

    @Operation(summary = "获取应用列表", method = "GET")
    @GetMapping("/list")
    public AjaxResult<LwAgentListVo> getList() {
        log.info("获取应用列表接口调用");
        List<LwAgentListVo> list = weAgentInfoService.getList();
        return AjaxResult.success(list);
    }

    @Operation(summary = "删除应用", method = "DELETE")
    @DeleteMapping("/delete/{id}")
    public AjaxResult<LwAgentListVo> deleteAgent(@PathVariable("id") Integer id) {
        log.info("删除应用入参query:{}", id);

        WeAgentInfo weAgentInfo
                = weAgentInfoService.getById(id);

        if(null != weAgentInfo){
            List<WeCorpAccount> weCorpAccounts = iWeCorpAccountService.list(new LambdaQueryWrapper<WeCorpAccount>()
                    .eq(WeCorpAccount::getAgentId, weAgentInfo.getAgentId())
                    .eq(WeCorpAccount::getAgentSecret,weAgentInfo.getSecret()));
            if(CollectionUtil.isNotEmpty(weCorpAccounts)){
                return AjaxResult.error("当前为默认应用不可删除");
            }

        }

        weAgentInfoService.deleteAgent(id);
        return AjaxResult.success();
    }

    @Operation(summary = "获取历史消息列表", method = "GET")
    @GetMapping("/msg/list")
    public TableDataInfo<WeAgentMsgListVo> getMsgList(WeAgentMsgListQuery query) {
        this.startPage();
        List<WeAgentMsgListVo> list = weAgentMsgService.getMsgList(query);
        return  getDataTable(list);
    }

    @Operation(summary = "新增应用消息", method = "POST")
    @PostMapping("/msg/add")
    public AjaxResult addMsg(@RequestBody WeAgentMsgAddQuery query) {
        log.info("新增应用消息入参query:{}", JSONObject.toJSONString(query));
        weAgentMsgService.addMsg(query);
        return AjaxResult.success();
    }

    @Operation(summary = "修改应用消息", method = "PUT")
    @PutMapping("/msg/update/{id}")
    public AjaxResult updateMsg(@PathVariable("id") Long id, @RequestBody WeAgentMsgAddQuery query) {
        query.setId(id);
        log.info("修改应用消息入参query:{}", JSONObject.toJSONString(query));
        weAgentMsgService.updateMsg(query);
        return AjaxResult.success();
    }

    @Operation(summary = "删除应用消息", method = "DELETE")
    @DeleteMapping("/msg/delete/{id}")
    public AjaxResult deleteMsg(@PathVariable("id") Long id) {
        log.info("删除应用消息入参query:{}", id);
        weAgentMsgService.deleteMsg(id);
        return AjaxResult.success();
    }

    @Operation(summary = "应用消息详情", method = "GET")
    @GetMapping("/msg/get/{id}")
    public AjaxResult<WeAgentMsgVo> getMsgInfo(@PathVariable("id") Long id) {
        log.info("应用消息详情入参query:{}", id);
        return AjaxResult.success(weAgentMsgService.getMsgInfo(id));
    }

    @Operation(summary = "撤销应用消息", method = "GET")
    @GetMapping("/msg/revoke/{id}")
    public AjaxResult revokeMsgInfo(@PathVariable("id") Long id) {
        log.info("撤销消息详情入参query:{}", id);
        weAgentMsgService.revokeMsgInfo(id);
        return AjaxResult.success();
    }


}
