package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageList;

/**
 * 群发消息列表(WeGroupMessageList)
 *
 * @author danmo
 * @since 2022-04-06 22:29:03
 */
@Repository()
@Mapper
public interface WeGroupMessageListMapper extends BaseMapper<WeGroupMessageList> {


}

