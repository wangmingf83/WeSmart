package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWePresTagGroupTaskStatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.WePresTagGroupTaskStat;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WePresTagGroupTaskStatMapper;
import org.springframework.stereotype.Service;

/**
 * @author leejoker
 * @date 2022/5/6 23:10
 */
@Service
public class WePresTagGroupTaskStatServiceImpl extends ServiceImpl<WePresTagGroupTaskStatMapper, WePresTagGroupTaskStat> implements IWePresTagGroupTaskStatService {
}
