package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 客户分析
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeSessionArchiveDetailVo {

    @Schema(description = "员工ID")
    private String userId;

    @Schema(description = "员工名称")
    private String userName;

    @Schema(description = "客户ID")
    private String customerId;

    @Schema(description = "客户名称")
    private String customerName;

    @Schema(description = "客户头像")
    private String customerAvatar;

    @Schema(description = "同意会话时间")
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date openChatTime;
}
