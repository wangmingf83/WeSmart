package cn.iocoder.yudao.module.smart.converter;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;
import cn.iocoder.yudao.module.smart.common.enums.CustomerAddWayEnum;

/**
 * 客户添加阶段
 *
 * @author 圭图智能
 * @version 1.0.0
 * @date 2024/08/23 16:47
 */
public class CustomerAddWayConverter implements Converter<Integer> {

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        CustomerAddWayEnum of = CustomerAddWayEnum.of(context.getValue());
        if (BeanUtil.isEmpty(of)) {
            return null;
        }

        return new WriteCellData<>(of.getVal());
    }

}
