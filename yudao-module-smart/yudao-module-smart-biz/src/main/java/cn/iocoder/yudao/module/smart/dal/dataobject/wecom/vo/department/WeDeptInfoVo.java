package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.department;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.department.WeDeptEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 部门详情
 * @date 2021/12/7 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeDeptInfoVo extends WeResultVo {
    /**
     * 部门信息
     */
    private WeDeptEntity department;
}
