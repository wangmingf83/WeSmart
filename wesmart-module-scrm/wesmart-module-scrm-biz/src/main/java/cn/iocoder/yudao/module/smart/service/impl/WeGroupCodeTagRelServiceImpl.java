package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeGroupCodeTagRelService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCodeTagRel;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.query.WeMakeGroupCodeTagQuery;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupCodeTagRelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeGroupCodeTagRelServiceImpl extends ServiceImpl<WeGroupCodeTagRelMapper, WeGroupCodeTagRel> implements IWeGroupCodeTagRelService {


    @Override
    @Transactional
    public void makeGroupCodeTag(WeMakeGroupCodeTagQuery query) {

        this.remove(new LambdaQueryWrapper<WeGroupCodeTagRel>()
                .eq(WeGroupCodeTagRel::getGroupCodeId,query.getGroupCodeId()));

        List<String> tagIds = query.getTagIds();
        if(CollectionUtil.isNotEmpty(tagIds)){
            List<WeGroupCodeTagRel> groupCodeTagRels=new ArrayList<>();
            tagIds.stream().forEach(tagId->{
                groupCodeTagRels.add(
                        WeGroupCodeTagRel.builder()
                                .groupCodeId(query.getGroupCodeId())
                                .tagId(tagId)
                                .build()
                );
            });
            if(CollectionUtil.isNotEmpty(groupCodeTagRels)){
                this.saveBatch(groupCodeTagRels);
            }
        }

    }
}
