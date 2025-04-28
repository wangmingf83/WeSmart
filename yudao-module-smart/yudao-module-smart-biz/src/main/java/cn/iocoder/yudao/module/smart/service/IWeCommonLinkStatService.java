package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCommonLinkStat;

import java.util.List;

/**
 * 短链通用统计表(WeCommonLinkStat)
 *
 * @author danmo
 * @since 2023-07-18 13:34:19
 */
public interface IWeCommonLinkStatService extends IService<WeCommonLinkStat> {

    List<WeCommonLinkStat> getStatByShortId(Long shortId, String type);
}
