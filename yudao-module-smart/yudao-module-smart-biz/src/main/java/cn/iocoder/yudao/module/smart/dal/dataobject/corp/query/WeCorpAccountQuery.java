package cn.iocoder.yudao.module.smart.dal.dataobject.corp.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 企业信息
 * @date 2022/4/11 23:11
 **/
@Schema
@Data
public class WeCorpAccountQuery {

    @Schema(description = "企业ID")
    private String corpId;

}
