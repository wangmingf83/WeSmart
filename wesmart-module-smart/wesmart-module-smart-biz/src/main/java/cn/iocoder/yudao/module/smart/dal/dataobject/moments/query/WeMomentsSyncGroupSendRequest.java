package cn.iocoder.yudao.module.smart.dal.dataobject.moments.query;

import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 同步成员群发
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/13 9:38
 */
@Data
public class WeMomentsSyncGroupSendRequest {

    /**
     * 朋友圈任务Id
     */
    @NotNull(message = "朋友圈任务Id必填")
    private Long weMomentsTaskId;

    /**
     * 发送时间
     */
    @NotNull(message = "发送时间必填")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sendTime;

    /**
     * 执行成员
     */
    private AdminUserAllDTO user;
}
