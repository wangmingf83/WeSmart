package cn.iocoder.yudao.module.smart.dal.dataobject.leads.sea.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 线索公海
 * </p>
 *
 * @author WangYX
 * @since 2023-04-03
 */
@Data
public class WeLeadsSeaListVo {

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    private Long id;

    /**
     * 公海名称
     */
    @Schema(description = "公海名称")
    private String name;

    /**
     * 公海可见范围
     */
    private List<String> visibleRange;

    /**
     * 公海线索数
     */
    @Schema(description = "公海线索数")
    private Integer num;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private String createBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建者Id
     */
    @Schema(description = "创建者Id")
    private Long createById;


}
