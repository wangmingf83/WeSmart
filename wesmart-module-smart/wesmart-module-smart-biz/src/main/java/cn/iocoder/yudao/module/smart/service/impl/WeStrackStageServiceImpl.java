package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeCustomerService;
import cn.iocoder.yudao.module.smart.service.IWeStrackStageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.enums.TrackState;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeStrackStage;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeStrackStageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class WeStrackStageServiceImpl extends ServiceImpl<WeStrackStageMapper, WeStrackStage> implements IWeStrackStageService {

    @Autowired
    @Lazy
    private IWeCustomerService iWeCustomerService;

    @Override
    public void initStrackStage() {

       if(count(new LambdaQueryWrapper<WeStrackStage>()
               .eq(WeStrackStage::getIsDefault,1))<=0){
           TrackState[] values = TrackState.values();
           List<WeStrackStage> weStrackStages=new ArrayList<>();
           for(TrackState value:values){
               weStrackStages.add(
                       WeStrackStage.builder()
                               .stageKey(value.getName())
                               .stageVal(value.getType())
                               .stageState(value.getStrackStage())
                               .stageDesc(value.getDesc())
                               .sort(value.getSort())
                               .isDefault(1)
                               .build()
               );
           }
           saveBatch(weStrackStages);
       }


    }

    @Override
    public void add(WeStrackStage weStrackStage) {
        WeStrackStage maxWeStrackStage = this.getOne(new LambdaQueryWrapper<WeStrackStage>()
                .orderByDesc(WeStrackStage::getStageVal).last(" limit 1"));
        if(null != maxWeStrackStage){
            weStrackStage.setStageVal(maxWeStrackStage.getStageVal()+1);
        }
        this.save(weStrackStage);
    }

    @Override
    @Transactional
    public void remove(String id, Integer growStageKey) {
        WeStrackStage weStrackStage = this.getById(id);
        if(null != weStrackStage){
            iWeCustomerService.update(WeCustomer.builder()
                    .trackState(growStageKey)
                    .build(), new LambdaQueryWrapper<WeCustomer>()
                    .eq(WeCustomer::getTrackState,weStrackStage.getStageVal()));
        }

        this.removeById(id);
    }
}
