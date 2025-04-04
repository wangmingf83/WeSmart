package cn.iocoder.yudao.module.system.dal.dataobject.area;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 行政区划 DO
 *
 * @author 圭图助手
 */
@TableName("system_area")
@KeySequence("system_area_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaDO extends BaseDO {

    /**
     * 区域ID
     */
    @TableId
    private Integer id;
    /**
     * 父ID
     */
    private Integer parentId;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 拼音首字母
     */
    private String ePrefix;
    /**
     * 拼音名称
     */
    private String eName;
    /**
     * 对外区域ID
     */
    private Long extId;
    /**
     * 区域对外名称
     */
    private String extName;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建人id
     */
    private Long createById;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新人id
     */
    private Long updateById;
    /**
     * 删除标识 0 正常 1 删除
     */
//    private Integer deleted;

}