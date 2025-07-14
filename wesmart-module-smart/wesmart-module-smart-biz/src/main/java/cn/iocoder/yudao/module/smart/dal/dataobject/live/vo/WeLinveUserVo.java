package cn.iocoder.yudao.module.smart.dal.dataobject.live.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.live.WeLive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeLinveUserVo {
    private String liveCode;
    private WeLive weLive;
}
