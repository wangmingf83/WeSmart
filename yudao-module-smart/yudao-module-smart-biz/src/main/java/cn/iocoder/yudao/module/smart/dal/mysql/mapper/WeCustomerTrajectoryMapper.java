package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerTrajectory;

public interface WeCustomerTrajectoryMapper extends BaseMapper<WeCustomerTrajectory> {

    void deleteSynchTrajectory();

//    List<WeCustomerTrajectory> followUpRecord(@Param("externalUserid") String externalUserid,
//                                              @Param("userId") String userId,@Param("trajectoryType") Integer trajectoryType);


}
