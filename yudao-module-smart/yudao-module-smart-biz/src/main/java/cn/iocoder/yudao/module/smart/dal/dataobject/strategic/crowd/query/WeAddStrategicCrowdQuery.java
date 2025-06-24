package cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query;

import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.WeStrategicCrowdSwipe;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * @author danmo
 * @description 策略人群新增入参
 * @date 2021/11/7 13:49
 **/
@Schema
@Data
public class WeAddStrategicCrowdQuery {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "名称")
    @NotEmpty(message = "名称不能为空")
    private String name;

    @Schema(description = "分组ID")
    @NotNull(message = "分组ID不能为空")
    private Long groupId;

    @Schema(description = "更新方式 1：手动 2：自动")
    private Integer type = 1;

    @Schema(description = "筛选条件")
    private List<WeStrategicCrowdSwipe> swipe;

    public String getSwipe2Str() {
        return JSONObject.toJSONString(swipe);
    }
}
