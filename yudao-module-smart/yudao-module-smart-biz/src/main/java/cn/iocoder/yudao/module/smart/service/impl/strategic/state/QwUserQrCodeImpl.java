package cn.iocoder.yudao.module.smart.service.impl.strategic.state;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeQrCodeService;
import cn.iocoder.yudao.module.smart.service.IWeStrategicCrowdStateTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query.WeCorpStateTagSourceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author danmo
 * @description 员工活码来源标签类
 * @date 2022/07/21 17:04
 **/

@Service
public class QwUserQrCodeImpl extends IWeStrategicCrowdStateTagService {

    @Autowired
    private IWeQrCodeService weQrCodeService;

    @Override
    public List<Map<String, Object>> getStateTagSourceList(WeCorpStateTagSourceQuery query) {
        LambdaQueryWrapper<WeQrCode> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(query.getName()),WeQrCode::getName,query.getName());
        wrapper.eq(WeQrCode::getDelFlag,0);
        List<WeQrCode> qrCodeList = weQrCodeService.list(wrapper);
        if(CollectionUtil.isNotEmpty(qrCodeList)){
            List<Map<String, Object>> qrTagList = qrCodeList.parallelStream().map(item -> {
                Map<String, Object> map = new HashMap<>();
                map.put("code", item.getId());
                map.put("value", item.getName());
                return map;
            }).collect(Collectors.toList());
            return qrTagList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<WeCustomer> getStateTagCustomerList(String code) {
        WeQrCode weQrCode = weQrCodeService.getById(code);
        if(weQrCode != null && StringUtils.isNotEmpty(weQrCode.getState())){
            return weCustomerService.list(new LambdaQueryWrapper<WeCustomer>()
                    .eq(WeCustomer::getState, weQrCode.getState()).eq(WeCustomer::getDelFlag,0));
        }
        return null;
    }
}
