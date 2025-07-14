package cn.iocoder.yudao.module.smart.dal.dataobject.qr.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 活码列表入参
 * @date 2021/11/9 13:58
 **/
@Schema
@Data
public class WeQrCodeListQuery extends BaseEntity {


    @Schema(description = "活码Id")
    private Long qrId;

    @Schema(description = "分组id")
    private String groupId;

    @Schema(description = "活码名称")
    private String qrName;

    @Schema(description = "员工ID")
    private String qrUserIds;

}
