package cn.iocoder.yudao.module.fileservice.service.impl;

import cn.iocoder.yudao.module.fileservice.service.IFileService;
import cn.iocoder.yudao.module.fileservice.utils.FileUploadUtils;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 本地文件存储
 *
 * @author leejoker
 */
@Service
@ConditionalOnProperty(prefix = "GuituAi.file", value = "object", havingValue = "local")
public class LocalFileServiceImpl implements IFileService {

    @Autowired
    private GuituAiConfig GuituAiConfig;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        return GuituAiConfig.getFile().getCos().getCosImgUrlPrefix()
                + FileUploadUtils.upload(GuituAiConfig.getFile().getCos().getBucketName(), file);
    }
}