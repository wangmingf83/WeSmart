package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.tag;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.tag.WeCorpTagGroupEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 获取客户列表入参
 * @date 2021/12/2 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeCorpTagVo extends WeResultVo {
    /**
     * 标签组列表
     */
    private WeCorpTagGroupEntity tagGroup;
}
