package cn.iocoder.yudao.module.smart.dal.dataobject.fission.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.vo.WeGroupMessageExecuteUsertipVo;
import lombok.Data;

@Data
public class WeGroupMessageExecuteUserOrGroupTipVo {

    //员工筛选条件
    WeGroupMessageExecuteUsertipVo executeUsertip;

    //群主,多个使用逗号隔开
    String groupOwner;


}
