package cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("we_tlp_material")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "模板素材关联表")
public class WeTlpMaterial {

    private Long tlpId;

    private Long materialId;
}
