package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import cn.iocoder.yudao.module.common.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客户聊天总数
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeSessionGroupTotalCntVo {

    @Schema(description = "日期")
    @Excel(name = "日期")
    private String xTime;

    @Schema(description = "群聊总数")
    @Excel(name = "群聊总数")
    private Integer chatTotal;

    @Schema(description = "群聊消息数")
    @Excel(name = "群聊消息数")
    private Integer msgTotal;

    @Schema(description = "发送消息群成员数")
    @Excel(name = "发送消息群成员数")
    private Integer memberHasMsg;
}
