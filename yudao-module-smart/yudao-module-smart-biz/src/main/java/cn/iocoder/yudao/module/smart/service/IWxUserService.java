package cn.iocoder.yudao.module.smart.service;

import cn.iocoder.yudao.module.smart.dal.dataobject.system.user.query.SysUserQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.user.WeUserDetailVo;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WxUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.weixin.WxAuthUserInfoVo;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信用户表(WxUser)
 *
 * @author danmo
 * @since 2022-07-01 13:42:38
 */
public interface IWxUserService extends IService<WxUser> {

    void saveOrUpdate(WxAuthUserInfoVo wxAuthUserInfoVo);

    WxUser getCustomerInfo(String openId, String unionId);

    AdminUserAllDTO findOrSynchSysUser(String weuserId);

    void addUser(SysUserQuery query);

    /**
     * 同步员工数据
     * @param detailVo
     */
    void syncAddOrUpdateUser(WeUserDetailVo detailVo);

    /**
     * 同步员工信息
     * @param msg
     */
    void syncUserHandler(JSONObject msg);

    /**
     * 同步员工和部门(发送同步消息到mq)
     */
    void syncUserAndDept();


}
