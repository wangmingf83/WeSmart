package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.user;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @Description 邀请成员
 * @date 2021/12/7 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeUserInviteVo extends WeResultVo {
    /**
     * 非法成员列表
     */
    private List<String> invalidUser;

    /**
     * 非法部门列表
     */
    private List<String> invalidParty;

    /**
     * 非法标签列表
     */
    private List<String> invalidTag;
}
