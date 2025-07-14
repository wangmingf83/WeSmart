package cn.iocoder.yudao.module.smart.converter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.smart.service.IWeStrackStageService;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.utils.spring.SpringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeStrackStage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 客户商机阶段
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/08/23 16:43
 */
public class CustomerTrackStateConverter implements Converter<Integer> {

    private Map<Integer, String> map = new HashMap<>();

    public CustomerTrackStateConverter() {
        IWeStrackStageService service = SpringUtils.getBean(IWeStrackStageService.class);
        LambdaQueryWrapper<WeStrackStage> queryWrapper = Wrappers.lambdaQuery(WeStrackStage.class);
        queryWrapper.eq(WeStrackStage::getDelFlag, Constants.COMMON_STATE);
        List<WeStrackStage> list = service.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            map = list.stream().collect(Collectors.toMap(WeStrackStage::getStageVal, WeStrackStage::getStageKey));
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        Integer value = context.getValue();
        String result = map.get(value);
        return new WriteCellData<>(StrUtil.isNotBlank(result) ? result : "");
    }
}
