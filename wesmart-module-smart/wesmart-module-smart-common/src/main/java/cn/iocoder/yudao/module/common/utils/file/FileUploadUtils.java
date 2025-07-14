package cn.iocoder.yudao.module.common.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.iocoder.yudao.module.smart.config.CosConfig;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.enums.FileCosType;
import cn.iocoder.yudao.module.common.exception.file.FileNameLengthLimitExceededException;
import cn.iocoder.yudao.module.common.exception.file.FileSizeLimitExceededException;
import cn.iocoder.yudao.module.common.exception.file.InvalidExtensionException;
import cn.iocoder.yudao.module.common.utils.DateUtils;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.uuid.IdUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 *
 * @author ruoyi
 */
@Slf4j
public class FileUploadUtils {
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

//    /**
//     * 默认上传的地址
//     */
//    private static String defaultBaseDir = GuituAiConfig.getProfile();
//
//    public static void setDefaultBaseDir(String defaultBaseDir) {
//        FileUploadUtils.defaultBaseDir = defaultBaseDir;
//    }
//
//    public static String getDefaultBaseDir() {
//        return defaultBaseDir;
//    }



//    /**
//     * 以默认配置进行文件上传
//     *
//     * @param file 上传的文件
//     * @return 文件名称
//     * @throws Exception
//     */
//    public static final String upload(MultipartFile file) throws IOException {
//        try {
//            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
//        } catch (Exception e) {
//            throw new IOException(e.getMessage(), e);
//        }
//    }
//
//    public static final String upload(InputStream inputStream,String fileName) throws IOException {
//        try {
//            return upload(getDefaultBaseDir(), inputStream, fileName);
//        } catch (Exception e) {
//            throw new IOException(e.getMessage(), e);
//        }
//    }
//
//
//    /**
//     * 以默认配置进行文件上传
//     *
//     * @param file 上传的文件
//     * @return 文件名称
//     * @throws Exception
//     */
//    public static final String uploadFile(MultipartFile file) throws IOException {
//        try {
//            return uploadFile(getDefaultBaseDir(), file);
//        } catch (Exception e) {
//            throw new IOException(e.getMessage(), e);
//        }
//    }
//
    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     * @throws InvalidExtensionException            文件校验异常
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;
    }

    public static final String upload(String baseDir, InputStream inputStream, String fileName) throws IOException {
        File desc = getAbsoluteFile(baseDir, fileName);
        File file = FileUtil.writeFromStream(inputStream, desc);
        return file.getAbsolutePath();
    }

    /**
     * 上传到腾讯对象云存储中
     *
     * @param file    上传的文件
     * @return 访问路径
     * @throws IOException
     */
    public static final String uploadTenantCos(MultipartFile file , CosConfig cosConfig) throws IOException {
        try {
            return uploadCos( file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION,cosConfig, FileCosType.FILE_COS_TYPE_TENANT.getType());
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }


    /**
     * 上传阿里云存储中
     * @param file
     * @param cosConfig
     * @return
     */
    public static final String uploadAliOss(MultipartFile file , CosConfig cosConfig) throws IOException {

        try {
            return uploadCos( file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION,cosConfig,FileCosType.FILE_COS_TYPE_ALI.getType());
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }

    }



    /**
     * 上传阿里云存储中
     * @param file
     * @param cosConfig
     * @return
     */
    public static final String uploadMinio(MultipartFile file , CosConfig cosConfig) throws IOException {

        try {
            return uploadCos( file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION,cosConfig,FileCosType.FILE_COS_TYPE_MINIO.getType());
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }

    }







    /**
     * 文件上传
     *
     * @param file             上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     * @throws InvalidExtensionException            文件校验异常
     */
    public static final String uploadCos(MultipartFile file, String[] allowedExtension, CosConfig cosConfig,Integer fileCosType)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);


        if(FileCosType.FILE_COS_TYPE_TENANT.getType().equals(fileCosType)){//腾讯云存储逻辑
            // 1 初始化用户身份信息（secretId, secretKey）。
            COSCredentials cred = new BasicCOSCredentials(cosConfig.getSecretId(), cosConfig.getSecretKey());
            // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
            // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
            Region region = new Region(cosConfig.getRegion());
            ClientConfig clientConfig = new ClientConfig(region);
            // 3 生成 cos 客户端。
            COSClient cosClient = new COSClient(cred, clientConfig);

            // 指定要上传到 COS 上对象键
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), fileName,file.getInputStream(),objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            log.info("{}",cosConfig.getCosImgUrlPrefix()+fileName);
            log.info("腾讯cos上传信息：{}",new ObjectMapper().writeValueAsString(putObjectResult));
            cosClient.shutdown();

        }else if(FileCosType.FILE_COS_TYPE_ALI.getType().equals(fileCosType)){//阿里云存储逻辑

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(cosConfig.getRegion(), cosConfig.getSecretId(), cosConfig.getSecretKey());
            // 上传文件
            ossClient.putObject(cosConfig.getBucketName(), fileName, file.getInputStream());
            ossClient.shutdown();
        }else if(FileCosType.FILE_COS_TYPE_MINIO.getType().equals(fileCosType)){ //minio存储

            try {
                MinioClient minioClient = MinioClient.builder()
                        .endpoint(cosConfig.getRegion())
                        .credentials(cosConfig.getSecretId(), cosConfig.getSecretKey())
                        .build();
                //判断我们要上传到的 bucket 是否存在
                boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(cosConfig.getBucketName()).build());
                //存储桶不存在则构建一个
                if (!found) {
                    minioClient.makeBucket(MakeBucketArgs.builder().bucket(cosConfig.getBucketName()).build());
                }
                InputStream inputStream = file.getInputStream();
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(cosConfig.getBucketName())
                                .object(fileName)
                                .contentType(file.getContentType())
                                .stream(inputStream, inputStream.available(), -1)
                                .build());
            }catch (Exception e){
                log.error("minio文件上传失败"+e.getMessage());
            }




        }

        return fileName;
    }

//    /**
//     * 将流中的文件传到腾讯云
//     * @param inputStream 输入流
//     * @param fileExtension 被写入流的文件的后缀名
//     * @param allowedExtension 允许的后缀名
//     * @throws FileSizeLimitExceededException 文件大小超过上限
//     * @throws InvalidExtensionException 文件后缀名非法
//     */
//    public static String uploadTenantCos(InputStream inputStream, String fileExtension, String[] allowedExtension, CosConfig cosConfig) throws FileSizeLimitExceededException, InvalidExtensionException {
//        String fileName = DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + fileExtension;
//        if (!isAllowedExtension(fileExtension, allowedExtension))
//        {
//            throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, fileExtension, fileName);
//        }
//        COSCredentials cred = new BasicCOSCredentials(cosConfig.getSecretId(), cosConfig.getSecretKey());
//        Region region = new Region(cosConfig.getRegion());
//        ClientConfig clientConfig = new ClientConfig(region);
//        COSClient cosClient = new COSClient(cred, clientConfig);
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), fileName ,inputStream ,objectMetadata);
//        cosClient.putObject(putObjectRequest);
//        cosClient.shutdown();
//        return fileName;
//    }


//    public static final String uploadFile(String baseDir, MultipartFile file)
//            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException {
//        int fileNamelength = file.getOriginalFilename().length();
//        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
//            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
//        }
//
//        String fileName = extractFilename(file);
//
//        File desc = getAbsoluteFile(baseDir, fileName);
//        file.transferTo(desc);
//        String pathFileName = getPathFileName(baseDir, fileName);
//        return pathFileName;
//    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
        return fileName;
    }

    private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    public static final String getPathFileName(String uploadDir, String fileName) throws IOException {
        int dirLastIndex = GuituAiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName;
        if(org.apache.commons.lang3.StringUtils.isBlank(currentDir)){
            pathFileName = Constants.RESOURCE_PREFIX + "/" +fileName;
        }else {
            pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        }
        return pathFileName;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws InvalidExtensionException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE) {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }

        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            } else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }
}
