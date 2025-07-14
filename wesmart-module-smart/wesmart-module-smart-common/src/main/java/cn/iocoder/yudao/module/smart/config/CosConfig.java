package cn.iocoder.yudao.module.smart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**腾讯云存储相关配置*/
@Component
@Data
public class CosConfig {

    private String secretId;

    private String secretKey;

    private String region;

    private String bucketName;

    private String cosImgUrlPrefix;

}
