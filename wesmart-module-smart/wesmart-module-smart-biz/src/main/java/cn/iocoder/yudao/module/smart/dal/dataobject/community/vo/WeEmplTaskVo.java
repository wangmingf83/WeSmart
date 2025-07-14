package cn.iocoder.yudao.module.smart.dal.dataobject.community.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeEmplTaskVo {
    private List todo;
    private List done;
}
