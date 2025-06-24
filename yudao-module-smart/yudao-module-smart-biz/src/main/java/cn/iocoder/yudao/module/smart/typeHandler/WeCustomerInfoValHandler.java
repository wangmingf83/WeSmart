package cn.iocoder.yudao.module.smart.typeHandler;


import com.alibaba.fastjson.TypeReference;
import cn.iocoder.yudao.module.common.typeHandler.ListTypeHandler;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerInfoExpand;
import java.util.List;

public class WeCustomerInfoValHandler extends ListTypeHandler< WeCustomerInfoExpand.CustomerInfoExpand> {
    @Override
    protected TypeReference<List<WeCustomerInfoExpand.CustomerInfoExpand>> specificType() {
        return new TypeReference<List<WeCustomerInfoExpand.CustomerInfoExpand>>() {
        };
    }
}
