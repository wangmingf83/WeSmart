package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.iocoder.yudao.module.common.enums.WeKfStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 场景列表
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfRecordListVo{

    @Schema(description = "客服账号ID")
    @ExcelIgnore
    private String openKfId;

    @Schema(description = "连接池ID")
    @ExcelIgnore
    private String poolId;

    @Schema(description = "客服名称")
    @ExcelProperty("客服名称")
    private String kfName;

    @Schema(description = "客服头像")
    @ExcelIgnore
    private String kfAvatar;

    @Schema(description = "客户名称")
    @ExcelProperty("客户名称")
    private String customerName;

    @Schema(description = "客户头像")
    @ExcelIgnore
    private String customerAvatar;

    @Schema(description = "客户ID")
    @ExcelIgnore
    private String externalUserId;

    @Schema(description = "场景值")
    @ExcelIgnore
    private String scene;

    @Schema(description = "场景名称")
    @ExcelProperty("场景名称")
    private String sceneName;

    @Schema(description = "员工ID")
    @ExcelIgnore
    private String userId;

    @Schema(description = "员工名称")
    @ExcelProperty("员工名称")
    private String userName;

    @Schema(description = "是否为企业客户 0=是,1=否")
    @ExcelIgnore
    private Integer isQyCustomer = 1;

    @Schema(description = "是否为企业客户",hidden = true)
    @ExcelProperty(value = "是否为企业客户")
    private String isQyCustomerStr;

    @Schema(description = "接待状态 0-未处理,1-机器人,2-接待池,3-人工接待,4-已结束/未开始")
    @ExcelIgnore
    private Integer status;

    @Schema(description = "接待状态",hidden = true)
    @ExcelProperty(value = "接待状态")
    private String statusStr;

    @Schema(description = "接待方式: 1-人工客服 2-智能助手")
    @ExcelIgnore
    private Integer receptionType;

    @Schema(description = "接待方式",hidden = true)
    @ExcelProperty(value = "接待方式")
    private String receptionTypeStr;

    @Schema(description = "会话开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "会话开始时间")
    private Date sessionStartTime;

    @Schema(description = "会话结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "会话结束时间")
    private Date sessionEndTime;

    @Schema(description = "时长")
    @ExcelProperty("时长")
    private String duration;

    public String getDuration() {
        if(this.duration == null){
            return null;
        }
        long second = Math.round(Double.parseDouble(duration));
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second /60;            //转换分钟
        second = second % 60;                //剩余秒数
        if(hours>0){
            return hours + "h" + minutes + "m" + second + "s";
        }else{
            return minutes + "m" + second + "s";
        }
    }

    public String getIsQyCustomerStr() {
        if(isQyCustomer == 0){
            return "是";
        }else if(isQyCustomer == 1){
            return "否";
        }
        return "";
    }

    public String getStatusStr() {
        WeKfStatusEnum weKfStatusEnum = WeKfStatusEnum.parseEnum(status);
        return weKfStatusEnum.getMsg();
    }

    public String getReceptionTypeStr() {
        if(receptionType == 1){
            return "人工客服";
        }else if(receptionType == 2){
            return "智能助手";
        }
        return "";
    }

}
