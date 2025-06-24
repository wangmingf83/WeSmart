package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeKfCustomerStatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfCustomerStat;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeKfCustomerStatMapper;
import org.springframework.stereotype.Service;

/**
 * 客服客户统计表(WeKfCustomerStat)
 *
 * @author danmo
 * @since 2022-11-28 16:48:24
 */
@Service
public class WeKfCustomerStatServiceImpl extends ServiceImpl<WeKfCustomerStatMapper, WeKfCustomerStat> implements IWeKfCustomerStatService {

}
