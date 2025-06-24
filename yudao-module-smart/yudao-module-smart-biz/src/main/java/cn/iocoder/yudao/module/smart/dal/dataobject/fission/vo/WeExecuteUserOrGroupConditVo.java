package cn.iocoder.yudao.module.smart.dal.dataobject.fission.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.sop.vo.WeSopExecuteUserConditVo;
import lombok.Data;

@Data
public class WeExecuteUserOrGroupConditVo {

    //员工筛选条件
    WeSopExecuteUserConditVo addWeUser;

    //构建群活码
    WeGroupCode addGroupCode;

}
