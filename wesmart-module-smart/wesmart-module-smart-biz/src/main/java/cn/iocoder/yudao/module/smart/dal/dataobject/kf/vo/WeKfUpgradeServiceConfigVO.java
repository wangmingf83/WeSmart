package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.WeGroupSimpleVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.system.user.vo.SysUserVo;
import lombok.Data;

import java.util.List;

/**
 * 客服升级服务
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/08/25 11:31
 */
@Data
public class WeKfUpgradeServiceConfigVO {

    /**
     * 员工集合
     */
    private List<SysUserVo> userList;

    /**
     * 群集合
     */
    private List<WeGroupSimpleVo> groupList;

}
