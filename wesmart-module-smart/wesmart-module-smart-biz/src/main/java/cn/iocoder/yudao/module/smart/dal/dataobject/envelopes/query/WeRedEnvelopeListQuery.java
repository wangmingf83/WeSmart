package cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 红包列表入参
 * @date 2023/3/21 10:48
 **/

@Schema
@Data
public class WeRedEnvelopeListQuery {


    @Schema(description = "场景类型 1-客户 2-客群 3-客户与客群 (多选逗号相隔)")
    private String sceneType;

    @Schema(description = "红包名称")
    private String name;

    @Schema(description = "状态 0-启用 1-停用")
    private Integer status;

    @Schema(description = "红包类型 0-企业红包 1-个人红包")
    private Integer redEnvelopesType;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "开始时间")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束时间")
    private Date endTime;
}

