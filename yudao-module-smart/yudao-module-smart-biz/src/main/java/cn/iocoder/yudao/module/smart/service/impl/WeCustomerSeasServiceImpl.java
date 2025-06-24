package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeCustomerSeasService;
import cn.iocoder.yudao.module.smart.service.IWeMessagePushService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;
import cn.iocoder.yudao.module.common.enums.MessageNoticeType;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.seas.CustomerSeasCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.seas.CustomerSeasRecordVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeCustomerSeasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerSeas;

import java.util.List;


@Service
public class WeCustomerSeasServiceImpl extends ServiceImpl<WeCustomerSeasMapper, WeCustomerSeas> implements IWeCustomerSeasService {


    @Autowired
    private IWeMessagePushService iWeMessagePushService;

    @Override
    public CustomerSeasCountVo countCustomerSeas() {
        return this.baseMapper.countCustomerSeas();
    }

    @Override
    public List<CustomerSeasRecordVo> findSeasRecord(Integer groupByType) {
        return this.baseMapper.findSeasRecord(groupByType);
    }


    @Override
    public void remidUser(List<String> addUserIds, Integer customerNum) {

        iWeMessagePushService.pushMessageSelfH5(addUserIds,
                "【任务动态】<br/> 您有一项「线索公海」任务待完成，请尽快处理", MessageNoticeType.SEAS.getType(),true);

    }

    @Override
    @DataScope(type = "2", value = @DataColumn(alias = "we_customer_seas", name = "create_by_id", userid = "user_id"))
    public List<WeCustomerSeas> findWeCustomerSeas(WeCustomerSeas weCustomerSea) {

        LambdaQueryWrapper<WeCustomerSeas> query = new LambdaQueryWrapper<WeCustomerSeas>()
                .like(StringUtils.isNotEmpty(weCustomerSea.getPhone()), WeCustomerSeas::getPhone, weCustomerSea.getPhone())
                .like(StringUtils.isNotEmpty(weCustomerSea.getCustomerName()), WeCustomerSeas::getCustomerName, weCustomerSea.getCustomerName())
                .like(StringUtils.isNotEmpty(weCustomerSea.getAddUserName()), WeCustomerSeas::getAddUserName, weCustomerSea.getAddUserName())
                .eq(weCustomerSea.getAddState() != null, WeCustomerSeas::getAddState, weCustomerSea.getAddState())
                .orderByDesc(WeCustomerSeas::getCreateTime);


        if(StringUtils.isNotEmpty(weCustomerSea.getParams().get("dataScope").toString())){
            query.apply(""+weCustomerSea.getParams().get("dataScope").toString()+"");
        }
        return list(
                query
        );
    }
}
