package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.side.vo.WeChatSideVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeChatCollection;

/**
 * 素材收藏表(WeChatCollection)
 *
 * @author danmo
 * @since 2022-05-25 17:56:59
 */
@Repository()
@Mapper
public interface WeChatCollectionMapper extends BaseMapper<WeChatCollection> {


//    @DataScope(type = "2", value = @DataColumn(alias = "wcc", name = "user_id", userid = "user_id"))
    List<WeChatSideVo> findCollections(@Param("userId") Long userId, @Param("keyword") String keyword);
}

