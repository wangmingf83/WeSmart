package cn.iocoder.yudao.module.smart.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 站点统计
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/10/14 13:46
 */
@Data
public class WeFormSurveySiteStas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    @TableField("belong_id")
    private Long belongId;

    /**
     * 总访问量
     */
    @Schema(description = "总访问量")
    @TableField("total_visits")
    private Integer totalVisits;


    /**
     * 总访问用户量
     */
    @Schema(description = "总访问用户量")
    @TableField("total_user")
    private Integer totalUser;


    /**
     * 有效收集量
     */
    @Schema(description = "有效收集量")
    @TableField("collection_volume")
    private Integer collectionVolume;


}
