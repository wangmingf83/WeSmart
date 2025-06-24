package cn.iocoder.yudao.module.fileservice.service.impl;

import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.utils.file.FileUploadUtils;
import cn.iocoder.yudao.module.fileservice.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 集成腾讯云oss
 */
@Service
@ConditionalOnProperty(prefix = "GuituAi.file", value = "object", havingValue = "tencentOss")
public class TencentFileServiceImpl implements IFileService {

    @Autowired
    private GuituAiConfig GuituAiConfig;



    @Override
    public String uploadFile(MultipartFile file) throws IOException {


        return  GuituAiConfig.getFile().getCos().getCosImgUrlPrefix() + FileUploadUtils.uploadTenantCos(file, GuituAiConfig.getFile().getCos());
    }



}
