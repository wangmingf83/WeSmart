package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeUserRedEnvelopsLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeUserRedEnvelopsLimit;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeUserRedEnvelopsLimitMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeUserRedEnvelopsLimitServiceImpl extends ServiceImpl<WeUserRedEnvelopsLimitMapper, WeUserRedEnvelopsLimit> implements IWeUserRedEnvelopsLimitService {


    /**
     * 员工限额列表
     * @param userId
     * @return
     */
    @Override
    public List<WeUserRedEnvelopsLimit> findLimitUserRedEnvelops(String userId) {
        return this.baseMapper.findLimitUserRedEnvelops(userId);
    }
}
