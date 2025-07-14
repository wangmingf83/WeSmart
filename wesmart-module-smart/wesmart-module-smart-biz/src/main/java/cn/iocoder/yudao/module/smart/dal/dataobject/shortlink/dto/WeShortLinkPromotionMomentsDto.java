package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.dto;



import cn.iocoder.yudao.framework.security.core.LoginUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 短链推广-朋友圈
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/03/14 9:48
 */
@Data
public class WeShortLinkPromotionMomentsDto {

    /**
     * 短链推广Id
     */
    @Schema(description = "短链推广Id")
    private Long shortLinkPromotionId;

    /**
     * 短链推广模板Id-朋友圈
     */
    @Schema(description = "短链推广模板Id-朋友圈")
    private Long businessId;

    /**
     * 当前用户
     */
    @Schema(description = "当前用户", hidden = true)
    private LoginUser loginUser;

    /**
     * 朋友圈文本内容
     */
    @Schema(description = "朋友圈文本内容")
    private String content;

    /**
     * 可见类型:0:部分可见;1:公开
     */
    @Schema(description = "可见类型:0:部分可见;1:公开")
    private Integer scopeType;

    /**
     * 客户标签，多个使用逗号隔开
     */
    @Schema(description = "客户标签")
    private String customerTag;

    /**
     * 未发送员工，使用逗号隔开
     */
    @Schema(description = "未发送员工")
    private String noAddUser;

    /**
     * 内容类型
     */
    @Schema(description = "内容类型")
    private String contentType;

    /**
     * 附件
     */
    @Schema(description = "附件")
    private List<OtherContent> otherContent;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtherContent {
        //附件类型：1:image 2:video 3:link  4:text
        private String annexType;

        //资源url
        private String annexUrl;

        //如视频封面,图文标题,文字内容
        private String other;

        //文章标题
        private String title;
    }


}
