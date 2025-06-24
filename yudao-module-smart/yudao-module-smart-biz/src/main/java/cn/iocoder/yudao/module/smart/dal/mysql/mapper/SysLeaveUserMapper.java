package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.SysLeaveUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLeaveUserMapper extends BaseMapper<SysLeaveUser> {
    void batchAddOrUpdate(@Param("leaveUsers") List<SysLeaveUser> leaveUsers);

    void leaveSysUser(@Param("weUserIds") List<String> weUserIds);

//    SysUser findSysUserByWeUserId(String weUserId);
}
