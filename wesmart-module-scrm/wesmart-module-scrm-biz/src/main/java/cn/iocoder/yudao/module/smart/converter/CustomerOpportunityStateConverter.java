package cn.iocoder.yudao.module.smart.converter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.spring.SpringUtils;
import cn.iocoder.yudao.module.smart.common.constant.Constants;
import cn.iocoder.yudao.module.smart.common.enums.TrackStateEnum;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeOpportunityStage;
import cn.iocoder.yudao.module.smart.service.IWeOpportunityStageService;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 客户商机阶段Opportunity
 *
 * @author 圭图智能
 * @version 1.0.0
 * @date 2024/08/23 16:43
 */
public class CustomerOpportunityStateConverter implements Converter<Integer> {

    private Map<Long, String> map = new HashMap<>();

    public CustomerOpportunityStateConverter() {
        IWeOpportunityStageService service = SpringUtils.getBean(IWeOpportunityStageService.class);
        LambdaQueryWrapper<WeOpportunityStage> queryWrapper = Wrappers.lambdaQuery(WeOpportunityStage.class);
        queryWrapper.eq(WeOpportunityStage::getDelFlag, Constants.COMMON_STATE);
        List<WeOpportunityStage> list = service.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            map = list.stream().collect(Collectors.toMap(WeOpportunityStage::getId, WeOpportunityStage::getStageKey));
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        Integer value = context.getValue();
        String result = map.get(value);
        return new WriteCellData<>(StrUtil.isNotBlank(result) ? result : "");
    }
}
