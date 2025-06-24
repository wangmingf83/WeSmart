package cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author danmo
 * @description 群发成员任务出参
 * @date 2021/10/23 15:09
 **/
@Schema
@Data
public class WeGroupMessageTaskVo {

    /**
     * 企业服务人员的userid
     */
    @Schema(description = "企业服务人员的userid")
    private String userId;

    /**
     * 企业服务人员的名称
     */
    @Schema(description = "企业服务人员的名称")
    private String userName;

    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;

    /**
     * 发送客户列表
     */
    @Schema(description = "发送客户列表")
    private List<String> customerList;

    /**
     * 发送群列表
     */
    @Schema(description = "发送群列表")
    private List<String> groupList;
}
