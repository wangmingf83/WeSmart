package cn.iocoder.yudao.module.smart.typeHandler;

import com.alibaba.fastjson.TypeReference;
import cn.iocoder.yudao.module.common.typeHandler.ListTypeHandler;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSysFieldTemplate;
import java.util.List;

public class WeCustomerInfoExpandListTypeHandler extends ListTypeHandler<WeSysFieldTemplate.OtherContent> {


    @Override
    protected TypeReference<List<WeSysFieldTemplate.OtherContent>> specificType() {
        return new TypeReference<List<WeSysFieldTemplate.OtherContent>>() {
        };
    }

}
