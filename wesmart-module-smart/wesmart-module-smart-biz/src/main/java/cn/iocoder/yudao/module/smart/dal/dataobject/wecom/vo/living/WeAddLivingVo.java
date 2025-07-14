package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 创建直播返回对象
 * @date 2022年10月11日 16:09
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeAddLivingVo extends WeResultVo {

    /**
     * 直播id，通过此id可调用“进入直播”接口(包括小程序接口和JS-SDK接口)，以实现主播到点后的开播操作，以及观众进入直播详情预约和观看直播
     */
    @Schema(description = "直播id")
    private String livingId;
}
