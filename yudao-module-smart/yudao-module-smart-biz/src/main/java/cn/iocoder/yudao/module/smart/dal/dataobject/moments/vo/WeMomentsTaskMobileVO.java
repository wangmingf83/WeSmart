package cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 朋友圈移动端列表展示
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/20 18:54
 */
@Data
public class WeMomentsTaskMobileVO {


    /**
     * 朋友圈任务Id
     */
    @Schema(description = "朋友圈任务Id")
    private Long weMomentsTaskId;

    /**
     * 朋友圈Id
     */
    @Schema(description = "朋友圈Id")
    private String momentsId;

    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    private String name;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 执行时间
     */
    @Schema(description = "执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime executeTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime executeEndTime;

    /**
     * 朋友圈文本内容
     */
    @Schema(description = "朋友圈文本内容")
    private String content;

    /**
     * 客户标签
     * 字段隐藏，不对前端展示
     * 1.Springboot默认是通过jackson来转换JSON的,所以这里要使用jackson的注解。
     * 2.也可以fastjson的注解，但是要更换SpringBoot的转换json的方式
     */
    @JsonIgnore
    @Schema(description = "客户标签")
    private String customerTag;

    /**
     * 客户标签集合
     */
    @Schema(description = "客户标签集合")
    private List<String> customerTags;

    /**
     * 客户数
     */
    @Schema(description = "客户数")
    private long customerNum;

    /**
     * 执行状态:0未执行，1已执行
     */
    @Schema(description = "执行状态:0未执行，1已执行")
    private Integer executeStatus;

    /**
     * 附件详情
     */
    @Schema(description = "附件详情")
    List<WeMaterial> materialList;

    /**
     * 朋友圈任务状态：1未开始，2进行中，3已结束
     */
    @Schema(description = "朋友圈任务状态：1未开始，2进行中，3已结束")
    private Integer status;


}
