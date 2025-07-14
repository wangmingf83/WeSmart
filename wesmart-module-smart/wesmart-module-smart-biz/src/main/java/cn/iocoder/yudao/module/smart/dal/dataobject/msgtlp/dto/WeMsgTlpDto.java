package cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.dto;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeMsgTlp;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.entity.WeTlpMaterial;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author 狗头军师
 * @Description TODO
 * @Date 2022/10/13 16:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeMsgTlpDto extends WeMsgTlp {

    @Schema(description = "员工ID列表")
    private List<String> userIdList;

    @Schema(description = "员工名称列表")
    private List<String> userNameList;

    @Schema(description = "欢迎语素材列表")
    @NotNull(message = "欢迎语素材列表不能为空")
    private List<WeMessageTemplate> attachmentList;

    @NotNull(message = "排序不能为空")
    private List<WeTlpMaterial> weTlpMaterialList;

}
