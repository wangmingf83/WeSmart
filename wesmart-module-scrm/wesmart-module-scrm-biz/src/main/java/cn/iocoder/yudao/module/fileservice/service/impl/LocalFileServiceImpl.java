package cn.iocoder.yudao.module.fileservice.service.impl;

import cn.iocoder.yudao.module.fileservice.service.IFileService;
import cn.iocoder.yudao.module.smart.common.utils.file.FileUploadUtils;
import cn.iocoder.yudao.module.smart.config.SmartWeChatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 本地文件存储
 *
 * @author 圭图智能
 */
@Service
@ConditionalOnProperty(prefix = "smartwechat.file", value = "object", havingValue = "local")
public class LocalFileServiceImpl implements IFileService {

    @Autowired
    private SmartWeChatConfig smartChatConfig;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        return smartChatConfig.getFile().getCos().getCosImgUrlPrefix()
                + FileUploadUtils.upload(smartChatConfig.getFile().getCos().getBucketName(), file);
    }
}