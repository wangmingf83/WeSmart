package cn.iocoder.yudao.module.scheduler.service.impl.crowd;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.scheduler.service.AbstractCrowdService;
import cn.iocoder.yudao.module.smart.service.IWeCustomerService;
import cn.iocoder.yudao.module.smart.service.IWeGroupMemberService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.enums.TrackState;
import cn.iocoder.yudao.module.common.enums.strategiccrowd.RelationEnum;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMember;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.WeStrategicCrowdSwipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 所在群聊计算客户
 */
@Service
@Slf4j
public class CrowdGroupServiceImpl extends AbstractCrowdService {

    @Autowired
    private IWeGroupMemberService iWeGroupMemberService;

    @Autowired
    private IWeCustomerService iWeCustomerService;

    @Override
    public List<WeCustomer> calculate(WeStrategicCrowdSwipe crowdSwipe) {
        List<WeCustomer> weCustomerList=new ArrayList<>();

        LambdaQueryWrapper<WeGroupMember> wrapper = new LambdaQueryWrapper<>();

        RelationEnum relationEnum = RelationEnum.parseEnum(Integer.valueOf(crowdSwipe.getRelation()));
        StringBuilder sqlStr = new StringBuilder(" chat_id ");

        switch (relationEnum){
            case EQUAL:
                sqlStr.append(" = ").append("'").append(crowdSwipe.getValue()).append("'");
                break;

            case NOT_EQUAL:
                sqlStr.append(" <> ").append("'").append(crowdSwipe.getValue()).append("'");
                break;
            default:
                break;
        }
        wrapper.eq(WeGroupMember::getDelFlag, Constants.COMMON_STATE);
        wrapper.apply(sqlStr.toString());

        List<WeGroupMember> groupMembers = iWeGroupMemberService.list(wrapper);

        if(CollectionUtil.isNotEmpty(groupMembers)){
            weCustomerList= iWeCustomerService.list(new LambdaQueryWrapper<WeCustomer>()
                    .in(WeCustomer::getExternalUserid,
                            groupMembers.stream().map(WeGroupMember::getUserId).collect(Collectors.toList()))
                    .ne(WeCustomer::getTrackState, TrackState.STATE_YLS.getType())
                    .eq(WeCustomer::getDelFlag,Constants.COMMON_STATE)
            );
        }

        return weCustomerList;
    }
}
