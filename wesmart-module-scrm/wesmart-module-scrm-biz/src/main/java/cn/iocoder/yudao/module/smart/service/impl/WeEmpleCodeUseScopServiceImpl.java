package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeEmpleCodeUseScopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeEmpleCodeUseScop;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeEmpleCodeUseScopMapper;
import org.springframework.stereotype.Service;

@Service
public class WeEmpleCodeUseScopServiceImpl extends ServiceImpl<WeEmpleCodeUseScopMapper, WeEmpleCodeUseScop> implements IWeEmpleCodeUseScopService {
}
