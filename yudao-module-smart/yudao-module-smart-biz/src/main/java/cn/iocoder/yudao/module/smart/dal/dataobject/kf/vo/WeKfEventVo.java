package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.WeKfUser;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author leejoker
 * @version 1.0
 * @date 2022/1/24 19:13
 */
@Schema
@Data
public class WeKfEventVo {
    @Schema(description = "主键id")
    private String id;

    @Schema(description = "咨询客户id")
    private String customerId;

    @Schema(description = "咨询客户")
    private String customer;

    @Schema(description = "咨询客户头像")
    private String customerAvatar;

    @Schema(description = "是否为企业客户: 0-否 1-是")
    private Integer corpCustomer;

    @Schema(description = "客服id")
    private String kfId;

    @Schema(description = "客服名称")
    private String name;

    @Schema(description = "客服头像")
    private String avatar;

    @Schema(description = "接待方式: 1-人工客服 2-智能助手")
    private Integer receptionType;

    @Schema(description = "接待员工")
    private WeKfUser user;

    @Schema(description = "咨询开始时间")
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    @Schema(description = "咨询结束时间")
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    @Schema(description = "咨询时长")
    private String costTime;
}
