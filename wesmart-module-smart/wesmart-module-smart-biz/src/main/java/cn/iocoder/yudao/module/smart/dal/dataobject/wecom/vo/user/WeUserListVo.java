package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.user;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @Description 成员列表
 * @date 2021/12/7 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeUserListVo extends WeResultVo {
    /**
     * 成员列表
     */
    private List<WeUserDetailVo> userList;
}
