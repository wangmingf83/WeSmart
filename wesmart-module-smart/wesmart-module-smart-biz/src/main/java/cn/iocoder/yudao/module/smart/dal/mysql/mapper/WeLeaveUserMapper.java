package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeAllocateCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeAllocateGroups;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeLeaveUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeLeaveUserMapper extends BaseMapper<WeLeaveUser> {

    /**
     * 离职未分配员工
     * @param weLeaveUser
     * @return
     */
     List<WeLeaveUser> leaveNoAllocateUserList(WeLeaveUser weLeaveUser);



    /**
     * 离职已分配员工
     * @param weLeaveUserVo
     * @return
     */
    List<WeLeaveUser> leaveAllocateUserList(WeLeaveUser weLeaveUserVo);


    /**
     * 获取历史分配的客户
     * @param weAllocateCustomers
     * @return
     */
    List<WeAllocateCustomer> getAllocateCustomers(WeAllocateCustomer weAllocateCustomers);



    /**
     * 获取历史分配群
     * @param weAllocateGroups
     * @return
     */
    List<WeAllocateGroups>  getAllocateGroups(WeAllocateGroups weAllocateGroups);


    /**
     * 更新用户状态为已分配
     * @param weUserId
     */
    void updateWeUserIsAllocate(@Param("weUserId") String weUserId);





}
