package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeUserRedEnvelopsLimit;

import java.util.List;

public interface IWeUserRedEnvelopsLimitService extends IService<WeUserRedEnvelopsLimit> {
    List<WeUserRedEnvelopsLimit> findLimitUserRedEnvelops(String userId);
}
