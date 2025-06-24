package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeCommonLinkStatService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeCommonLinkStatMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCommonLinkStat;

import java.util.List;

/**
 * 短链通用统计表(WeCommonLinkStat)
 *
 * @author danmo
 * @since 2023-07-18 13:34:19
 */
@Service
public class WeCommonLinkStatServiceImpl extends ServiceImpl<WeCommonLinkStatMapper, WeCommonLinkStat> implements IWeCommonLinkStatService {


    @Override
    public List<WeCommonLinkStat> getStatByShortId(Long shortId, String type) {
        return list(new LambdaQueryWrapper<WeCommonLinkStat>()
                .eq(WeCommonLinkStat::getShortId,shortId)
                .eq(WeCommonLinkStat::getType,type));
    }
}
