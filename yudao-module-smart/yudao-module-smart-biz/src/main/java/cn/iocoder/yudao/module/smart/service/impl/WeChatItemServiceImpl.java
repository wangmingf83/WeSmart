package cn.iocoder.yudao.module.smart.service.impl;


import cn.iocoder.yudao.module.smart.service.IWeChatItemService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.LinkMediaQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.WeChatItem;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.WeChatSide;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.dto.WeChatItemDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.vo.WeChatSideVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeChatItemMapper;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeChatSideMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 聊天工具侧边栏
 *
 * @author kewen
 */
@Service
public class WeChatItemServiceImpl extends ServiceImpl<WeChatItemMapper, WeChatItem> implements IWeChatItemService {


    @Autowired
    private IWeMaterialService weMaterialService;

    @Autowired
    private WeChatSideMapper weChatSideMapper;

    @Override
    public void checkItems(WeChatItemDto chatItemDto) {
        List<Long> materialIds = new ArrayList<>();
        if ("0".equals(chatItemDto.getCheckAll())) {
            List<WeMaterial> weMaterials = weMaterialService.findWeMaterials(LinkMediaQuery.builder().mediaType(chatItemDto.getMediaType()).build());
            if (CollectionUtils.isNotEmpty(weMaterials)) {
                materialIds = weMaterials.stream().map(WeMaterial::getId).collect(Collectors.toList());
            }
        } else {
            materialIds = chatItemDto.getMaterialIds();
        }
        List<Long> finalMaterialIds = materialIds;
        Optional.ofNullable(materialIds).ifPresent(
                s -> {
                    List<WeChatItem> items = new ArrayList<>();
                    finalMaterialIds.forEach(materialId -> {
                        WeChatItem item = new WeChatItem();
                        item.setId(SnowFlakeUtil.nextId());
                        item.setMaterialId(materialId);
                        item.setSideId(chatItemDto.getSideId());
                        items.add(item);
                    });
                    this.baseMapper.dropItem(chatItemDto.getSideId());
                    if (CollectionUtils.isNotEmpty(items)) {
                        this.baseMapper.addItem(items);
                    }
                    checkItemsTotal(chatItemDto, finalMaterialIds);
                }
        );
    }

    @Override
    public List<WeChatSideVo> chatItems(Long sideId, String keyword, String mediaType, String userId) {
        return this.baseMapper.findChatItems(sideId, keyword, mediaType, userId);
    }


    /**
     * 更新该次选择的素材数
     *
     * @param chatItemDto      侧边栏信息
     * @param finalMaterialIds 素材id列表
     */
    private void checkItemsTotal(WeChatItemDto chatItemDto, List<Long> finalMaterialIds) {
        WeChatSide side = new WeChatSide();
        side.setId(chatItemDto.getSideId());
        side.setTotal(finalMaterialIds.size());
        weChatSideMapper.updateById(side);
    }
}

