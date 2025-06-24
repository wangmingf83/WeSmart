package cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsEstimateUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 预估朋友圈执行员工
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/26 19:23
 */
@Schema(description = "预估朋友圈执行员工")
@Data
public class WeMomentsEstimateUserVO extends WeMomentsEstimateUser {

    /**
     * 执行状态:0未执行，1已执行
     */
    private Integer executeStatus;
}
