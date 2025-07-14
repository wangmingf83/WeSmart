package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.IWeAgentInfoService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import cn.iocoder.yudao.module.wecom.service.IQwAgentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.common.enums.MessageType;
import cn.iocoder.yudao.module.common.enums.WeErrorCodeEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;

import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeAgentInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.query.WeAgentAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.query.WeAgentEditQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.agent.vo.LwAgentListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.agent.query.WeAgentQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.agent.vo.WeAgentDetailVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.media.WeMediaVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeAgentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 应用信息表(WeAgentInfo)
 *
 * @author danmo
 * @since 2022-11-04 17:08:08
 */
@Service
public class WeAgentInfoServiceImpl extends ServiceImpl<WeAgentInfoMapper, WeAgentInfo> implements IWeAgentInfoService {

    @Resource
    private IQwAgentService qwAgentService;

    @Autowired
    private IWeMaterialService weMaterialService;

    @Override
    public Integer addAgent(WeAgentAddQuery query) {



        WeAgentInfo weAgentInfo = new WeAgentInfo();
        weAgentInfo.setAgentId(query.getAgentId());
        weAgentInfo.setSecret(query.getSecret());
        save(weAgentInfo);
        return weAgentInfo.getId();
    }

    @Override
    public void pullAgent(Integer id) {
        WeAgentInfo weAgentInfo = getById(id);
        WeAgentQuery weAgentQuery = new WeAgentQuery();
        weAgentQuery.setAgentid(String.valueOf(weAgentInfo.getAgentId()));
        weAgentQuery.setCorpid(ScrmSecurityUtils.getCorpId());
        WeAgentDetailVo ajaxResult = qwAgentService.getAgentDetail(weAgentQuery);
        WeAgentDetailVo weAgentDetail = ajaxResult;
        if(Objects.isNull(weAgentDetail)){
            throw new WeComException(ajaxResult.getErrCode(),ajaxResult.getErrMsg());
        }
        if (Objects.nonNull(weAgentDetail.getAgentId())) {
            if(CollectionUtil.isNotEmpty(weAgentDetail.getAllowUserinfos().getUser())){
                String userId = weAgentDetail.getAllowUserinfos().getUser().stream().map(WeAgentDetailVo.AllowUser::getUserId).collect(Collectors.joining(","));
                weAgentInfo.setAllowUserinfoId(userId);
            }
            if(Objects.nonNull(weAgentDetail.getAllowPartys()) && CollectionUtil.isNotEmpty(weAgentDetail.getAllowPartys().getPartyId())){
                String partyIds = String.join(",", weAgentDetail.getAllowPartys().getPartyId());
                weAgentInfo.setAllowPartyId(partyIds);
            }
            if(Objects.nonNull(weAgentDetail.getAllowTags()) && CollectionUtil.isNotEmpty(weAgentDetail.getAllowTags().getTagId())){
                String tagIds = String.join(",", weAgentDetail.getAllowTags().getTagId());
                weAgentInfo.setAllowTagId(tagIds);
            }
            weAgentInfo.setClose(weAgentDetail.getClose());
            weAgentInfo.setCustomizedPublishStatus(weAgentDetail.getCustomizedPublishStatus());
            weAgentInfo.setDescription(weAgentDetail.getDescription());
            weAgentInfo.setHomeUrl(weAgentDetail.getHomeUrl());
            weAgentInfo.setIsReporter(weAgentDetail.getIsreportenter());
            weAgentInfo.setLogoUrl(weAgentDetail.getSquareLogoUrl());
            weAgentInfo.setName(weAgentDetail.getName());
            weAgentInfo.setRedirectDomain(weAgentDetail.getRedirectDomain());
            weAgentInfo.setReportLocationFlag(weAgentDetail.getReportLocationFlag());
            updateById(weAgentInfo);
        }else {
            throw new WeComException(weAgentDetail.getErrCode(), WeErrorCodeEnum.parseEnum(weAgentDetail.getErrCode()).getErrorMsg());
        }
    }

    @Override
    public void update(WeAgentEditQuery query) {
        WeAgentInfo agentInfo = getById(query.getId());
        if (Objects.isNull(agentInfo)) {
            throw new WeComException("应用不存在");
        }
        WeAgentQuery weAgentQuery = new WeAgentQuery();
        weAgentQuery.setAgentid(String.valueOf(agentInfo.getAgentId()));
        weAgentQuery.setCorpid(ScrmSecurityUtils.getCorpId());
        WeAgentInfo weAgentInfo = new WeAgentInfo();
        weAgentInfo.setId(query.getId());
        if (StringUtils.isNotEmpty(query.getName())) {
            weAgentInfo.setName(query.getName());
            weAgentQuery.setName(query.getName());
        }
        if (StringUtils.isNotEmpty(query.getAgentId())) {
            weAgentInfo.setAgentId(Integer.valueOf(query.getAgentId()));
        }
        if (StringUtils.isNotEmpty(query.getSecret())) {
            weAgentInfo.setSecret(query.getSecret());
        }
        if (StringUtils.isNotEmpty(query.getName())) {
            weAgentInfo.setName(query.getName());
            weAgentQuery.setName(query.getName());
        }
        if (StringUtils.isNotEmpty(query.getDescription())) {
            weAgentInfo.setDescription(query.getDescription());
            weAgentQuery.setDescription(query.getDescription());
        }
        if (StringUtils.isNotEmpty(query.getHomeUrl())) {
            weAgentInfo.setHomeUrl(query.getHomeUrl());
            weAgentQuery.setHome_url(query.getHomeUrl());
        }
        if (StringUtils.isNotEmpty(query.getRedirectDomain())) {
            weAgentInfo.setRedirectDomain(query.getRedirectDomain());
            weAgentQuery.setRedirect_domain(query.getRedirectDomain());
        }
        if (StringUtils.isNotEmpty(query.getLogoUrl())) {
            WeMediaVo weMediaVo = weMaterialService.uploadTemporaryMaterial(query.getLogoUrl(),
                    MessageType.IMAGE.getMessageType(),
                    FileUtil.getName(query.getLogoUrl()));
            if (Objects.nonNull(weMediaVo) && StringUtils.isNotEmpty(weMediaVo.getMediaId())) {
                weAgentInfo.setLogoUrl(query.getLogoUrl());
                weAgentQuery.setLogo_mediaid(weMediaVo.getMediaId());
            }
        }
        updateById(weAgentInfo);
        WeResultVo ajaxResult = qwAgentService.updateAgent(weAgentQuery);
        WeResultVo resultVo = ajaxResult;
        if(Objects.isNull(resultVo)){
            throw new WeComException(ajaxResult.getErrCode(), ajaxResult.getErrMsg());
        }
        if (!Objects.equals(0, resultVo.getErrCode())) {
            throw new WeComException(resultVo.getErrCode(), WeErrorCodeEnum.parseEnum(resultVo.getErrCode()).getErrorMsg());
        }
    }

    @Override
    public WeAgentInfo getAgentInfoByAgentId(Integer agentId) {
        return getOne(new LambdaQueryWrapper<WeAgentInfo>().eq(WeAgentInfo::getAgentId, agentId).eq(WeAgentInfo::getDelFlag, 0).last("limit 1"));
    }

    @Override
    public void deleteAgent(Integer id) {
        WeAgentInfo weAgentInfo = new WeAgentInfo();
        weAgentInfo.setId(id);
        weAgentInfo.setDelFlag(1);
        updateById(weAgentInfo);
    }

    @Override
    public List<LwAgentListVo> getList() {
        return this.baseMapper.getList();
    }
}
