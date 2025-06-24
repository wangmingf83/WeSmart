package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.user.tag;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 成员标签入参
 * @date 2021/12/2 18:27
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeUserTagQuery extends WeBaseQuery {
    /**
     * 标签id，非负整型，指定此参数时新增的标签会生成对应的标签id
     */
    private String tagid;
}
