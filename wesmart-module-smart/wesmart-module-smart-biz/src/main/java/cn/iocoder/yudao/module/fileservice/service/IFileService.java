package cn.iocoder.yudao.module.fileservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传接口
 *
 * @author leejoker
 */
public interface IFileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws IOException;
}
