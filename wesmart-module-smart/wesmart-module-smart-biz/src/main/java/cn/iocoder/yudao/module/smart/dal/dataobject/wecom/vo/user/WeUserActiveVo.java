package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.user;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 获取企业活跃成员数
 * @date 2021/12/7 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeUserActiveVo extends WeResultVo {
    /**
     * 活跃成员数
     */
    private Integer activeCnt;
}
