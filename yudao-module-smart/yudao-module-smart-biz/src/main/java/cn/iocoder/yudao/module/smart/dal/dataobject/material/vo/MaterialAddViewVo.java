package cn.iocoder.yudao.module.smart.dal.dataobject.material.vo;

import lombok.Data;

@Data
public class MaterialAddViewVo {
    private Long sendUserId;
    private String openid;
    private String unionid;

    private Long talkId;

    private Long contentId;

    private Long viewWatchTime;

    private Integer resourceType;


}
