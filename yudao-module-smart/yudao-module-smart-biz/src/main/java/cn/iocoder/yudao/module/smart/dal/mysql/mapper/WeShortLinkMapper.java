package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeShortLink;

/**
 * 短链信息表(WeShortLink)
 *
 * @author danmo
 * @since 2022-12-26 11:07:15
 */
@Repository()
@Mapper
public interface WeShortLinkMapper extends BaseMapper<WeShortLink> {


}

