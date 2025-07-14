package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @description 客服知识库
 * @date 2022/10/11 10:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeKfKnowledgeGroupListVo extends WeResultVo {

    /**
     * 	分页游标，再下次请求时填写以获取之后分页的记录
     */
    @Schema(description = "分页游标，再下次请求时填写以获取之后分页的记录")
    private String nextCursor;

    @Schema(description = "是否还有更多数据。0-没有 1-有")
    private Integer hasMore;

    @Schema(description = "分组列表")
    private List<KnowledgeGroupVo> groupList;

    @Schema
    @Data
    public static class KnowledgeGroupVo {

        @Schema(description = "分组ID")
        private String groupId;

        @Schema(description = "分组名")
        private String name;

        @Schema(description = "是否为默认分组。0-否 1-是。默认分组为系统自动创建，不可修改/删除")
        private String isDefault;
    }
}
