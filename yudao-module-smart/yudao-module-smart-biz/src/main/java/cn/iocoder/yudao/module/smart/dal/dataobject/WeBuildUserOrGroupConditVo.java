package cn.iocoder.yudao.module.smart.dal.dataobject;

import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.query.WeQrAddQuery;
import lombok.Data;

/**
 * 构建活码或者员工活码条件参数
 */
@Data
public class WeBuildUserOrGroupConditVo {


    //构建员工活码
    WeQrAddQuery weQrAddQuery;


    //构建群活码
    WeGroupCode addGroupCode;
}
