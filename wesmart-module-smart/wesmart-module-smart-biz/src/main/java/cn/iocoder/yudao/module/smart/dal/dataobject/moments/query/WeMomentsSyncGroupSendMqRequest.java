package cn.iocoder.yudao.module.smart.dal.dataobject.moments.query;


import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import lombok.Data;

/**
 * 同步成员群发MQ参数
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/04 15:27
 */
@Data
public class WeMomentsSyncGroupSendMqRequest extends WeMomentsSyncGroupSendRequest {

    /**
     * 执行成员
     */
    private AdminUserAllDTO user;

    /**
     * 执行次数
     */
    private Integer num;


}
