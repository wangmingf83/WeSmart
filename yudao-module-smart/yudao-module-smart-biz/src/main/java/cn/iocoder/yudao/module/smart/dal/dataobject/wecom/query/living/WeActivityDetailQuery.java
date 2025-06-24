package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.living;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @description 直播接口入参
 * @date 2022/10/10 10:27
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeActivityDetailQuery extends WeBaseQuery {

    /**
     * 活动直播特定参数，活动直播简介
     */
    @Schema(description = "活动直播特定参数，活动直播简介")
    private String description;

    /**
     * 活动直播特定参数，活动直播附图的mediaId列表，最多支持传5张，超过五张取前五张
     */
    @Schema(description = "活动直播特定参数，活动直播附图的mediaId列表，最多支持传5张，超过五张取前五张")
    private List<String> image_list;
}
