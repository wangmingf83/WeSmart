package cn.iocoder.yudao.module.smart.dal.dataobject.community;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;


/**
 * 员工活码标签对象 we_emple_code_tag
 *
 * @author ruoyi
 * @date 2020-10-04
 */
@Data
@TableName("we_emple_code_tag")
public class WeEmpleCodeTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private Long id=SnowFlakeUtil.nextId();

    /** 标签id */
    @Schema(description = "标签id")
    private String tagId;

    /** 员工活码id */
    @Schema(description = "员工活码id")
    private Long empleCodeId;

    /** 0:正常;2:删除; */
    @Schema(description = "0:正常;1:删除")
    @TableLogic
    private Integer delFlag = 0;

    /** 标签名 */
    @Schema(description = "标签名")
    private String tagName;


}