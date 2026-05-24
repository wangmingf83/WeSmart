package cn.iocoder.yudao.module.smart.wxmsg;

import lombok.Data;

@Data
public class LocationMessage {
    private String latitude; // 纬度
    private String longitude; // 经度
    private String name;
    private String address;
}
