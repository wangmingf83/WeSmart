package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import cn.iocoder.yudao.module.smart.config.mybatis.LwBaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsEstimateUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsStatisticUserRecordRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo.WeMomentsEstimateUserVO;

import java.util.List;

/**
 * 预估朋友圈执行员工 Mapper 接口
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/26 19:24
 */
public interface WeMomentsEstimateUserMapper extends LwBaseMapper<WeMomentsEstimateUser> {

    /**
     * 获取未执行的员工 （成员群发）
     *
     * @param weMomentsTaskId 朋友圈任务Id
     * @return {@link List <WeMomentsEstimateUser>}
     * @author WangYX
     * @date 2023/06/30 16:37
     */
    List<WeMomentsEstimateUser> getNonExecuteUser(Long weMomentsTaskId);

    /**
     * 获取执行员工列表(成员群发)
     *
     * @param request 朋友圈统计-用户记录列表请求参数
     * @return {@link List<WeMomentsEstimateUser>}
     * @author WangYX
     * @date 2023/06/30 17:25
     */
    List<WeMomentsEstimateUserVO> getExecuteUsers(WeMomentsStatisticUserRecordRequest request);

}
