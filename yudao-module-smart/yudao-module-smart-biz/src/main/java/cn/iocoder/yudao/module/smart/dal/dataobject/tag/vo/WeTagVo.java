package cn.iocoder.yudao.module.smart.dal.dataobject.tag.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author danmo
 * @description 标签出参
 * @date 2021/11/8 21:34
 **/
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeTagVo {

    @Schema(description = "标签Id")
    private String tagId;

    @Schema(description = "标签名称")
    private String tagName;
}
