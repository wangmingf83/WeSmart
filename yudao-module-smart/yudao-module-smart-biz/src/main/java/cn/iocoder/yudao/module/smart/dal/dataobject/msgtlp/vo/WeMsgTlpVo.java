package cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeMsgTlp;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 欢迎语新增入参
 * @date 2022/03/26 13:49
 **/
@Schema
@Data
public class WeMsgTlpVo extends WeMsgTlp {

    @Schema(description = "欢迎语素材列表")
    private List<WeMessageTemplate> attachments;


    private Long attachTotalNum;

    private List<WeMaterial> weMaterialList;
}
