package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.user;


import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeLeaveUserQuery extends WeBaseQuery {

    private String cursor;
    private Integer page_size;


}
