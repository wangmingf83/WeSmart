package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeLxQrScope;

/**
 * 拉新活码使用范围表(WeLxQrScope)
 *
 * @author danmo
 * @since 2023-03-07 15:06:04
 */
@Repository()
@Mapper
public interface WeLxQrScopeMapper extends BaseMapper<WeLxQrScope> {


}

