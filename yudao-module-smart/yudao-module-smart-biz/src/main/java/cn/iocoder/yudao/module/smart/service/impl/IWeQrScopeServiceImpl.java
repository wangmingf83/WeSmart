package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.RandomUtil;
import cn.iocoder.yudao.module.smart.service.IWeQrScopeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.query.WeQrUserInfoQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrScopeVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeQrScopeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 活码使用范围表(WeQrScope)表服务实现类
 *
 * @author makejava
 * @since 2021-11-07 01:29:14
 */
//@DS("db1")
@Service
public class IWeQrScopeServiceImpl extends ServiceImpl<WeQrScopeMapper, WeQrScope> implements IWeQrScopeService {

    public IWeQrScopeServiceImpl() {
    }

    @Autowired
    private WeQrScopeMapper weQrScopeMapper;

    @Override
    public void saveBatchByQrId(Long qrId, List<WeQrUserInfoQuery> qrUserInfos) {
        if (CollectionUtil.isNotEmpty(qrUserInfos)) {
            List<WeQrScope> scopeList = new ArrayList<>();
            for (WeQrUserInfoQuery userInfo : qrUserInfos) {
                String uuId = RandomUtil.randomString(18);
                List<WeQrScope> weQrScopeUserList = Optional.ofNullable(userInfo.getQrUserInfosDetail()).orElseGet(ArrayList::new).stream().map(userId -> {
                    WeQrScope weQrScope = new WeQrScope();
                    weQrScope.setQrId(qrId);
                    weQrScope.setScopeId(uuId);
                    weQrScope.setUserId(userId.getUserId());
                    weQrScope.setSchedulingNum(userId.getSchedulingNum());
                    weQrScope.setScopeType(1);
                    weQrScope.setType(userInfo.getType());
                    if(CollUtil.isNotEmpty(userInfo.getWorkCycle())){
                        weQrScope.setWorkCycle(userInfo.getWorkCycle().stream().map(String::valueOf).collect(Collectors.joining(",")));
                    }
                    weQrScope.setBeginTime(userInfo.getBeginTime());
                    weQrScope.setEndTime(userInfo.getEndTime());
                    return weQrScope;
                }).collect(Collectors.toList());
                scopeList.addAll(weQrScopeUserList);

                List<WeQrScope> weQrSpareScopeUserList = Optional.ofNullable(userInfo.getSpareUserIds()).orElseGet(ArrayList::new).stream().map(userId -> {
                    WeQrScope weQrScope = new WeQrScope();
                    weQrScope.setQrId(qrId);
                    weQrScope.setScopeId(uuId);
                    weQrScope.setUserId(userId);
                    weQrScope.setScopeType(1);
                    weQrScope.setIsSpareUser(1);
                    weQrScope.setType(userInfo.getType());
                    if(CollUtil.isNotEmpty(userInfo.getWorkCycle())){
                        weQrScope.setWorkCycle(userInfo.getWorkCycle().stream().map(String::valueOf).collect(Collectors.joining(",")));
                    }
                    weQrScope.setBeginTime(userInfo.getBeginTime());
                    weQrScope.setEndTime(userInfo.getEndTime());
                    return weQrScope;
                }).collect(Collectors.toList());
                scopeList.addAll(weQrSpareScopeUserList);

                List<WeQrScope> weQrScopePartyList = Optional.ofNullable(userInfo.getPartys()).orElseGet(ArrayList::new).stream().map(party -> {
                    WeQrScope weQrScope = new WeQrScope();
                    weQrScope.setQrId(qrId);
                    weQrScope.setScopeId(uuId);
                    weQrScope.setParty(String.valueOf(party));
                    weQrScope.setScopeType(2);
                    if(CollUtil.isNotEmpty(userInfo.getWorkCycle())){
                        weQrScope.setWorkCycle(userInfo.getWorkCycle().stream().map(String::valueOf).collect(Collectors.joining(",")));
                    }
                    weQrScope.setType(userInfo.getType());
                    weQrScope.setBeginTime(userInfo.getBeginTime());
                    weQrScope.setEndTime(userInfo.getEndTime());
                    return weQrScope;
                }).collect(Collectors.toList());
                scopeList.addAll(weQrScopePartyList);
            }
            saveBatch(scopeList);
        }
    }

    @Override
    public Boolean delBatchByQrIds(List<Long> qrIds) {
        WeQrScope weQrScope = new WeQrScope();
        weQrScope.setDelFlag(1);
        return this.update(weQrScope,new LambdaQueryWrapper<WeQrScope>().in(WeQrScope::getQrId,qrIds));
    }

    @Override
    public void updateBatchByQrId(Long qrId, List<WeQrUserInfoQuery> qrUserInfos) {
        if(CollectionUtil.isNotEmpty(qrUserInfos)){
            delBatchByQrIds(ListUtil.toList(qrId));
            this.saveBatchByQrId(qrId,qrUserInfos);
        }
    }

    @Override
    public List<WeQrScopeVo> getWeQrScopeByQrIds(List<Long> qrIds) {
        return this.baseMapper.getWeQrScopeByQrIds(qrIds);
    }

    @Override
    public List<WeQrScopeVo> getWeQrScopeByTime(String formatTime, String qrId) {
       return this.baseMapper.getWeQrScopeByTime(formatTime,qrId);
    }

    @Override
    public void updateScopeStatusByQrId(Long qrId, String scopeId) {
        if(qrId != null && StringUtils.isNotEmpty(scopeId)){
            this.baseMapper.updateScopeStatusByQrId(qrId,scopeId);
        }
    }
}
