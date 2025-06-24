package cn.iocoder.yudao.module.smart.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.IWeMessageNotificationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.message.MessageReadEnum;

import cn.iocoder.yudao.module.smart.dal.dataobject.message.entity.WeMessageNotification;
import cn.iocoder.yudao.module.smart.dal.dataobject.message.vo.WeMessageNotificationVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 消息通知
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/20 14:40
 */
@Tag(name = "消息通知")
@RestController
@RequestMapping("/message/notification")
public class WeMessageNotificationController extends BaseController {

    @Resource
    private IWeMessageNotificationService weMessageNotificationService;

    /**
     * 消息通知未读数量
     *
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/07/20 14:46
     */
    @GetMapping("/num")
    public AjaxResult num() {
        LoginUser sysUser = ScrmSecurityUtils.getLoginUser();
        LambdaQueryWrapper<WeMessageNotification> queryWrapper = Wrappers.lambdaQuery(WeMessageNotification.class);
        queryWrapper.eq(WeMessageNotification::getWeUserId, sysUser.getWeUserId());
        queryWrapper.eq(WeMessageNotification::getIsRead, MessageReadEnum.UN_READ.getCode());
        queryWrapper.eq(WeMessageNotification::getDelFlag, Constants.COMMON_STATE);
        long count = weMessageNotificationService.count(queryWrapper);
        return AjaxResult.success(count);
    }

    /**
     * 消息通知未读列表
     *
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/07/20 14:45
     */
    @GetMapping("/unread/list")
    public TableDataInfo unreadList() {
        startPage();
        LambdaQueryWrapper<WeMessageNotification> queryWrapper = Wrappers.lambdaQuery(WeMessageNotification.class);
        queryWrapper.eq(WeMessageNotification::getWeUserId, ScrmSecurityUtils.getLoginUser().getWeUserId());
        queryWrapper.eq(WeMessageNotification::getDelFlag, Constants.COMMON_STATE);
        queryWrapper.orderByDesc(WeMessageNotification::getCreateTime);
        List<WeMessageNotification> list = weMessageNotificationService.list(queryWrapper);
        TableDataInfo dataTable = getDataTable(list);
        List<WeMessageNotificationVo> vos = BeanUtil.copyToList(list, WeMessageNotificationVo.class);
        dataTable.setRows(vos);
        return dataTable;
    }

    /**
     * 消息通知已读列表
     *
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/07/20 14:45
     */
    @GetMapping("/read/list")
    public TableDataInfo readList() {
        startPage();
        LambdaQueryWrapper<WeMessageNotification> queryWrapper = Wrappers.lambdaQuery(WeMessageNotification.class);
        queryWrapper.eq(WeMessageNotification::getWeUserId, ScrmSecurityUtils.getLoginUser().getWeUserId());
        queryWrapper.eq(WeMessageNotification::getDelFlag, Constants.COMMON_STATE);
        queryWrapper.orderByDesc(WeMessageNotification::getCreateTime);
        List<WeMessageNotification> list = weMessageNotificationService.list(queryWrapper);
        TableDataInfo dataTable = getDataTable(list);
        List<WeMessageNotificationVo> vos = BeanUtil.copyToList(list, WeMessageNotificationVo.class);
        dataTable.setRows(vos);
        return dataTable;
    }

    /**
     * 已读
     *
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/07/20 14:45
     */
    @PostMapping("/read")
    public AjaxResult read() {
        String weUserId = ScrmSecurityUtils.getLoginUser().getWeUserId();
        LambdaUpdateWrapper<WeMessageNotification> wrapper = Wrappers.lambdaUpdate(WeMessageNotification.class);
        wrapper.set(WeMessageNotification::getIsRead, MessageReadEnum.READ.getCode());
        wrapper.set(WeMessageNotification::getUpdateTime, new Date());
        wrapper.eq(WeMessageNotification::getWeUserId, weUserId);
        weMessageNotificationService.update(wrapper);
        return AjaxResult.success();
    }
}

