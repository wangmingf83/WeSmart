package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.user.tag;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 成员标签
 * @date 2021/12/7 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeUserTagVo extends WeResultVo {
    /**
     * 标签id
     */
    private String tagId;

    private String tagName;
}
