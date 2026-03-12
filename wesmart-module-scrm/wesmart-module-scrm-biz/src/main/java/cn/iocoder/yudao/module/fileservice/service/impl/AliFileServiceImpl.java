package cn.iocoder.yudao.module.fileservice.service.impl;

import cn.iocoder.yudao.module.fileservice.service.IFileService;
import cn.iocoder.yudao.module.smart.config.SmartWeChatConfig;
import cn.iocoder.yudao.module.smart.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * 集成阿里云oss
 */
@Service
@ConditionalOnProperty(prefix = "smartwechat.file", value = "object", havingValue = "aliOss")
public class AliFileServiceImpl implements IFileService {
    @Autowired
    private SmartWeChatConfig smartChatConfig;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        return  smartChatConfig.getFile().getCos().getCosImgUrlPrefix() + FileUploadUtils
                .uploadAliOss(file, smartChatConfig.getFile().getCos());
    }
}
