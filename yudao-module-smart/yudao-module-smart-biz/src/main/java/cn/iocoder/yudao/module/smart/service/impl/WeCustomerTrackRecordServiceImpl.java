package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeCustomerTrackRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerTrackRecord;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeCustomerTrackRecordMapper;
import org.springframework.stereotype.Service;

@Service
public class WeCustomerTrackRecordServiceImpl extends ServiceImpl<WeCustomerTrackRecordMapper, WeCustomerTrackRecord> implements IWeCustomerTrackRecordService {
}
