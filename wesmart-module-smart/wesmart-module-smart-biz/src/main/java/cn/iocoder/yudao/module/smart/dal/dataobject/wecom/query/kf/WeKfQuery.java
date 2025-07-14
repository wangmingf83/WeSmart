package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * @author danmo
 * @description 客服接口入参
 * @date 2021/12/13 10:27
 **/
@Schema
@Data
public class WeKfQuery extends WeBaseQuery {

    @Schema(description = "客服名称")
    private String name;

    @Schema(description = "客服头像临时素材")
    private String media_id;

    @Schema(description = "客服帐号ID")
    @NotNull(message = "客服帐号ID不能为空")
    private String open_kfid;

    @Schema(description = "场景值，字符串类型，由开发者自定义")
    private String scene;

    @Schema(description = "员工ID")
    private List<Integer> accountid_list;

    @Schema(description = "接待人员userid列表")
    private List<String> userid_list;

    @Schema(description = "接待人员部门id列表")
    private List<Long> department_id_list;
}
