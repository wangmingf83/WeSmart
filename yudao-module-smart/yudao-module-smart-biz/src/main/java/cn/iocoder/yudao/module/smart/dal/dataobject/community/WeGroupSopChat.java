package cn.iocoder.yudao.module.smart.dal.dataobject.community;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 群SOP和群关联对象
 */
@Data
@TableName("we_group_sop_chat")
public class WeGroupSopChat extends BaseEntity {

    @TableId
    private Long id;
    /**
     * 群sop id
     */
    private Long ruleId;

    /**
     * 实际群聊id
     */
    private String chatId;

    /**
     * 是否已完成
     */
    private boolean isDone;
}
