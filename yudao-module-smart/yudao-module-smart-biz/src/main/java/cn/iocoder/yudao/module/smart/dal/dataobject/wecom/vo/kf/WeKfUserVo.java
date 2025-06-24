package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 客服
 * @date 2021/12/13 10:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeKfUserVo extends WeResultVo {

    /**
     * 接待人员的userid
     */
    private String userId;


    /**
     * 接待人员部门的id
     */
    private Integer departmentId;

    /**
     * 接待人员的接待状态。0:接待中,1:停止接待
     */
    private Integer status;
}
