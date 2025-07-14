package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.media;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 * @description 获取素材入参
 * @date 2022/05/04 11:54
 **/
@Data
public class WeGetMediaQuery extends WeBaseQuery {
    /**
     * 素材id
     */
    @NotNull(message = "素材ID不能为空")
    private String mediaId;

    public WeGetMediaQuery() {
    }

    public WeGetMediaQuery(String mediaId) {
        this.mediaId = mediaId;
    }
}
