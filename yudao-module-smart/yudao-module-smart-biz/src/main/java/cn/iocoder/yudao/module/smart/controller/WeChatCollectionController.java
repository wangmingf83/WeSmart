package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.IWeChatCollectionService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.MediaType;

import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.LinkMediaCollectQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.vo.WeChatSideVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 聊天工具 侧边栏 素材收藏
 *
 * @author danmo
 */
@Tag(name = "素材收藏接口")
@RequestMapping(value = "/chat/collection")
@RestController
public class WeChatCollectionController extends BaseController {


    @Autowired
    private IWeChatCollectionService weChatCollectionService;

    @Autowired
    private IWeMaterialService weMaterialService;


    /**
     * 添加收藏
     */
    @Operation(summary = "添加收藏",method = "POST")
    @PostMapping("addCollection")
    public AjaxResult addCollection(@RequestBody LinkMediaCollectQuery query) {
        weChatCollectionService.addCollection(query.getMaterialId());
        return  AjaxResult.success();
    }


    /**
     * 取消收藏
     */
    @Operation(summary = "取消收藏",method = "POST")
    @PostMapping(value = "cancleCollection")
    public AjaxResult cancleCollection(@RequestBody LinkMediaCollectQuery query) {
        weChatCollectionService.cancleCollection(query.getMaterialId());
        return  AjaxResult.success();
    }

    /**
     * 收藏列表
     */
    @Operation(summary = "收藏列表",method = "GET")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam(value = "keyword",required = false) String keyword) {
        startPage();
        List<WeChatSideVo> collections = weChatCollectionService.collections(ScrmSecurityUtils.getUserId(),keyword);
        if(CollectionUtil.isNotEmpty(collections)){
            collections.forEach(k->{
                if(StringUtils.isEmpty(k.getMediaType())){//为空则为海报
                    WeMaterial weMaterial = weMaterialService.getById(k.getMaterialId());
                    if(null != weMaterial){
                        k.setMediaType(MediaType.POSTER.getType());
                        k.setMaterialName(weMaterial.getMaterialName());
                        k.setMaterialUrl(weMaterial.getMaterialUrl());
                    }
                }
            });
        }
        return getDataTable(collections);
    }

}
