package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeProduct;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.query.WeProductQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.vo.WeProductListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.product.vo.WeProductStatisticsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品信息表(WeProduct)
 *
 * @author danmo
 * @since 2022-09-30 11:36:06
 */
@Repository()
@Mapper
public interface WeProductMapper extends BaseMapper<WeProduct> {

    /**
     * 查询商品列表
     *
     * @param query
     * @return
     */
    List<WeProductListVo> queryProductList(WeProductQuery query);


    /**
     * 商品详情统计
     *
     * @author WangYX
     * @date 2022/11/22 11:31
     * @version 1.0.0
     */
    WeProductStatisticsVo statistics(Long productId);


}

