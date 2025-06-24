package cn.iocoder.yudao.module.smart.dal.dataobject.sop.dto;


import cn.iocoder.yudao.framework.security.core.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeSopBaseDto implements Serializable {
    //sop主键
    private Long sopBaseId;
    //创建或更新
    private boolean isCreateOrUpdate=true;
    //当前系统登陆用户
    private LoginUser loginUser;

}
