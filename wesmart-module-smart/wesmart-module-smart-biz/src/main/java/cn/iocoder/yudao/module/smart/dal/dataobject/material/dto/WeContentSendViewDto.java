package cn.iocoder.yudao.module.smart.dal.dataobject.material.dto;

import lombok.Data;

@Data
public class WeContentSendViewDto {
    private Long talkId;
    private Long contentId;
    private Long sendTotalNum;
    private Long viewTotalNum;
    private Long viewByTotalNum;
}
