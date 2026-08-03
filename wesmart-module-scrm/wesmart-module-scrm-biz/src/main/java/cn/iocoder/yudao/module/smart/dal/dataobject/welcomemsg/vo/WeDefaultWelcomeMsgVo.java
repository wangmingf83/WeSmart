package cn.iocoder.yudao.module.smart.dal.dataobject.welcomemsg.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import lombok.Data;

import java.util.List;

@Data
public class WeDefaultWelcomeMsgVo {
    /**
     * 素材新增编辑传入(欢迎语和附件)
     */
    @TableField(exist = false)
    private List<WeMessageTemplate> attachments;

}
