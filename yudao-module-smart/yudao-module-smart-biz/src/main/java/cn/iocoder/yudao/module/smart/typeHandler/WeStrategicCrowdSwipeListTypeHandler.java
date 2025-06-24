package cn.iocoder.yudao.module.smart.typeHandler;

import com.alibaba.fastjson.TypeReference;
import cn.iocoder.yudao.module.common.typeHandler.ListTypeHandler;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.WeStrategicCrowdSwipe;

import java.util.List;

public class WeStrategicCrowdSwipeListTypeHandler extends ListTypeHandler<WeStrategicCrowdSwipe> {

    @Override
    protected TypeReference<List<WeStrategicCrowdSwipe>> specificType() {
        return new TypeReference<List<WeStrategicCrowdSwipe>>() {
        };
    }


}
