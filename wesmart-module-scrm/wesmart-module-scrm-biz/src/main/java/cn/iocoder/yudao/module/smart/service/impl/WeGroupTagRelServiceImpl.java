package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeGroupTagRelService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupTagRel;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.query.WeMakeGroupTagQuery;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupTagRelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 群标签关系(WeGroupTagRel)
 *
 * @author 圭图智能
 * @since 2022-04-06 11:09:57
 */
@Service
public class WeGroupTagRelServiceImpl extends ServiceImpl<WeGroupTagRelMapper, WeGroupTagRel> implements IWeGroupTagRelService {


    @Transactional
    @Override
    public void makeGroupTag(WeMakeGroupTagQuery query) {

        this.remove(new LambdaQueryWrapper<WeGroupTagRel>()
                .eq(WeGroupTagRel::getChatId,query.getChatId()));

         if(CollectionUtil.isNotEmpty(query.getTagIds())){
             List<WeGroupTagRel> tagRels=new ArrayList<>();
             query.getTagIds().stream().forEach(k->{
                 tagRels.add(
                         WeGroupTagRel.builder()
                                 .chatId(query.getChatId())
                                 .tagId(k)
                                 .build()
                 );
             });
             if(CollectionUtil.isNotEmpty(tagRels)){
                 this.saveBatch(tagRels);
             }
         }
    }

    @Override
    public List<WeGroupTagRel> selectGroupTagList(WeMakeGroupTagQuery query) {
        List<WeGroupTagRel> tagRels = this.list(new LambdaQueryWrapper<WeGroupTagRel>()
                .eq(WeGroupTagRel::getChatId, query.getChatId())
                .in(WeGroupTagRel::getTagId, query.getTagIds())
                .eq(WeGroupTagRel::getDelFlag, 0)
        );

        return tagRels;
    }
}
