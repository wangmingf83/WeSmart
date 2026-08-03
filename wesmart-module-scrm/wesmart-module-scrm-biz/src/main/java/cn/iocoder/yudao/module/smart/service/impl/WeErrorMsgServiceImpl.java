package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeErrorMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeErrorMsg;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeErrorMsgMapper;
import org.springframework.stereotype.Service;

/**
* @author robin
* @description 针对表【we_error_msg(企业微信响应错误参数记录表)】的数据库操作Service实现
* @createDate 2023-08-23 17:36:43
*/
@Service
public class WeErrorMsgServiceImpl extends ServiceImpl<WeErrorMsgMapper, WeErrorMsg>
    implements IWeErrorMsgService {

}




