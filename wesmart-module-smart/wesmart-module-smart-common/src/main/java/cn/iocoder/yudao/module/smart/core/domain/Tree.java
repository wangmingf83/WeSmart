package cn.iocoder.yudao.module.smart.core.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree<T> {

    @Schema(name = "id", description = "id")
    private Long id;

    /**
     * 名称
     */
    @Schema(name = "label", description = "名称")
    private String name;

    /**
     * 名称
     */
    @Schema(description = "可删除标识 0 可删除 1 不可删除")
    private Integer flag;

    /**
     * 父的叶子信息
     */
    @Schema(name = "children", description = "父的叶子信息")
    private List<Tree<T>> children;

    /**
     * 父id
     */
    @Schema(name = "parentId", description = "父id")
    private Long parentId;

    @Schema(name = "hasParent", description = "是否有父节点")
    private boolean hasParent = false;

    @Schema(name = "false", description = "是否有叶子节点")
    private boolean hasChildren = false;

    public void initChildren() {
        this.children = new ArrayList<>();
    }

}
