package cn.iocoder.yudao.module.smart.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import cn.iocoder.yudao.module.common.enums.LiveStateEnums;
import cn.iocoder.yudao.module.common.utils.StringUtils;


/**
 * 直播状态转换器
 */
public class LiveStateConverter implements Converter<Integer> {



    @Override
    public Class<?> supportJavaTypeKey() {
        //对象属性类型
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        //CellData属性类型
        return CellDataTypeEnum.STRING;
    }


    @Override
    public Integer convertToJavaData(ReadConverterContext<?> context) throws Exception {
        //CellData转对象属性
        String cellStr = context.getReadCellData().getStringValue();
        if (StringUtils.isEmpty(cellStr)) {
            return null;
        }

        return LiveStateEnums.ofByInfo(cellStr).get().getCode();
    }


    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        //对象属性转CellData
        Integer cellValue = context.getValue();
        if (cellValue == null) {
            return new WriteCellData<>("");
        }

        return new WriteCellData<>(LiveStateEnums.ofByCode(cellValue).get().getInfo());
    }


}
