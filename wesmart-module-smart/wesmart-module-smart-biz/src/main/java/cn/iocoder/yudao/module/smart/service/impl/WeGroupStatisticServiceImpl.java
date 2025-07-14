package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeGroupStatisticService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupStatistic;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupStatisticMapper;
import org.springframework.stereotype.Service;

/**
 * 群聊数据统计数据
 * (WeGroupStatistic)
 *
 * @author danmo
 * @since 2022-04-30 23:28:18
 */
@Service
public class WeGroupStatisticServiceImpl extends ServiceImpl<WeGroupStatisticMapper, WeGroupStatistic> implements IWeGroupStatisticService {

}
