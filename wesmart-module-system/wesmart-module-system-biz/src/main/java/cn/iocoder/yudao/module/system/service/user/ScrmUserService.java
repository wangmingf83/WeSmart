package cn.iocoder.yudao.module.system.service.user;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.system.api.user.dto.WeUserDetailDto;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthRegisterReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserImportExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserImportRespVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import jakarta.validation.Valid;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户 Service 接口
 *
 * @author 芋道源码
 */
public interface ScrmUserService {

    AdminUserDO findSysUserByWeUserId(String weUserId);

    List<AdminUserDO> selectList(String field, Object value);

    AdminUserDO selectOne(String field, Object value);

    void updateUserChatStatus(List<String> weUserIds);

    List<AdminUserAllDTO> screenConditWeUser(String weUserIds, String deptIds, String positions);

    List<AdminUserAllDTO> getUserListByWeUserIds(List<String> weUserIds);

}
