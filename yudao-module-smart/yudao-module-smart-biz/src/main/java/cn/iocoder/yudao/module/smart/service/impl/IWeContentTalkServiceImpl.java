package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.service.IWeContentSendRecordService;
import cn.iocoder.yudao.module.smart.service.IWeContentTalkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.dto.WeContentSendViewDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeContentTalk;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeTalkMaterial;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.talk.WeContentTalkVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeContentTalkMapper;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeTalkMaterialMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class IWeContentTalkServiceImpl extends ServiceImpl<WeContentTalkMapper, WeContentTalk> implements IWeContentTalkService {

    @Resource
    private WeContentTalkMapper weContentTalkMapper;

    @Resource
    private WeTalkMaterialMapper weTalkMaterialMapper;

    @Resource
    private IWeContentSendRecordService weContentSendRecordService;


    @Override
    @Transactional
    public void saveContentTalk(WeContentTalk weContentTalk) {
        List<WeTalkMaterial> talkMaterialList = weContentTalk.getTalkMaterialList();
        if (ObjectUtil.isEmpty(talkMaterialList)) {
            throw new WeComException("素材不能为空");
        }
        Long talkId = weContentTalk.getId();
        if (ObjectUtil.isNotEmpty(talkId)) {
            weTalkMaterialMapper.delete(new LambdaQueryWrapper<>(WeTalkMaterial.class).
                    eq(WeTalkMaterial::getTalkId, talkId));
        }
        saveOrUpdate(weContentTalk);
        talkMaterialList.forEach(talkMaterial -> {
            WeTalkMaterial weTalkMaterial = new WeTalkMaterial();
            weTalkMaterial.setMaterialId(talkMaterial.getMaterialId());
            weTalkMaterial.setSort(talkMaterial.getSort());
            weTalkMaterial.setTalkId(weContentTalk.getId());
            weTalkMaterialMapper.insert(weTalkMaterial);
        });
    }

    @Deprecated
    @Override
    public List<WeContentTalkVo> selectContentTalkPage(WeContentTalk weContentTalk) {
        Integer resourceType = weContentTalk.getTalkType() == 0 ? 2 : 3;
        //查询查看和发送次数
        List<WeContentSendViewDto> weContentSendViewDtoList = weContentSendRecordService.getSendViewDataTotal(resourceType);

        //查询话术列表
        List<WeContentTalkVo> weContentTalkVoList = weContentTalkMapper.selectWeContentVoList(weContentTalk);

        for (WeContentTalkVo weContentTalkVo : weContentTalkVoList) {
            String materialIds = weContentTalkVo.getMaterialIds();
            if (ObjectUtil.isNotEmpty(materialIds)) {
                List<String> list = Arrays.asList(materialIds.split(","));
                weContentTalkVo.setMaterialNum(list.size());
                for (WeContentSendViewDto weContentSendViewDto : weContentSendViewDtoList) {
                    Long contentId = weContentSendViewDto.getContentId();
                    if (list.contains(contentId.toString())) {
                        Integer sendTotalNum = weContentTalkVo.getSendTotalNum();
                        Integer viewTotalNum = weContentTalkVo.getViewTotalNum();
                        Integer viewByTotalNum = weContentTalkVo.getViewByTotalNum();
                        weContentTalkVo.setSendTotalNum((int) (sendTotalNum + weContentSendViewDto.getSendTotalNum()));
                        weContentTalkVo.setViewTotalNum((int) (viewTotalNum + weContentSendViewDto.getViewTotalNum()));
                        weContentTalkVo.setViewByTotalNum((int) (viewByTotalNum + weContentSendViewDto.getViewByTotalNum()));
                    }
                }
            }
        }
        return weContentTalkVoList;
    }

    @Override
    public WeContentTalk getByIdWithOutTenantId(Long id) {
        return this.baseMapper.getByIdWithOutTenantId(id);
    }


    @Override
    @Transactional
    public void del(List<Long> talkIds) {
        if (ObjectUtil.isEmpty(talkIds)) {
            throw new WeComException("素材不能为空");
        }
        weTalkMaterialMapper.delete(new LambdaQueryWrapper<>(WeTalkMaterial.class).
                in(WeTalkMaterial::getTalkId, talkIds));
        update(Wrappers.lambdaUpdate(WeContentTalk.class).set(WeContentTalk::getDelFlag, 1).in(WeContentTalk::getId, talkIds));
    }

    @Override
    public List<WeContentTalkVo> selectWeContentVoList(WeContentTalk weContentTalk) {
        return this.baseMapper.selectWeContentVoList(weContentTalk);
    }
}
