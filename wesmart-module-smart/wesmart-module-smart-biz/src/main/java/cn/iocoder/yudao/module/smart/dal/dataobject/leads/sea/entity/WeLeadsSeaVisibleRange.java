package cn.iocoder.yudao.module.smart.dal.dataobject.leads.sea.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公海可见范围
 *
 * @author WangYX
 * @since 2023-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("we_leads_sea_visible_range")
public class WeLeadsSeaVisibleRange extends BaseEntity {

    /**
     * 主键Id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 公海池Id
     */
    private Long seaId;

    /**
     * 类型(0部门 1岗位 2员工)
     */
    private Integer type;

    /**
     * 数据Id
     */
    private String dataId;

    /**
     * 数据名称
     */
    private String dataName;

    /**
     * 是否管理员（1是，0否）
     */
    private Boolean isAdmin;

    /**
     * 删除标识
     */
    private Integer delFlag;

}
