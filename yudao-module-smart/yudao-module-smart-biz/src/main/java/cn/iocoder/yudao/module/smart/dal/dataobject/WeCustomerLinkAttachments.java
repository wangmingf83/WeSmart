package cn.iocoder.yudao.module.smart.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 活码附件表
 * @TableName we_customer_link_attachments
 */
@TableName(value ="we_customer_link_attachments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeCustomerLinkAttachments implements Serializable {
    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 识客码id
     */
    private Long customerLinkId;

    /**
     * 消息类型 文本:text 图片:image 图文:link 小程序:miniprogram 视频:video 文件:file 
     */
    private String msgType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 媒体id
     */
    private String mediaId;

    /**
     * 企业微信端返回的消息id
     */
    private String msgId;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息描述
     */
    private String description;

    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * 消息链接
     */
    private String linkUrl;

    /**
     * 消息图片地址
     */
    private String picUrl;

    /**
     * 小程序appid
     */
    private String appId;


    /**
     * 素材真实类型
     */
    private Integer realType;

    /**
     * 素材id
     */
    private Long materialId;

    /**
     * 删除标识 0 有效 1删除
     */
    private Integer delFlag;


}