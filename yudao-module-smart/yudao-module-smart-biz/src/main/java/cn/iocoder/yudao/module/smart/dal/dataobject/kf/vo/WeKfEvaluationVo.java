package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年11月21日 13:59
 */
@Data
@Schema
public class WeKfEvaluationVo {

    @Schema(description = "评价类型 101-好评 102-一般 103-差评")
    private String evaluationType;

    @Schema(description = "评价内容")
    private String content;

}
