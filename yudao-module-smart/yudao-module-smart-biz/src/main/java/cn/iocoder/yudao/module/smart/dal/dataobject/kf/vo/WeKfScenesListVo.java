package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfScenes;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 场景列表
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfScenesListVo extends WeKfScenes {

    @Schema(description = "客服名称")
    private String kfName;

    @Schema(description = "客服头像")
    private String kfAvatar;

    @Schema(description = "访问客户数")
    private Integer accessCnt;

    @Schema(description = "咨询客户数")
    private Integer consultCnt;

    @Schema(description = "接待客户数")
    private Integer receptionCnt;
}
