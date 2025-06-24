package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.living;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

/**
 * @author danmo
 * @description 直播接口入参
 * @date 2022/10/10 10:27
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeLivingQuery extends WeBaseQuery {

    /**
     * 直播id，仅允许取消预约状态下的直播id
     */
    @Schema(description = "直播id，仅允许取消预约状态下的直播id")
    private String livingid;

    /**
     * 上一次调用时返回的next_key
     */
    @Schema(description = "上一次调用时返回的next_key，初次调用可以填0")
    private String next_key = "0";
}
