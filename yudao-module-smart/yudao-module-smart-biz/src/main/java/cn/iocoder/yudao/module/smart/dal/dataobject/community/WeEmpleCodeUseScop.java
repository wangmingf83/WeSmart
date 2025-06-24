package cn.iocoder.yudao.module.smart.dal.dataobject.community;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;

import lombok.Data;


/**
 * 员工活码使用人对象 we_emple_code_use_scop
 *
 * @author ruoyi
 * @date 2020-10-04
 */
@Data
@TableName("we_emple_code_use_scop")
public class WeEmpleCodeUseScop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @TableId
    private Long id = SnowFlakeUtil.nextId();

    /** 员工活码id */
    private Long empleCodeId;

    /** 业务id类型1:组织机构id,2:成员id */
    private Integer businessIdType;

    /** 活码类型下业务使用人的id */
    private String businessId;

    /** 0:正常;2:删除; */
    private Integer delFlag = 0;

    /** 活码使用人员名称 */
    private String businessName;

    /** 活码使用人员手机号 */
    @TableField(exist = false)
    private String mobile;


}

