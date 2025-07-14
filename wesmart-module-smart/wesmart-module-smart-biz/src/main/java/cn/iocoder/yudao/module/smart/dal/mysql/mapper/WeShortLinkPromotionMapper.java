package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeShortLinkPromotion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query.WeShortLinkPromotionQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo.WeShortLinkPromotionVo;

import java.util.List;

/**
 * <p>
 * 短链推广 Mapper 接口
 * </p>
 *
 * @author WangYX
 * @since 2023-03-07
 */
public interface WeShortLinkPromotionMapper extends BaseMapper<WeShortLinkPromotion> {

    /**
     * 短链推广列表
     *
     * @param query 查询条件
     * @return {@link List <  WeShortLinkPromotionVo >}
     * @author WangYX
     * @date 2023/03/07 15:41
     */
    List<WeShortLinkPromotionVo> selectList(WeShortLinkPromotionQuery query);

}
