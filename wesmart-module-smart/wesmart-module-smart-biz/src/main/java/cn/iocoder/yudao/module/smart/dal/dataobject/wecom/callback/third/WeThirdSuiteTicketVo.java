package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.third;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 推送suite_ticket
 * @date 2022/3/7 22:00
 **/
@Schema
@Data
public class WeThirdSuiteTicketVo extends WeThirdBackBaseVo{

    @Schema(description = "Ticket内容，最长为512字节")
    private String SuiteTicket;
}
