package cn.iocoder.yudao.module.smart.core.domain;

import lombok.experimental.SuperBuilder;
import lombok.Data;

/**
 * @description:
 * @author: HaoN
 * @create: 2021-07-13 15:25
 **/
@Data
@SuperBuilder
public class FileVo {
    private  String fileName;
    private String url;
}
