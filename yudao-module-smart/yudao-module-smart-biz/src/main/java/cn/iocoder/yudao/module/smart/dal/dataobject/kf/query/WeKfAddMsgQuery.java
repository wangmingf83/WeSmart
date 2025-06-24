package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.wx.WxBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @author danmo
 * @Description 客服任务入参
 * @date 2021/12/13 10:57
 **/
@Schema
@Data
public class WeKfAddMsgQuery extends WxBaseQuery {
    /**
     * 客服ID
     */
    @NotBlank(message = "客服ID不能为空")
    @Schema(description = "客服ID")
    private String poolId;


    @NotBlank(message = "类型不能为空")
    @Schema(description = "评价类型")
    private String evaluationType;
    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    @Schema(description = "内容")
    private String content;
}
