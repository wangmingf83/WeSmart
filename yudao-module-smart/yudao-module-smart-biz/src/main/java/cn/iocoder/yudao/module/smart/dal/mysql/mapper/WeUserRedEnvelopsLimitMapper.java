package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeUserRedEnvelopsLimit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeUserRedEnvelopsLimitMapper extends BaseMapper<WeUserRedEnvelopsLimit> {

    List<WeUserRedEnvelopsLimit> findLimitUserRedEnvelops(@Param("userId") String userId);

}
