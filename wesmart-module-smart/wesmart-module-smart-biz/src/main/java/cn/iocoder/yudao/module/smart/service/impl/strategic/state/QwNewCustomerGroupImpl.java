package cn.iocoder.yudao.module.smart.service.impl.strategic.state;

import cn.iocoder.yudao.module.smart.service.IWeCommunityNewGroupService;
import cn.iocoder.yudao.module.smart.service.IWeEmpleCodeService;
import cn.iocoder.yudao.module.smart.service.IWeStrategicCrowdStateTagService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query.WeCorpStateTagSourceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QwNewCustomerGroupImpl extends IWeStrategicCrowdStateTagService {

    @Autowired
    private IWeCommunityNewGroupService iWeCommunityNewGroupService;

    @Autowired
    private IWeEmpleCodeService iWeEmpleCodeService;

    @Override
    public List<Map<String, Object>> getStateTagSourceList(WeCorpStateTagSourceQuery query) {
//        LambdaQueryWrapper<WeCommunityNewGroup> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(StringUtils.isNotEmpty(query.getName()),WeCommunityNewGroup::getEmplCodeName,query.getName());
//        wrapper.eq(WeCommunityNewGroup::getDelFlag,0);
//        List<WeCommunityNewGroup> communityNewGroups = iWeCommunityNewGroupService.list(wrapper);
//        if(CollectionUtil.isNotEmpty(communityNewGroups)){
//            List<Map<String, Object>> qrTagList = communityNewGroups.parallelStream().map(item -> {
//                Map<String, Object> map = new HashMap<>();
//                map.put("code", item.getId());
//                map.put("value", item.getEmplCodeName());
//                return map;
//            }).collect(Collectors.toList());
//            return qrTagList;
//        }
        return new ArrayList<>();
    }

    @Override
    public List<WeCustomer> getStateTagCustomerList(String code) {
//        WeCommunityNewGroup weCommunityNewGroup = iWeCommunityNewGroupService.getById(code);
//        if(weCommunityNewGroup != null){
//            WeEmpleCode weEmpleCode = iWeEmpleCodeService.getById(weCommunityNewGroup.getEmplCodeId());
//            if(weEmpleCode != null && StringUtils.isNotEmpty(weEmpleCode.getState())){
//                return weCustomerService.list(new LambdaQueryWrapper<WeCustomer>()
//                        .eq(WeCustomer::getState, weEmpleCode.getState()).eq(WeCustomer::getDelFlag,0));
//            }
//        }
        return null;
    }
}
