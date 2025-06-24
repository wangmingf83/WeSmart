package cn.iocoder.yudao.module.smart.common;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.SysUserCrmApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class ScrmSysDeptClient {
    @Resource
    private DeptApi deptApi;

    public List<DeptRespDTO> getListByDeptIds(Collection<Long> ids) {
        CommonResult<List<DeptRespDTO>> re = deptApi.getDeptList(ids);
        return re.getData();
    }
}
