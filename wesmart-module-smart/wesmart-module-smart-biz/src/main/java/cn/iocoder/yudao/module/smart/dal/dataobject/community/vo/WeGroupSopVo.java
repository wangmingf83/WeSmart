package cn.iocoder.yudao.module.smart.dal.dataobject.community.vo;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import cn.iocoder.yudao.module.common.enums.MessageNoticeType;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.WeMaterialVo;
import lombok.Data;

import java.util.List;

@Data
public class WeGroupSopVo extends BaseEntity {
    /**
     * 类型。该属性仅用于H5页面与老客标签建群混合列表的任务类型判断
     */
    @TableField(exist = false)
    private final Integer type = MessageNoticeType.SOP.getType();

    /**
     * id
     */
    private Long ruleId;

    /**
     * 规则名
     */
    private String ruleName;

    /**
     * 内容标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 素材列表
     */
    @TableField(exist = false)
    private List<WeMaterialVo> materialList;

    /**
     * 自上传图片列表
     */
    @TableField(exist = false)
    private List<String> picList;

    /**
     * 群聊
     */
    @TableField(exist = false)
    private List<WeGroup> groupList;

    /**
     * 执行人列表
     */
    @TableField(exist = false)
    private List<WeCommunityTaskEmplVo> scopeList;

    /**
     * 开始执行时间
     */
    private String startExeTime;

    /**
     * 结束执行时间
     */
    private String stopExeTime;


}
