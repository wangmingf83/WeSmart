package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.service.IWeChatContactSensitiveMsgService;
import cn.iocoder.yudao.module.smart.service.IWeSensitiveAuditScopeService;
import cn.iocoder.yudao.module.smart.service.IWeSensitiveService;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import cn.iocoder.yudao.module.common.constant.WeConstans;

import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.DateUtils;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitive;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveAuditScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.query.WeSensitiveHitQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.vo.WeChatContactSensitiveMsgVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeSensitiveMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 敏感词设置表(WeSensitive)
 *
 * @author danmo
 * @since 2022-06-10 10:38:46
 */
@Service
public class WeSensitiveServiceImpl extends ServiceImpl<WeSensitiveMapper, WeSensitive> implements IWeSensitiveService {

    @Autowired
    private IWeSensitiveAuditScopeService sensitiveAuditScopeService;

    @Autowired
    private IWeChatContactSensitiveMsgService weChatContactSensitiveMsgService;

    @Autowired
    private ScrmSysUserClient scrmSysUserClient;

    /**
     * 查询敏感词设置
     *
     * @param id 敏感词设置ID
     * @return 敏感词设置
     */
    @Override
    public WeSensitive selectWeSensitiveById(Long id) {
        return this.baseMapper.selectWeSensitiveById(id);
    }

    /**
     * 查询敏感词设置列表
     *
     * @param weSensitive 敏感词设置
     * @return 敏感词设置
     */
    @Override
    public List<WeSensitive> selectWeSensitiveList(WeSensitive weSensitive) {
        return this.baseMapper.selectWeSensitiveList(weSensitive);
    }

    /**
     * 新增敏感词设置
     *
     * @param weSensitive 敏感词设置
     * @return 结果
     */
    @Override
    @Transactional
    public void insertWeSensitive(WeSensitive weSensitive) {
        if (save(weSensitive)) {
            if (CollectionUtils.isNotEmpty(weSensitive.getAuditUserScope())) {
                if (weSensitive.getId() != null) {
                    weSensitive.getAuditUserScope().forEach(scope -> {
                        scope.setSensitiveId(weSensitive.getId());
                    });
                    sensitiveAuditScopeService.saveBatch(weSensitive.getAuditUserScope());
                }
            }
        }
    }

    /**
     * 修改敏感词设置
     *
     * @param weSensitive 敏感词设置
     * @return 结果
     */
    @Override
    @Transactional
    public void updateWeSensitive(WeSensitive weSensitive) {
        if (updateById(weSensitive) && weSensitive.getAuditUserScope() != null) {
            //删除原有关联信息
            sensitiveAuditScopeService.deleteAuditScopeBySensitiveId(weSensitive.getId());
            if (CollectionUtils.isNotEmpty(weSensitive.getAuditUserScope())) {
                weSensitive.getAuditUserScope().forEach(scope -> {
                    scope.setSensitiveId(weSensitive.getId());
                });
                sensitiveAuditScopeService.saveBatch(weSensitive.getAuditUserScope());
            }
        }
    }

    /**
     * 批量删除敏感词设置
     *
     * @param ids 需要删除的敏感词设置ID
     * @return 结果
     */
    @Override
    public int deleteWeSensitiveByIds(Long[] ids) {
        List<WeSensitive> sensitiveList = this.baseMapper.selectWeSensitiveByIds(ids);
        sensitiveList.forEach(sensitive -> {
            sensitive.setDelFlag(1);
            sensitive.setUpdateBy(ScrmSecurityUtils.getUserName());
            sensitive.setUpdateById(ScrmSecurityUtils.getUserId());
            sensitive.setUpdateTime(DateUtils.getNowDate());
        });
        return this.baseMapper.batchUpdateWeSensitive(sensitiveList);
    }

    /**
     * 批量删除敏感词配置数据
     *
     * @param ids 敏感词设置ID
     * @return 结果
     */
    @Override
    @Transactional
    public void destroyWeSensitiveByIds(Long[] ids) {
        List<Long> idList = Arrays.stream(ids).collect(Collectors.toList());
        WeSensitive weSensitive = new WeSensitive();
        weSensitive.setDelFlag(1);
        boolean update = update(weSensitive, new LambdaQueryWrapper<WeSensitive>().in(WeSensitive::getId, idList));
        if (update) {
            //删除关联数据
            sensitiveAuditScopeService.deleteAuditScopeBySensitiveIds(idList);
        }
    }

    @Override
    public List<WeChatContactSensitiveMsgVo> getHitSensitiveList(WeSensitiveHitQuery query) {
        return weChatContactSensitiveMsgService.getListByQuery(query);
    }

    @Override
    public List<String> getScopeUsers(List<WeSensitiveAuditScope> scopeList) {
        List<String> users = Lists.newArrayList();
        if(CollectionUtil.isNotEmpty(scopeList)){
            scopeList.forEach(scope -> {
                if (scope.getScopeType().equals(WeConstans.USE_SCOP_BUSINESSID_TYPE_USER)) {
                    users.add(scope.getAuditScopeId());
                } else {
//                    List<SysUser> userList = qwSysUserClient.listByQuery(SysUser.builder().deptId(Long.parseLong(scope.getAuditScopeId())).build()).getData();
                    List<AdminUserAllDTO> userList = scrmSysUserClient.selectList("deptId", Long.parseLong(scope.getAuditScopeId()));

                    if(CollectionUtil.isEmpty(userList)){
                        throw new WeComException("未查询到有效员工");
                    }
                    List<String> userIdList = userList.stream().map(AdminUserAllDTO::getWeUserId).collect(Collectors.toList());
                    users.addAll(userIdList);
                }
            });
        }
        return users;
    }
}
