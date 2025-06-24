package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WxUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 微信用户表(WxUser)
 *
 * @author danmo
 * @since 2022-07-01 13:42:38
 */

@Repository()
@Mapper
public interface WxUserMapper extends BaseMapper<WxUser> {


}

