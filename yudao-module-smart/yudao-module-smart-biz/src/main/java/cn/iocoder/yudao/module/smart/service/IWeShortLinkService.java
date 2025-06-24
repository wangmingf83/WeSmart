package cn.iocoder.yudao.module.smart.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeShortLink;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query.WeShortLinkAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query.WeShortLinkQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query.WeShortLinkStatisticQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkAddVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkStatisticsVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkVo;

import java.util.List;

/**
 * 短链信息表(WeShortLink)
 *
 * @author danmo
 * @since 2022-12-26 11:07:16
 */
public interface IWeShortLinkService extends IService<WeShortLink> {

    Boolean checkEnv();

    WeShortLinkAddVo addShortLink(WeShortLinkAddQuery query);

    WeShortLinkAddVo updateShortLink(WeShortLinkAddQuery query);

    void deleteShortLink(List<Long> ids);

    WeShortLinkVo getShortLinkInfo(Long id);

    PageInfo<WeShortLinkListVo> getShortLinkList(WeShortLinkQuery query);

    JSONObject getShort2LongUrl(String shortUrl, String promotionIdKey);

    WeShortLinkStatisticsVo getDataStatistics(WeShortLinkStatisticQuery query);

    WeShortLinkStatisticsVo getLineStatistics(WeShortLinkStatisticQuery query);

}
