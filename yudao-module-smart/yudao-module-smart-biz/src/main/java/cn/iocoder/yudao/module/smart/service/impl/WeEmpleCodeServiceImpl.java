package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeEmpleCodeService;
import cn.iocoder.yudao.module.smart.service.IWeEmpleCodeTagService;
import cn.iocoder.yudao.module.smart.service.IWeEmpleCodeUseScopService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeEmpleCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeEmpleCodeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeEmpleCodeUseScop;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.qr.WeAddWayQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.qr.WeAddWayVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeEmpleCodeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeEmpleCodeServiceImpl extends ServiceImpl<WeEmpleCodeMapper, WeEmpleCode> implements IWeEmpleCodeService {


    @Resource
    private IQwCustomerService qwCustomerService;


    @Autowired
    private IWeEmpleCodeTagService iWeEmpleCodeTagService;


    @Autowired
    private IWeMaterialService materialService;

    @Autowired
    private IWeEmpleCodeUseScopService iWeEmpleCodeUseScopService;

    @Override
    public WeAddWayVo getWeContactWay(WeEmpleCode weEmpleCode) {

        return qwCustomerService.addContactWay(this.getWeAddWayQuery(weEmpleCode));
    }

    private WeAddWayQuery getWeAddWayQuery(WeEmpleCode weEmpleCode){
        List<WeEmpleCodeUseScop> weEmpleCodeUseScops = weEmpleCode.getWeEmpleCodeUseScops();
        //根据类型生成相应的活码

        WeAddWayQuery weAddWayQuery = WeAddWayQuery.builder()
                .config_id(weEmpleCode.getConfigId())
                .type(weEmpleCode.getCodeType())
                .scene(WeConstans.QR_CODE_EMPLE_CODE_SCENE)
                .skip_verify(weEmpleCode.getIsJoinConfirmFriends().equals(Integer.valueOf(0)) ? false : true)
                .state(weEmpleCode.getState() == null ? String.valueOf(weEmpleCode.getId()) : weEmpleCode.getState())
                .build();

        if (CollectionUtil.isNotEmpty(weEmpleCodeUseScops)) {
            //员工列表
            List<String> userIdArr = weEmpleCodeUseScops.stream().filter(itme ->
                            WeConstans.USE_SCOP_BUSINESSID_TYPE_USER.equals(itme.getBusinessIdType())
                                    && StringUtils.isNotEmpty(itme.getBusinessId()))
                    .map(WeEmpleCodeUseScop::getBusinessId).collect(Collectors.toList());
            weAddWayQuery.setUser(userIdArr);
            //部门列表
            if (!WeConstans.SINGLE_EMPLE_CODE_TYPE.equals(weEmpleCode.getCodeType())) {
                List<Long> partyArr = weEmpleCodeUseScops.stream().filter(itme ->
                                WeConstans.USE_SCOP_BUSINESSID_TYPE_ORG.equals(itme.getBusinessIdType())
                                        && StringUtils.isNotEmpty(itme.getBusinessId()))
                        .map(item -> Long.valueOf(item.getBusinessId())).collect(Collectors.toList());
                weAddWayQuery.setParty(partyArr);
            }

        }
        return weAddWayQuery;
    }


    @Override
    public WeEmpleCode selectWeEmpleCodeById(Long id) {

        WeEmpleCode weEmpleCode = this.baseMapper.selectWeEmpleCodeById(id);



        if(null != weEmpleCode){

            if(null != weEmpleCode.getMediaId()){
                weEmpleCode.setWeMaterial(
                        materialService.getById(weEmpleCode.getMediaId())
                );
            }

            weEmpleCode.setWeEmpleCodeTags(
                    iWeEmpleCodeTagService.list(new LambdaQueryWrapper<WeEmpleCodeTag>()
                            .eq(WeEmpleCodeTag::getEmpleCodeId,weEmpleCode.getId()))
            );

        }

        return weEmpleCode;
    }

    @Override
    public void updateWeContactWay(WeEmpleCode weEmpleCode) {
        qwCustomerService.updateContactWay(this.getWeAddWayQuery(weEmpleCode));
    }


//    /**
//     * 修改员工活码
//     *
//     * @param weEmpleCode 员工活码
//     * @return 结果
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void updateWeEmpleCode(WeEmpleCode weEmpleCode) {
//
//        if(StringUtils.isEmpty(weEmpleCode.getState())){
//            weEmpleCode.setState(String.valueOf(weEmpleCode.getId()));
//        }
//
//
//
//        AjaxResult<WeResultVo> weResultVoAjaxResult = qwCustomerClient.updateContactWay(this.getWeAddWayQuery(weEmpleCode));
//        if(null != weResultVoAjaxResult){
//            WeResultVo weResultVo = weResultVoAjaxResult.getData();
//
//            if(weResultVo !=null &&  weResultVo.getErrCode().equals(WeErrorCodeEnum.ERROR_CODE_0.getErrorCode())){
//                if (this.updateById(weEmpleCode)) {
//                    if (CollectionUtil.isNotEmpty(weEmpleCode.getWeEmpleCodeUseScops())) {
//
//                        //移除原有的记录
//                        if(iWeEmpleCodeUseScopService.remove(new LambdaQueryWrapper<WeEmpleCodeUseScop>()
//                                .eq(WeEmpleCodeUseScop::getEmpleCodeId,weEmpleCode.getId()))){
//
//                            weEmpleCode.getWeEmpleCodeUseScops().forEach(item -> item.setEmpleCodeId(weEmpleCode.getId()));
//                            iWeEmpleCodeUseScopService.saveOrUpdateBatch(weEmpleCode.getWeEmpleCodeUseScops());
//
//                        }
//
//                    }
//                    if (CollectionUtil.isNotEmpty(weEmpleCode.getWeEmpleCodeTags())) {
//
//                        //有id的更新
//                        iWeEmpleCodeTagService.remove(new LambdaQueryWrapper<WeEmpleCodeTag>()
//                                .eq(WeEmpleCodeTag::getEmpleCodeId,weEmpleCode.getId()));
//
//
//                        weEmpleCode.getWeEmpleCodeTags().forEach(item -> item.setEmpleCodeId(weEmpleCode.getId()));
//                        weEmpleCode.getWeEmpleCodeTags().stream().forEach(k->{
//                            k.setEmpleCodeId(weEmpleCode.getId());
//                            k.setId(SnowFlakeUtil.nextId());
//                        });
//
//                        iWeEmpleCodeTagService.saveOrUpdateBatch(weEmpleCode.getWeEmpleCodeTags());
//
//
//                    }
//                }
//            }
//
//        }
//
//
//
//    }
//

}
