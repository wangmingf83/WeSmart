package cn.iocoder.yudao.module.smart.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fhs.core.trans.vo.VO;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author 圭图智能
 * @description 企业信息
 * @date 2024/4/11 22:57
 **/
@Schema
@Data
public class WeCorpAccountVo implements VO {

    /**
     * 主键ID
     */
    private Long id;

    /***********************************************
     *******************企业配置start****************
     **********************************************/

    /**
     * 企业ID
     */
    private String corpId;


    /**
     * 企业名称不能为空
     */
    private String companyName;

    /***********************************************
     *******************企业配置end****************
     **********************************************/


    /***********************************************
     *******************通讯录配置start**************
     **********************************************/

    /**
     * 通讯录Secret
     */
    private String corpSecret;

    /**
     * 直播Secret
     */
    private String liveSecret;

    /***********************************************
     *******************通讯录配置end****************
     **********************************************/


    /***********************************************
     *******************客户联系配置start*************
     **********************************************/


    /**
     * 客户联系Secret不可为空
     */
    private String contactSecret;


    /***********************************************
     *******************客户联系配置end***************
     **********************************************/


    /***********************************************
     *******************应用配置start****************
     **********************************************/
    /**
     * 消息应用ID
     */
    private String agentId;

    /**
     * 消息应用Secret
     */
    private String agentSecret;


    /**
     * 会话私钥Secret
     */
    private String chatSecret;


    /**
     * 会话存档密钥
     */
    private String financePrivateKey;

    /***********************************************
     *******************应用配置end****************
     **********************************************/


    /***********************************************
     *******************接收事件服务器start***********
     **********************************************/

    /**回调url*/
    //private String backOffUrl;

    /**
     * 回调token
     */
    private String token;

    /**
     * 回调EncodingAESKey
     */
    private String encodingAesKey;


    /***********************************************
     *******************接收事件服务器end*************
     **********************************************/


    /***********************************************
     *******************微信客服start****************
     **********************************************/

    /**
     * 客服密钥
     */
    private String kfSecret;


    /***********************************************
     *******************微信客服end******************
     **********************************************/

    /***********************************************
     *******************微信支付start****************
     **********************************************/

    /**
     * 商户名称
     */
    private String merChantName;

    /**
     * 商户号
     */
    private String merChantNumber;

    /**
     * 商户密钥
     */
    private String merChantSecret;


    /**
     * API证书文件p12
     */
    private String certP12Url;


    /***********************************************
     *******************微信支付start****************
     **********************************************/

    /**
     * 微信公众号ID
     */
    private String wxAppId;

    /**
     * 微信公众号密钥
     */
    private String wxSecret;

    /**
     * 企业logo
     */
    private String logoUrl;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;


    /**
     * 客户流失通知开关 0:关闭 1:开启
     */
    private String customerChurnNoticeSwitch;


    /***********************************************
     *******************对外收款start****************
     **********************************************/
    /**
     * 对外收款
     */
    private String billSecret;

    /***********************************************
     *******************对外收款end****************
     **********************************************/

    /**
     * 微信小程序ID
     */
    private String miniAppId;

    /**
     * 微信小程序密钥
     */
    private String miniSecret;

    /**
     * 微信小程序原始ID
     */
    private String wxAppletOriginalId;

    /**
     * 获客助手剩余可用量
     */
    private long customerLinkMargin;


    /**
     * 获客助手总量
     */
    private long customerLinkTotal;


    /***********************************************
     *******************商城start****************
     **********************************************/

    /**
     * 商城小程序app_id
     */
    private String shopAppId;

    /**
     * 商城小程序秘钥
     */
    private String shopSecret;

    /**
     * 商城小程序消息推送token（令牌）
     */
    private String shopMaToken;

    /**
     * 商城小程序消息推送秘钥
     */
    private String shopMaEncodingaeskey;

    /**
     * 商城小程序消息推送-服务器地址
     */
    private String shopMaApi;

    /***********************************************
     *******************商城end****************
     **********************************************/


    @Schema(description = "租户编号", hidden = true)
    private Long tenantId;

}
