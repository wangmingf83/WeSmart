package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.handler.WeQiRuleWeeklyUserDetailWriteHandler;
import cn.iocoder.yudao.module.smart.service.IWeQiRuleService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.vo.WeChatContactMsgVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 会话质检管理
 *
 * @author danmo
 * @date 2023/05/05 18:22
 **/
@Slf4j
@RestController
@RequestMapping(value = "qi")
@Tag(name = "会话质检管理")
public class WeQiRuleController extends BaseController {

    @Autowired
    private IWeQiRuleService weQiRuleService;

    @Operation(summary = "新增质检规则", method = "POST")
    @PostMapping("/add")
    public AjaxResult addQiRule(@RequestBody @Validated WeQiRuleAddQuery query) {
        weQiRuleService.addQiRule(query);
        return AjaxResult.success();
    }

    @Operation(summary = "修改质检规则", method = "PUT")
    @PutMapping("/update/{id}")
    public AjaxResult updateQiRule(@PathVariable("id") Long id, @RequestBody @Validated WeQiRuleAddQuery query) {
        query.setQiId(id);
        weQiRuleService.updateQiRule(query);
        return AjaxResult.success();
    }


    @Operation(summary = "获取质检规则详情", method = "GET")
    @GetMapping("/get/{id}")
    public AjaxResult<WeQiRuleDetailVo> getQiRuleDetail(@PathVariable("id") Long id) {
        WeQiRuleDetailVo detail = weQiRuleService.getQiRuleDetail(id);
        return AjaxResult.success(detail);
    }

    @Operation(summary = "获取质检规则列表", method = "GET")
    @GetMapping("/list")
    public TableDataInfo<WeQiRuleListVo> getQiRuleList(WeQiRuleListQuery query) {
        startPage();
        PageInfo<WeQiRuleListVo> qrCodeList = weQiRuleService.getQiRulePageList(query);
        return getDataTable(qrCodeList);
    }

    @Operation(summary = "删除质检规则", method = "DELETE")
    @DeleteMapping("/del/{ids}")
    public AjaxResult delQiRule(@PathVariable("ids") List<Long> ids) {
        weQiRuleService.delQiRule(ids);
        return AjaxResult.success();
    }

    @Operation(summary = "质检数据看板统计", method = "GET")
    @GetMapping("/statistics/view/{id}")
    public AjaxResult<WeQiRuleStatisticsViewVo> qiRuleViewStatistics(@PathVariable("id") Long id) {
        WeQiRuleStatisticsViewVo viewVo = weQiRuleService.qiRuleViewStatistics(id);
        return AjaxResult.success(viewVo);
    }

    @Operation(summary = "质检数据列表统计", method = "GET")
    @GetMapping("/statistics/table/{id}")
    public TableDataInfo<List<WeQiRuleStatisticsTableVo>> qiRuleTableStatistics(@PathVariable("id") Long id, WeQiRuleStatisticsTableListQuery query) {
        startPage();
        query.setRuleId(id);
        List<WeQiRuleStatisticsTableVo> list = weQiRuleService.qiRuleTableStatistics(query);
        return getDataTable(list);
    }

    @Operation(summary = "质检数据统计聊天记录", method = "POST")
    @PostMapping("/statistics/table/msg")
    public AjaxResult<List<WeChatContactMsgVo>> getQiRuleTableStatisticsMsg(@RequestBody WeQiRuleStatisticsTableMsgQuery query) {
        List<WeChatContactMsgVo> list = weQiRuleService.getQiRuleTableStatisticsMsg(query);
        return AjaxResult.success(list);
    }

    @Operation(summary = "质检通知列表", method = "GET")
    @GetMapping("/notice/list")
    public TableDataInfo<List<WeQiRuleNoticeListVo>> getNoticeList(WeQiRuleNoticeListQuery query) {
        startPage();
        List<WeQiRuleNoticeListVo> list = weQiRuleService.getNoticeList(query);
        return getDataTable(list);
    }


    @Operation(summary = "质检通知设置回复状态", method = "PUT")
    @PutMapping("/notice/update/replyStatus/{qiRuleMsgId}")
    public AjaxResult updateReplyStatus(@PathVariable("qiRuleMsgId") Long qiRuleMsgId) {
        weQiRuleService.updateReplyStatus(qiRuleMsgId);
        return AjaxResult.success();
    }

    @Operation(summary = "质检周报列表", method = "GET")
    @GetMapping("/weekly/list")
    public TableDataInfo<List<WeQiRuleWeeklyListVo>> getWeeklyList(WeQiRuleWeeklyListQuery query) {
        super.startPage();
        List<WeQiRuleWeeklyListVo> list = weQiRuleService.getWeeklyList(query);
        return getDataTable(list);
    }

    @Operation(summary = "质检周报详情", method = "GET")
    @GetMapping("/weekly/getDetail/{id}")
    public AjaxResult<WeQiRuleWeeklyDetailVo> getWeeklyDetail(@PathVariable("id") Long id) {
        WeQiRuleWeeklyDetailVo detail = weQiRuleService.getWeeklyDetail(id);
        return AjaxResult.success(detail);
    }

    @Operation(summary = "质检周报明细列表", method = "GET")
    @GetMapping("/weekly/detail/list/{id}")
    public TableDataInfo<List<WeQiRuleWeeklyDetailListVo>> getWeeklyDetailList(@PathVariable("id") Long id, WeQiRuleWeeklyDetailListQuery query) {
        query.setWeeklyId(id);
        super.startPage();
        List<WeQiRuleWeeklyDetailListVo> list = weQiRuleService.getWeeklyDetailList(query);
        return getDataTable(list);
    }

    @Operation(summary = "质检周报明细列表导出", method = "GET")
    @GetMapping("/weekly/detail/list/export/{id}")
    public void weeklyDetailListExport(@PathVariable("id") Long id, WeQiRuleWeeklyDetailListQuery query) {
        query.setWeeklyId(id);
        List<WeQiRuleWeeklyDetailListVo> list = weQiRuleService.getWeeklyDetailList(query);
        try {
            HttpServletResponse response = ServletUtils.getResponse();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("质检周报明细", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(), WeQiRuleWeeklyDetailListVo.class);
            write.relativeHeadRowIndex(1);
            write.registerWriteHandler(new WeQiRuleWeeklyUserDetailWriteHandler(query));
            write.sheet("质检周报明细").doWrite(list);
        } catch (IOException e) {
            log.error("质检周报明细列表导出异常：query:{}", JSONObject.toJSONString(query), e);
        }
    }
}
