package cn.iocoder.yudao.module.smart.dal.dataobject.kf;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 客服欢迎语
 * @date 2022/1/18 21:57
 **/
@Schema
@Data
public class WeKfWelcomeInfo {

    @Schema(description = "工作周期")
    private String workCycle;

    @Schema(description = "开始时间")
    private String beginTime;

    @Schema(description = "结束时间")
    private String endTime;

    @Schema(description = "类型 1-文本 2-菜单")
    private Integer type;

    @Schema(description = "欢迎语")
    private String content;

    @Schema(description = "菜单列表")
    private List<WeKfMenu> menuList;


}
