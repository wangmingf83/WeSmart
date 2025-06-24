package cn.iocoder.yudao.module.smart.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeFissionInviterRecordService;
import cn.iocoder.yudao.module.smart.service.IWeFissionInviterRecordSubService;
import cn.iocoder.yudao.module.smart.service.IWeFissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.WeFission;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.WeFissionInviterRecord;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.WeFissionInviterRecordSub;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.vo.WeFissionProgressVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeFissionInviterRecordSubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author robin
* @description 针对表【we_fission_detail_sub(裂变明细子表)】的数据库操作Service实现
* @createDate 2023-03-14 14:07:21
*/
@Service
public class WeFissionInviterRecordSubServiceImpl extends ServiceImpl<WeFissionInviterRecordSubMapper, WeFissionInviterRecordSub>
    implements IWeFissionInviterRecordSubService {

    @Autowired
    @Lazy
    private IWeFissionService iWeFissionService;


    @Autowired
    private IWeFissionInviterRecordService iWeFissionInviterRecordService;

    @Override
    public WeFissionProgressVo findWeFissionProgress(String unionid, String fissionId) {
        WeFissionProgressVo weFissionProgressVo=new WeFissionProgressVo();

        WeFissionInviterRecord weFissionInviterRecord = iWeFissionInviterRecordService.getOne(new LambdaQueryWrapper<WeFissionInviterRecord>()
                .eq(WeFissionInviterRecord::getFissionId, fissionId)
                .eq(WeFissionInviterRecord::getInviterUnionid, unionid));

        if(null != weFissionInviterRecord){
            weFissionProgressVo.setWeFissionInviterRecordSubList(this.list(new LambdaQueryWrapper<WeFissionInviterRecordSub>()
                    .eq(WeFissionInviterRecordSub::getFissionInviterRecordId, weFissionInviterRecord.getId())));
            WeFission weFission = iWeFissionService.getById(fissionId);
            if(null != weFission){
                weFissionProgressVo.setExchangeTip(weFission.getExchangeTip());
                if(CollectionUtil.isNotEmpty(weFissionProgressVo.getWeFissionInviterRecordSubList())){
                    if(weFissionProgressVo
                            .getWeFissionInviterRecordSubList().size()>=weFission.getExchangeTip()){
                        //设置回显奖励
                        weFissionProgressVo.setExchangeContent(weFission.getExchangeContent());

                    }
                }

            }

        }

        return weFissionProgressVo;
    }

    @Override
    public void batchSaveOrUpdate(List<WeFissionInviterRecordSub> weFissionInviterRecordSubList) {

        this.baseMapper.batchSaveOrUpdate(weFissionInviterRecordSubList);
    }
}




