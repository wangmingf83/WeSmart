package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.service.IWeShortLinkService;
import com.github.pagehelper.PageInfo;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.common.RedisService;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.Base62NumUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query.WeShortLinkAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query.WeShortLinkQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query.WeShortLinkStatisticQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkAddVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkStatisticsVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author danmo
 * @description 短链管理
 * @date 2022/12/18 18:22
 **/

@RestController
@RequestMapping(value = "/short/link")
@Tag(name = "短链管理")
public class WeShortLinkController extends BaseController {

    @Autowired
    private IWeShortLinkService weShortLinkService;

    @Autowired
    private RedisService redisService;


    @Operation(summary = "校验短链环境", method = "GET")
    @GetMapping("/check/env")
    public AjaxResult<Boolean> checkEnv() {
        Boolean result = weShortLinkService.checkEnv();
        return AjaxResult.success(result);
    }

    @Operation(summary = "新增短链", method = "POST")
    @Log(title = "新增短链", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult<WeShortLinkAddVo> addShortLink(@RequestBody @Validated WeShortLinkAddQuery query) {
        WeShortLinkAddVo result = weShortLinkService.addShortLink(query);
        return AjaxResult.success(result);
    }


    @Operation(summary = "修改短链", method = "PUT")
    @Log(title = "修改短链", businessType = BusinessType.UPDATE)
    @PutMapping("/update/{id}")
    public AjaxResult<WeShortLinkAddVo> updateShortLink(@PathVariable("id") Long id, @RequestBody @Validated WeShortLinkAddQuery query) {
        query.setId(id);
        WeShortLinkAddVo result = weShortLinkService.updateShortLink(query);
        return AjaxResult.success(result);
    }


    @Operation(summary = "删除短链", method = "DELETE")
    @Log(title = "删除短链", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public AjaxResult deleteShortLink(@PathVariable("ids") List<Long> ids) {
        weShortLinkService.deleteShortLink(ids);
        return AjaxResult.success();
    }

    @Operation(summary = "通过ID获取短链详情", method = "GET")
    @Log(title = "短链详情", businessType = BusinessType.SELECT)
    @GetMapping("/get/{id}")
    public AjaxResult<WeShortLinkVo> getShortLinkInfo(@PathVariable("id") Long id) {
        WeShortLinkVo result = weShortLinkService.getShortLinkInfo(id);
        return AjaxResult.success(result);
    }

    @Operation(summary = "通过短链接获取短链详情", method = "GET")
    @Log(title = "短链详情", businessType = BusinessType.SELECT)
    @GetMapping(value = {"/getByShort/{shortUrl}", "/getByShort/{shortUrl}/{promotionId}"})
    public AjaxResult<WeShortLinkVo> getShortLinkInfo(
            @PathVariable("shortUrl") String shortUrl,
            @PathVariable(value = "promotionId", required = false) Long promotionId) {
        long id = Base62NumUtil.decode(shortUrl);
        WeShortLinkVo result = weShortLinkService.getShortLinkInfo(id);
        //短链
        redisService.increment(WeConstans.WE_SHORT_LINK_KEY + WeConstans.OPEN_APPLET + shortUrl);

        //短链推广
        if (ObjectUtil.isNotEmpty(promotionId)) {
            String encode = Base62NumUtil.encode(promotionId);
            redisService.increment(WeConstans.WE_SHORT_LINK_PROMOTION_KEY + WeConstans.OPEN_APPLET + encode);
        }
        return AjaxResult.success(result);
    }


    @Operation(summary = "短链列表", method = "GET")
    @Log(title = "短链列表", businessType = BusinessType.SELECT)
    @GetMapping("/list")
    public TableDataInfo<PageInfo<WeShortLinkListVo>> getShortLinkList(WeShortLinkQuery query) {
        startPage();
        PageInfo<WeShortLinkListVo> shortLinkList = weShortLinkService.getShortLinkList(query);
        return getDataTable(shortLinkList);
    }


    @Operation(summary = "短链数据统计（传ID查单个）", method = "GET")
    @Log(title = "短链数据统计", businessType = BusinessType.SELECT)
    @GetMapping("/data/statistics")
    public AjaxResult<WeShortLinkStatisticsVo> getDataStatistics(WeShortLinkStatisticQuery query) {
        if (Objects.isNull(query)) {
            throw new WeComException("入参不能为空");
        }
        WeShortLinkStatisticsVo result = weShortLinkService.getDataStatistics(query);
        return AjaxResult.success(result);
    }

    @Operation(summary = "短链折线统计", method = "GET")
    @Log(title = "短链折线统计", businessType = BusinessType.SELECT)
    @GetMapping("/line/statistics")
    public AjaxResult<WeShortLinkStatisticsVo> getLineStatistics(WeShortLinkStatisticQuery query) {
        if (Objects.isNull(query)) {
            throw new WeComException("入参不能为空");
        }
        if (Objects.isNull(query.getId())) {
            throw new WeComException("ID不能为空");
        }
        if (Objects.isNull(query.getBeginTime())) {
            throw new WeComException("开始时间不能为空");
        }
        WeShortLinkStatisticsVo result = weShortLinkService.getLineStatistics(query);
        return AjaxResult.success(result);
    }

}
