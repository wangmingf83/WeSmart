package cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query;


import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeCustomersQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author danmo
 * @description 添加群发消息入参
 * @date 2021/10/24 12:14
 **/
@Data
public class WeAddGroupMessageQuery extends WeGroupMessageTemplate {

    /**
     * 是否全部发送
     */
    private Boolean isAll=true;



    /**
     * 指定接收消息的成员及对应客户列表
     */
    private List<SenderInfo> senderList;


    /**
     * 附件列表(新建编辑入参)
     */
    private List<WeMessageTemplate> attachmentsList;


    /**
     * 附件列表展示
     */
    private List<WeGroupMessageAttachments> weGroupMessageAttachmentsVo;

    /**
     * 当前用户
     */
    private LoginUser loginUser;


    //消息来源 1:通常 2:sop 3:直播 ,4:裂变，5:短链推广 6:标签建群
    private Integer msgSource=1;


    /**
     * 客户查询条件
     */

    private WeCustomersQuery weCustomersQuery;




    public void setCurrentUserInfo(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Schema
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SenderInfo {

        @Schema(description = "成员id")
        private String userId;

        @Schema(description = "客户列表")
        private List<String> customerList;

        @Schema(description = "群组列表")
        private List<String> chatList;


    }

}
