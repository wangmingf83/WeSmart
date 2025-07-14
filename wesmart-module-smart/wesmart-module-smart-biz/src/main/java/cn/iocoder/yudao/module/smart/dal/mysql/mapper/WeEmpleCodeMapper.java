package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeEmpleCode;


public interface WeEmpleCodeMapper extends BaseMapper<WeEmpleCode> {

    /**
     * 查询员工活码
     *
     * @param id 员工活码ID
     * @return 员工活码
     */
    WeEmpleCode selectWeEmpleCodeById(Long id);



}
