package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeCommunityKeywordToGroupService;
import cn.iocoder.yudao.module.smart.service.IWeGroupCodeService;
import cn.iocoder.yudao.module.smart.service.IWeGroupService;
import cn.iocoder.yudao.module.smart.service.IWeKeyWordGroupSubService;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.constant.WeComeStateContants;
import cn.iocoder.yudao.module.common.enums.WeErrorCodeEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKeyWordGroupSub;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.groupchat.WeGroupChatJoinWayQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.groupchat.WeGroupChatUpdateJoinWayQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.groupchat.WeGroupChatGetJoinWayVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeKeyWordGroupSubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author robin
* @description 针对表【we_key_word_group_sub(关键词群子表)】的数据库操作Service实现
* @createDate 2023-12-19 12:33:29
*/
@Service
public class WeKeyWordGroupSubServiceImpl extends ServiceImpl<WeKeyWordGroupSubMapper, WeKeyWordGroupSub>
    implements IWeKeyWordGroupSubService {

    @Autowired
    private IWeGroupCodeService iWeGroupCodeService;


    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    private IWeGroupService iWeGroupService;

    @Autowired
    @Lazy
    private IWeCommunityKeywordToGroupService keywordToGroupService;
    @Override
    public void createWeKeyWordGroup(WeKeyWordGroupSub weKeyWordGroupSub) {

        if(StringUtils.isNotEmpty(weKeyWordGroupSub.getChatIdList())){

            List<WeGroup> weGroups = iWeGroupService.list(new LambdaQueryWrapper<WeGroup>()
                    .in(WeGroup::getChatId, weKeyWordGroupSub.getChatIdList().split(",")));
            if(CollectionUtil.isNotEmpty(weGroups)){
                weKeyWordGroupSub.setGroupCodeName( weGroups.stream().map(WeGroup::getGroupName).collect(Collectors.joining(",")));
            }


            weKeyWordGroupSub.setId(SnowFlakeUtil.nextId());
            weKeyWordGroupSub.setGroupCodeState(WeComeStateContants.GJCQ_STATE + weKeyWordGroupSub.getId());
            //配置进群方式
            WeGroupChatGetJoinWayVo addJoinWayVo = iWeGroupCodeService.builderGroupCodeUrl(
                    WeGroupCode.builder()
                            .autoCreateRoom(weKeyWordGroupSub.getAutoCreateRoom())
                            .roomBaseId(weKeyWordGroupSub.getRoomBaseId())
                            .roomBaseName(weKeyWordGroupSub.getRoomBaseName())
                            .chatIdList(weKeyWordGroupSub.getChatIdList())
                            .state(weKeyWordGroupSub.getGroupCodeState())
                            .build()
            );

            if(null != addJoinWayVo && null != addJoinWayVo.getJoin_way()
                    && StringUtils.isNotEmpty(addJoinWayVo.getJoin_way().getQr_code())) {

                weKeyWordGroupSub.setGroupCodeConfigId(addJoinWayVo.getJoin_way().getConfig_id());
                weKeyWordGroupSub.setGroupCodeUrl(addJoinWayVo.getJoin_way().getQr_code());
                this.save(weKeyWordGroupSub);
            }
        }



    }

    @Override
    public void updateWeKeyWordGroup(WeKeyWordGroupSub weKeyWordGroupSub) {

        if(StringUtils.isNotEmpty(weKeyWordGroupSub.getChatIdList())){
            List<WeGroup> weGroups = iWeGroupService.list(new LambdaQueryWrapper<WeGroup>()
                    .in(WeGroup::getChatId, weKeyWordGroupSub.getChatIdList().split(",")));
            if(CollectionUtil.isNotEmpty(weGroups)){
                weKeyWordGroupSub.setGroupCodeName( weGroups.stream().map(WeGroup::getGroupName).collect(Collectors.joining(",")));
            }

            WeKeyWordGroupSub oldWeKeyWordGroupSub = this.getById(weKeyWordGroupSub.getId());
            if(null != oldWeKeyWordGroupSub){
                //更新群活码
                WeResultVo weResultVo = qwCustomerService.updateJoinWayForGroupChat(
                        WeGroupChatUpdateJoinWayQuery.builder()
                                .config_id(oldWeKeyWordGroupSub.getGroupCodeConfigId())
                                .scene(2)
                                .auto_create_room(weKeyWordGroupSub.getAutoCreateRoom())
                                .room_base_id(weKeyWordGroupSub.getRoomBaseId())
                                .room_base_name(weKeyWordGroupSub.getRoomBaseName())
                                .chat_id_list(Arrays.asList(weKeyWordGroupSub.getChatIdList().split(",")))
                                .build()
                );

                if(null != weResultVo && weResultVo.getErrCode()
                        .equals(WeErrorCodeEnum.ERROR_CODE_0.getErrorCode())){
                    this.updateById(weKeyWordGroupSub);
                }else {
                    throw new WeComException(weResultVo.getErrMsg());
                }
            }
        }


    }

    @Override
    @Transactional
    public void batchRemoveWeKeyWordGroupByIds(List<Long> keyWordId) {
        this.batchRemoves(
                this.listByIds(keyWordId)
        );

    }

    @Override
    @Transactional
    public void batchRemoveWeKeyWordGroupByKeyWordId(Long keyWordId) {
        if(keywordToGroupService.removeById(keyWordId)){
            this.batchRemoves(
                    this.list(new LambdaQueryWrapper<WeKeyWordGroupSub>()
                            .eq(WeKeyWordGroupSub::getKeywordGroupId, keyWordId))
            );

        }

    }


    private void batchRemoves( List<WeKeyWordGroupSub> weKeyWordGroupSubs ){
        if(CollectionUtil.isNotEmpty(weKeyWordGroupSubs)){
            weKeyWordGroupSubs.stream().forEach(k->{
                if(StringUtils.isNotEmpty(k.getGroupCodeConfigId())){
                    if(removeById(k.getId())){
                        qwCustomerService.delJoinWayForGroupChat(
                                WeGroupChatJoinWayQuery.builder()
                                        .config_id(k.getGroupCodeConfigId())
                                        .build()
                        );

                    }
                }
            });
        }
    }
}




