package cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.talk;

import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeContentTalk;
import lombok.Data;

@Data
public class WeContentTalkVo extends WeContentTalk {
    private Integer materialNum = 0;
    private Integer sendTotalNum = 0;
    private Integer viewTotalNum = 0;
    private Integer viewByTotalNum = 0;
    private String  materialIds;
}
