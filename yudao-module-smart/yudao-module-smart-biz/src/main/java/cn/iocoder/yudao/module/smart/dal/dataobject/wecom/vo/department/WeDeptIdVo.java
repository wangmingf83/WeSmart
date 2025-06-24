package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.department;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.department.WeDeptEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @Description 部门Id
 * @date 2021/12/7 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeDeptIdVo extends WeResultVo {
    /**
     * 部门id
     */
    private List<WeDeptEntity> departmentId;
}
