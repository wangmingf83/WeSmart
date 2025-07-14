package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeGroupMessageListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupMessageListMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageList;

/**
 * 群发消息列表(WeGroupMessageList)
 *
 * @author danmo
 * @since 2022-04-06 22:29:04
 */
@Slf4j
@Service
public class WeGroupMessageListServiceImpl extends ServiceImpl<WeGroupMessageListMapper, WeGroupMessageList> implements IWeGroupMessageListService {

}
