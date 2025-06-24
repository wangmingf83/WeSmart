package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeChatItemService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.LinkMediaQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.WeChatItem;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.dto.WeChatItemDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.query.WeChatItemQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 聊天工具侧边栏
 *
 * @author kewen
 */
@RestController
@RequestMapping(value = "/chat/item")
public class WeChatItemController extends BaseController {

    @Autowired
    private IWeChatItemService weChatItemService;

    @Autowired
    private IWeMaterialService materialService;


    /**
     * 获取侧边栏选中或者未选中素材
     * @param query
     * @return
     */
    @GetMapping("/mList")
    public TableDataInfo mList(WeChatItemQuery query) {
        startPage();
        List<WeMaterial> list=materialService.findWeMaterials(LinkMediaQuery.builder()
                .categoryId(query.getCategoryId())
                .search(query.getSearch())
                .mediaType(query.getMediaType())
                .build());
        if(CollectionUtil.isNotEmpty(list)){
            List<WeChatItem> weChatItems = weChatItemService.list(new LambdaQueryWrapper<WeChatItem>()
                    .in(WeChatItem::getMaterialId,list.stream().map(WeMaterial::getId).collect(Collectors.toList())));
            if(CollectionUtil.isNotEmpty(weChatItems)){
                list.stream().forEach(k->{
                    k.setIsCheck(
                            weChatItems.stream().filter(item -> item.getMaterialId()
                                    .equals(k.getId())).findFirst().isPresent()?true:false
                    );
                });
            }
        }

        return getDataTable(list);
    }


    /**
     * 侧边栏抓取素材
     */
    @Log(title = "侧边栏抓取素材", businessType = BusinessType.INSERT)
    @PutMapping
    public AjaxResult add(@RequestBody WeChatItemDto chatItemDto) {
        weChatItemService.checkItems(chatItemDto);
        return AjaxResult.success();
    }

    /**
     * h5素材列表
     */
    @GetMapping("/list")
    public TableDataInfo list(WeChatItemQuery query) {
        startPage();
        return getDataTable(
                weChatItemService.chatItems(query.getSideId(),query.getKeyword(),query.getMediaType(),query.getUserId())
        );
    }





}

