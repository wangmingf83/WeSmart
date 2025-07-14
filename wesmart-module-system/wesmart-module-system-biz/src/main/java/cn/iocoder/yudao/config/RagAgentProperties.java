package cn.iocoder.yudao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ragagent")
@Data
public class RagAgentProperties {

    private String callbackUrl;
    private String registerUrl;

}
