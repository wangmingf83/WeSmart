package cn.iocoder.yudao.module.smart.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;

/**
 * 客户类型转换
 *
 * @author 圭图智能
 * @version 1.0.0
 * @date 2024/08/23 16:08
 */
public class CustomerTypeConverter implements Converter<Integer> {


    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        Integer value = context.getValue();
        if (value == null) {
            return null;
        }
        String result = value.equals(1) ? "微信用户" : value.equals(2) ? "企业用户" : "";
        return new WriteCellData<>(result);
    }
}
