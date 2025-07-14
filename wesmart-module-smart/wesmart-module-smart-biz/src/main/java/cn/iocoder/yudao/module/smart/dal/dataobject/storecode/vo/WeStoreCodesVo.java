package cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.entity.WeStoreCode;
import lombok.Data;

import java.util.List;

@Data
public class WeStoreCodesVo {
    //加群欢迎语
    private String welcomeMsg;

    private List<WeStoreCode> weStoreCodes;
}
