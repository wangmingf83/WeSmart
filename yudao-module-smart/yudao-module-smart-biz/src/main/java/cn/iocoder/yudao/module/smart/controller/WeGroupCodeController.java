package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.core.page.PageDomain;
import cn.iocoder.yudao.module.smart.core.page.TableSupport;
import cn.iocoder.yudao.module.smart.service.IWeGroupCodeService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;

import cn.iocoder.yudao.module.smart.common.RedisService;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.common.exception.CustomException;
import cn.iocoder.yudao.module.common.utils.Base62NumUtil;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.file.FileUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.vo.WeGroupChatInfoVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.vo.WeGroupCodeCountTrendVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrCodeDetailVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrCodeScanCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrCodeScanLineCountVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 客户群活码Controller
 *
 * @author ruoyi
 * @date 2020-10-07
 */
@Slf4j
@RestController
@RequestMapping("/groupCode")
@Tag(name = "群活码管理")
public class WeGroupCodeController extends BaseController {

    @Autowired
    private IWeGroupCodeService groupCodeService;

    @Autowired
    private RedisService redisService;



    /**
     * 查询客户群活码列表
     */
    @GetMapping("/list")
    public TableDataInfo<WeGroupCode> list(WeGroupCode weGroupCode) {
        startPage();
        List<WeGroupCode> list = groupCodeService.selectWeGroupCodeList(weGroupCode);
        return getDataTable(list);
    }


    /**
     * 获取客群详情
     * @param groupCodeId
     * @return
     */
    @GetMapping("/findWeGroupCodeById/{groupCodeId}")
    public AjaxResult<WeGroupCode> findWeGroupCodeById(@PathVariable Long groupCodeId){

        return AjaxResult.success(
                groupCodeService.findWeGroupCodeById(groupCodeId)
        );
    }


    /**
     * 获取群活码详情
     * @param id
     * @return
     */
    @GetMapping("/getActualCode/{id}")
    public AjaxResult<WeGroupCode> getWeGroupCode(@PathVariable String id){
        return AjaxResult.success(
                groupCodeService.getDetail(id)
        );
    }

    /**
     * 批量下载群活码
     */
    @GetMapping("/downloadBatch")
    public void downloadBatch(String ids, HttpServletResponse response) {

        // 构建文件信息列表
        List<FileUtils.FileEntity> fileList = Arrays.stream(Optional.ofNullable(ids).orElse("").split(","))
                .filter(StringUtils::isNotEmpty).map(id -> {
                    WeGroupCode code = groupCodeService.getById(id);
                    return FileUtils.FileEntity.builder()
                            .fileName(code.getActivityName())
                            .url(code.getCodeUrl())
                            .suffix(".jpg")
                            .build();
                }).collect(Collectors.toList());
        try {
            FileUtils.batchDownloadFile(fileList, response.getOutputStream());
        } catch (IOException e) {
            log.error("群活码批量下载失败:"+e.getMessage());
            throw new CustomException("群活码批量下载失败");
        }
    }

    /**
     * 下载群活码
     */
    @GetMapping("/download")
    public void download(String id, HttpServletResponse response) {
        WeGroupCode weGroupCode = groupCodeService.getById(Long.valueOf(id));
        try {
            FileUtils.downloadFile(weGroupCode.getCodeUrl(), response.getOutputStream());
        } catch (IOException e) {
            log.error("群活码下载失败:"+e.getMessage());
            throw new CustomException("群活码下载失败");
        }
    }


    /**
     * 新增客户群活码
     */
    @Log(title = "客户群活码", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult<WeGroupCode> add(@Validated @RequestBody WeGroupCode weGroupCode) {

        groupCodeService.insertWeGroupCode(weGroupCode);
        return AjaxResult.success(
                weGroupCode
        );
    }

    /**
     * 修改客户群活码
     */
    @Log(title = "客户群活码", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult<WeGroupCode> edit(@Validated @RequestBody WeGroupCode weGroupCode) {


        return AjaxResult.success(
                groupCodeService
                        .updateWeGroupCode(weGroupCode)
        );
    }

    /**
     * 删除客户群活码
     */
    @Log(title = "客户群活码", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult batchRemove(@PathVariable Long[] ids) {
        groupCodeService.batchRemoveByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }


    /**
     * 获取指定活码下,群相关信息
     * @return
     */
    @GetMapping("/findWeGroupChatInfos/{groupId}")
    public AjaxResult<WeGroupChatInfoVo> findWeGroupChatInfoVos(@PathVariable Long groupId){
        return AjaxResult.success(
                groupCodeService.findWeGroupChatInfoVos(groupId)
        );
    }


    /**
     *  获取指定活码加群退群指定时间段内相关数据
     * @param state
     * @param beginTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    @GetMapping("/findWeGroupCodeCountTrend")
    public AjaxResult<WeGroupCodeCountTrendVo> findWeGroupCodeCountTrend(String state, String beginTime, String endTime) throws ParseException {

        return AjaxResult.success(
                groupCodeService.findWeGroupCodeCountTrend(state,beginTime,endTime)
        );
    }

    @Operation(summary = "通过短链接获取群活码详情", method = "GET")
    @GetMapping("/getByDetail/{shortUrl}")
    public AjaxResult<WeQrCodeDetailVo> getGroupCodeInfoByShortUrl(@PathVariable("shortUrl") String shortUrl) {
        long id = Base62NumUtil.decode(shortUrl);
        WeGroupCode detail = groupCodeService.getDetail(String.valueOf(id));
        redisService.increment(WeConstans.WE_SHORT_LINK_COMMON_KEY + WeConstans.OPEN_APPLET + "gqr:" +shortUrl);
        return AjaxResult.success(detail);
    }

    @Operation(summary = "获取群活码总数统计", method = "GET")
    @GetMapping("/scan/total")
    public AjaxResult<WeQrCodeScanCountVo> getWeQrCodeScanTotalCount(WeGroupCode weGroupCode) {
        WeQrCodeScanCountVo weQrCodeScanCount = groupCodeService.getWeQrCodeScanTotalCount(weGroupCode);
        return AjaxResult.success(weQrCodeScanCount);
    }

    @Operation(summary = "获取群活码折线图统计", method = "GET")
    @GetMapping("/scan/line")
    public AjaxResult<List<WeQrCodeScanLineCountVo>> getWeQrCodeScanLineCount(WeGroupCode weGroupCode) {
        List<WeQrCodeScanLineCountVo> weQrCodeScanCount = groupCodeService.getWeQrCodeScanLineCount(weGroupCode);
        return AjaxResult.success(weQrCodeScanCount);
    }

    @Operation(summary = "获取群活码表格统计", method = "GET")
    @GetMapping("/scan/sheet")
    public TableDataInfo<List<WeQrCodeScanLineCountVo>> getWeQrCodeScanSheetCount(WeGroupCode weGroupCode) {
        List<WeQrCodeScanLineCountVo> weQrCodeScanCount = groupCodeService.getWeQrCodeScanSheetCount(weGroupCode);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        PageInfo<WeQrCodeScanLineCountVo> pageInfo = new PageInfo<>();
        pageInfo.setTotal(weQrCodeScanCount.size());
        pageInfo.setList(startPage(weQrCodeScanCount,pageNum,pageSize));
        return getDataTable(pageInfo);
    }

    @Operation(summary = "获取群活码表格统计导出", method = "GET")
    @GetMapping("/scan/sheet/export")
    public void getWeQrCodeScanSheetExport(WeGroupCode weGroupCode) {
        List<WeQrCodeScanLineCountVo> weQrCodeScanCount = groupCodeService.getWeQrCodeScanSheetCount(weGroupCode);
        try {
            HttpServletResponse response = ServletUtils.getResponse();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("群活码数据报表", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(), WeQrCodeScanLineCountVo.class);
            write.sheet("数据明细").doWrite(weQrCodeScanCount);
        } catch (IOException e) {
            log.error("获取活码表格统计导出异常：query:{}", JSONObject.toJSONString(weGroupCode), e);
        }
    }
}
