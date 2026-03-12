package cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 红包详情
 */
@Schema
@Data
@Builder
public class H5RedEnvelopesDetailDto {
    //机构名称
    @Schema(description = "机构名称")
    private String corpName;
    //企业logo
    @Schema(description = "企业logo")
    private String logo;
    //红包类
    @Schema(description = "红包类型")
    private Integer redEnvelopesType;
    //当前收取金额
    @Schema(description = "当前收取金额")
    private int currentAcceptMoney;
    //红包总额
    @Schema(description = "红包总额")
    private int totalMoney;
    //已领取金额
    @Schema(description = "已领取金额")
    private int accpectMoney;

    @Schema(description = "红包个数")
    private Integer redEnvelopeNum;

    //接受人
    @Schema(description = "接受人信息")
    private List<AccpestCustomer> accpestCustomerList;

    //已领取个数
    @Schema(description = "已领取个数")
    private int acceptNum;

    //未领取个数
    @Schema(description = "未领取个数")
    private int noAcceptNum;

    @Schema
    @Data
    public static class AccpestCustomer {
        //头像
        @Schema(description = "头像")
        private String avatar;
        //领取人姓名
        @Schema(description = "领取人姓名")
        private String customerName;
        //领取时间
        @Schema(description = "领取时间")
         @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date accpectTime;
        //领取金额
        @Schema(description = "领取金额")
        private int accpectMoney;
    }

}

