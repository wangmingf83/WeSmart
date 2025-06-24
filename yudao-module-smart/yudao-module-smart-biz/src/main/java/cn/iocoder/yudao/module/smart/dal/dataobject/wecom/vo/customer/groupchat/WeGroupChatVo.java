package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.groupchat;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 获取客户群列表
 * @date 2021/12/2 16:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeGroupChatVo extends WeResultVo {
    /**
     * 客户群ID
     */
    private String chatId;
}
