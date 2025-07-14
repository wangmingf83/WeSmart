package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeShortLinkStat;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query.WeShortLinkStatisticQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkStatisticsVo;

/**
 * 短链统计表(WeShortLinkStat)
 *
 * @author danmo
 * @since 2023-01-10 23:04:09
 */
public interface IWeShortLinkStatService extends IService<WeShortLinkStat> {

    WeShortLinkStatisticsVo getDataStatistics(WeShortLinkStatisticQuery query);

    WeShortLinkStatisticsVo getLineStatistics(WeShortLinkStatisticQuery query);
}
