package cn.iocoder.yudao.module.smart.dal.dataobject.robot.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo 新增机器人入参
 * @date 2022年11月09日 11:25
 */
@Schema
@Data
public class WeRobotQuery {

    @Schema(description = "群名称")
    private String groupName;

    @Schema(description = "开始时间")
    private Date startTime;

    @Schema(description = "结束时间")
    private Date endTime;
}
