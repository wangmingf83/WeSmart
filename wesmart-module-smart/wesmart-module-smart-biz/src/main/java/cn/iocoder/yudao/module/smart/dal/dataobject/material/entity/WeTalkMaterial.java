package cn.iocoder.yudao.module.smart.dal.dataobject.material.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("we_talk_material")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "话术素材关联表")
public class WeTalkMaterial {

    private Long talkId;

    private Long materialId;

    private Integer sort;

}
