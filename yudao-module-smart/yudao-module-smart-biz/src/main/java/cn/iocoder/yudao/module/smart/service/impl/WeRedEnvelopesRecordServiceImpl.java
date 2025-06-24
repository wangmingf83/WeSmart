package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeRedEnvelopesRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeRedEnvelopesRecord;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeRedEnvelopesRecordMapper;
import org.springframework.stereotype.Service;

@Service
public class WeRedEnvelopesRecordServiceImpl extends ServiceImpl<WeRedEnvelopesRecordMapper,WeRedEnvelopesRecord>
        implements IWeRedEnvelopesRecordService {

}
