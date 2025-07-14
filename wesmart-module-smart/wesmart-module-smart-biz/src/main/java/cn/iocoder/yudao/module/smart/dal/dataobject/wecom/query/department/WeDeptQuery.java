package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.department;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 部门入参
 * @date 2021/12/2 18:27
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeDeptQuery extends WeBaseQuery {
    /**
     * 部门id，32位整型，指定时必须大于1。若不填该参数，将自动生成id
     */
    private Long id;
}
