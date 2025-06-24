package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.moment;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @Description 评论列表
 * @date 2021/12/3 10:54
 **/
@Schema
@Data
public class WeMomentCommentListVo extends WeResultVo {

    @Schema(description = "评论列表")
    private List<Comment> commentList;

    @Schema(description = "点赞列表")
    private List<Comment> likeList;


    @Schema
    @Data
    public static class Comment {
        @Schema(description = "外部联系人userid")
        private String externalUserId;

        @Schema(description = "企业成员userid")
        private String userId;

        @Schema(description = "触发时间")
        private Long createTime;
    }
}
