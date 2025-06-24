package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeEmpleCodeTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeEmpleCodeTag;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeEmpleCodeTagMapper;
import org.springframework.stereotype.Service;

@Service
public class WeEmpleCodeTagServiceImpl extends ServiceImpl<WeEmpleCodeTagMapper, WeEmpleCodeTag> implements IWeEmpleCodeTagService {
}
