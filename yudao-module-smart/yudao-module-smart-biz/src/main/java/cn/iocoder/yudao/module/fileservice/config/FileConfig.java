package cn.iocoder.yudao.module.fileservice.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;


@Configuration
public class FileConfig {

    @Value("${GuituAi.file.maxFileSize}")
    private  Integer maxFileSize;

    @Value("${GuituAi.file.maxRequestSize}")
    private  Integer maxRequestSize;
    /**
     * Description: springboot对文件上传大小控制
     *
     * @interfaceName
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        factory.setMaxFileSize(DataSize.ofMegabytes(maxFileSize)); // MB
        // 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(maxRequestSize));
        return factory.createMultipartConfig();
    }
}
