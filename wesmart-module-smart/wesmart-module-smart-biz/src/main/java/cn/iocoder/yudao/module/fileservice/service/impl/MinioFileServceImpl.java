package cn.iocoder.yudao.module.fileservice.service.impl;

import cn.iocoder.yudao.module.fileservice.service.IFileService;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * minio存储
 *
 * @author leejoker
 */
@Service
@ConditionalOnProperty(prefix = "GuituAi.file", value = "object", havingValue = "minio")
public class MinioFileServceImpl implements IFileService {

    @Autowired
    private GuituAiConfig GuituAiConfig;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        return  GuituAiConfig.getFile().getCos().getCosImgUrlPrefix()
                + FileUploadUtils.uploadMinio(file, GuituAiConfig.getFile().getCos());
    }
}
