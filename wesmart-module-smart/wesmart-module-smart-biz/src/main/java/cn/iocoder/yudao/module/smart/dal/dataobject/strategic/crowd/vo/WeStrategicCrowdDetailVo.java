package cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.vo;

import com.alibaba.fastjson.JSONArray;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.WeStrategicCrowdSwipe;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

@Schema
@Data
public class WeStrategicCrowdDetailVo {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "分组ID")
    private Long groupId;

    @Schema(description = "状态 1、待计算 2、计算中 3、计算完成 4、计算失败")
    private Integer status;

    @Schema(description = "人群数量")
    private Integer crowdNum;


    @Schema(description = "更新方式 1：手动 2：自动")
    private Integer type = 1;

    @Schema(description = "筛选条件")
    private List<WeStrategicCrowdSwipe> swipe;

    public void setStr2Swipe(String swipe) {
        this.swipe =  JSONArray.parseArray(swipe,WeStrategicCrowdSwipe.class);
    }
}
