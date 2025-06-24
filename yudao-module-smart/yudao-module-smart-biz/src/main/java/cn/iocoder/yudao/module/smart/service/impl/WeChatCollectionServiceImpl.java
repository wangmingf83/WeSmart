package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.IWeChatCollectionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeChatCollection;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.vo.WeChatSideVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeChatCollectionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 素材收藏表(WeChatCollection)
 *
 * @author danmo
 * @since 2022-05-25 17:56:59
 */
@Service
public class WeChatCollectionServiceImpl extends ServiceImpl<WeChatCollectionMapper, WeChatCollection> implements IWeChatCollectionService {

    @Override
    public List<WeChatSideVo> collections(Long userId, String keyword) {
        return this.baseMapper.findCollections(userId,keyword);
    }

    @Override
    public void addCollection(Long materialId) {

        WeChatCollection queryCollection = getOne(new LambdaQueryWrapper<WeChatCollection>()
                .eq(WeChatCollection::getMaterialId, materialId)
                .eq(WeChatCollection::getUserId, ScrmSecurityUtils.getUserId())
                .eq(WeChatCollection::getDelFlag, 0)
                .last("limit 1"));
        if (null != queryCollection) {
            throw new WeComException("当前素材不可重复收藏");
        }
        WeChatCollection chatCollection = new WeChatCollection();
        chatCollection.setMaterialId(materialId);
        chatCollection.setUserId(ScrmSecurityUtils.getUserId());
        this.save(chatCollection);
    }

    @Override
    public void cancleCollection(Long materialId) {

        this.remove(new LambdaQueryWrapper<WeChatCollection>()
                .eq(WeChatCollection::getMaterialId,materialId));
//        List<WeChatCollection> queryCollections = list(new LambdaQueryWrapper<WeChatCollection>()
//                .eq(WeChatCollection::getMaterialId, materialId)
//                .eq(WeChatCollection::getUserId, SecurityUtils.getUserId())
//                .eq(WeChatCollection::getDelFlag, 0));
//        if(CollectionUtil.isNotEmpty(queryCollections)){
//            queryCollections.forEach(item -> item.setDelFlag(1));
//            updateBatchById(queryCollections);
//
//        }
    }
}
