package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.service.IWeUnionidExternalUseridRelationService;
import cn.iocoder.yudao.module.wecom.service.IQwCorpService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeUnionidExternalUseridRelation;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.agentdev.WeUnionidTransformExternalUserIdQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.agentdev.WeUnionidTransformExternalUserIdVO;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeUnionExternalUseridRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
public class WeUnionidExternalUseridRelationServiceImpl extends ServiceImpl<WeUnionExternalUseridRelationMapper, WeUnionidExternalUseridRelation> implements IWeUnionidExternalUseridRelationService {


    @Autowired
    private IQwCorpService qwCorpService;

    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public WeUnionidExternalUseridRelation get(String openid, String unionid) {
        WeUnionidExternalUseridRelation weUnionidExternalUseridRelation = getWeUnionidExternalUseridRelation(openid, unionid);
        if (ObjectUtil.isNull(weUnionidExternalUseridRelation)) {
            lock.lock();
            try {
                weUnionidExternalUseridRelation = getWeUnionidExternalUseridRelation(openid, unionid);
                if (ObjectUtil.isNull(weUnionidExternalUseridRelation)) {
                    WeUnionidTransformExternalUserIdQuery query = new WeUnionidTransformExternalUserIdQuery();
                    query.setOpenid(openid);
                    query.setUnionid(unionid);
                    WeUnionidTransformExternalUserIdVO result = qwCorpService.unionidTransformExternalUserId(query);
                    if (ObjectUtil.isNotNull(result)) {
//                        if (result.getCode() == 200 && result.getData() != null) {
                        if (result.getErrMsg() == "") {
                            WeUnionidTransformExternalUserIdVO data = result;
                            if (data.getErrCode().equals(0)) {
                                weUnionidExternalUseridRelation = save(openid, unionid, data.getExternalUserid(), data.getPendingId());
                            }
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return weUnionidExternalUseridRelation;
    }

    /**
     * 查询数据
     *
     * @param openid
     * @param unionid
     * @return
     */
    private WeUnionidExternalUseridRelation getWeUnionidExternalUseridRelation(String openid, String unionid) {
        LambdaQueryWrapper<WeUnionidExternalUseridRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WeUnionidExternalUseridRelation::getOpenid, openid);
        queryWrapper.eq(WeUnionidExternalUseridRelation::getUnionid, unionid);
        queryWrapper.eq(WeUnionidExternalUseridRelation::getDelFlag, Constants.COMMON_STATE);
        WeUnionidExternalUseridRelation weUnionidExternalUseridRelation = this.baseMapper.selectOne(queryWrapper);
        return weUnionidExternalUseridRelation;
    }

    /**
     * 保存数据
     *
     * @param openid
     * @param unionid
     * @param externalUserid
     * @param pendingId
     * @return
     */
    private WeUnionidExternalUseridRelation save(String openid, String unionid, String externalUserid, String pendingId) {
        WeUnionidExternalUseridRelation weUnionidExternalUseridRelation = new WeUnionidExternalUseridRelation();
        weUnionidExternalUseridRelation.setOpenid(openid);
        weUnionidExternalUseridRelation.setUnionid(unionid);
        weUnionidExternalUseridRelation.setExternalUserid(externalUserid);
        weUnionidExternalUseridRelation.setPendingId(pendingId);
        weUnionidExternalUseridRelation.setCreateTime(new Date());
        weUnionidExternalUseridRelation.setDelFlag(Constants.COMMON_STATE);
        int insert = this.baseMapper.insert(weUnionidExternalUseridRelation);
        return weUnionidExternalUseridRelation;
    }

}
