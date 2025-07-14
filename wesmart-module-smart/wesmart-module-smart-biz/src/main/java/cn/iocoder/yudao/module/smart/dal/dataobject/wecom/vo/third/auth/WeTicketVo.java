package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.third.auth;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 获取jsapi_ticket
 * @date 2022/3/4 11:34
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeTicketVo extends WeResultVo {
    /**
     * 生成签名所需的jsapi_ticket，最长为512字节
     */
    @Schema(description = "生成签名所需的jsapi_ticket")
    private String ticket;

    /**
     * 有效期（秒）
     */
    @Schema(description = "有效期（秒）")
    private Long expiresIn;
}
