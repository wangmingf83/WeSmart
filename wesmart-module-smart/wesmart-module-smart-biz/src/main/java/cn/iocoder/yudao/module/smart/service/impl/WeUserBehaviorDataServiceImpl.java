package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeUserBehaviorDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeUserBehaviorData;
import cn.iocoder.yudao.module.smart.dal.dataobject.operation.query.WePageStateQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo.WePageCountVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeUserBehaviorDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联系客户统计数据 (WeUserBehaviorData)
 *
 * @author danmo
 * @since 2022-04-30 23:28:06
 */
@Service
public class WeUserBehaviorDataServiceImpl extends ServiceImpl<WeUserBehaviorDataMapper, WeUserBehaviorData> implements IWeUserBehaviorDataService {


    @Override
    public List<WePageCountVo> getDayCountDataByTime(String beginTime, String endTime) {
        WePageStateQuery query = new WePageStateQuery();
        query.setStartTime(beginTime);
        query.setEndTime(endTime);
        return this.baseMapper.getDayCountDataByTime(query);
    }
}
