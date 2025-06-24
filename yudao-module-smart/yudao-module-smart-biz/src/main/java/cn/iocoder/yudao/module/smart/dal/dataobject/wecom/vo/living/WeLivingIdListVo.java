package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @description 创建直播返回对象
 * @date 2022年10月11日 16:09
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeLivingIdListVo extends WeResultVo {

    /**
     * 当前数据最后一个key值
     */
    @Schema(description = "当前数据最后一个key值")
    private String nextCursor;
    /**
     * 直播ID列表
     */
    @Schema(description = "直播ID列表")
    private List<String> livingIdList;
}
