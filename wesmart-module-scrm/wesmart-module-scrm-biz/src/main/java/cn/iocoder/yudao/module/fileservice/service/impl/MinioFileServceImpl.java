package cn.iocoder.yudao.module.fileservice.service.impl;

import cn.iocoder.yudao.module.common.MinioConfig;
import cn.iocoder.yudao.module.fileservice.service.IFileService;
import cn.iocoder.yudao.module.smart.config.SmartWeChatConfig;
import cn.iocoder.yudao.module.smart.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * minio存储
 *
 * @author 圭图智能
 */
@Service
@ConditionalOnProperty(prefix = "smartwechat.file", value = "object", havingValue = "minio")
public class MinioFileServceImpl implements IFileService {

    @Autowired
    private SmartWeChatConfig smartChatConfig;
    @Autowired
    private MinioConfig minioConfig;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String filepath = FileUploadUtils.uploadMinio(file, smartChatConfig.getFile().getCos());
        String url = getPermanentPublicUrl(smartChatConfig.getMinioUrl(), minioConfig.getBucketName(), filepath);
        return url;
    }

    private String getPermanentPublicUrl(String endpoint, String bucketName, String objectName) {
        return String.format("%s/%s/%s",
                endpoint.endsWith("/") ? endpoint.substring(0, endpoint.length() - 1) : endpoint,
                bucketName,
                encodeObjectName(objectName));
    }

    private String encodeObjectName(String objectName) {
        return java.net.URLEncoder.encode(objectName, java.nio.charset.StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20"); // 替换空格为 %20 而不是 +
    }
}
