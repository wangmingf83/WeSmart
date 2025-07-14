package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleWeeklyDetailListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleWeeklyDetailListVo;
import cn.iocoder.yudao.module.smart.service.IWeQiRuleWeeklyUserDataService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeQiRuleWeeklyUserDataMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleWeeklyUserData;

import java.util.List;

/**
 * 会话质检周报员工数据表(WeQiRuleWeeklyUserData)
 *
 * @author danmo
 * @since 2023-05-18 17:36:35
 */
@Service
public class WeQiRuleWeeklyUserDataServiceImpl extends ServiceImpl<WeQiRuleWeeklyUserDataMapper, WeQiRuleWeeklyUserData> implements IWeQiRuleWeeklyUserDataService {


    @Override
    public List<WeQiRuleWeeklyDetailListVo> getWeeklyDetailList(WeQiRuleWeeklyDetailListQuery query) {
        return this.baseMapper.getWeeklyDetailList(query);
    }
}
