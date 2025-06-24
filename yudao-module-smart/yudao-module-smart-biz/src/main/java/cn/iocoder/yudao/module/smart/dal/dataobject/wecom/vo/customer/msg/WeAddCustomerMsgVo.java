package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.msg;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @Description 创建企业群发
 * @date 2021/12/2 16:11
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class WeAddCustomerMsgVo extends WeResultVo {

    /**
     * 无效或无法发送的external_userid列表
     */
    private List<String> failList;
    /**
     * 企业群发消息的id，可用于获取群发消息发送结果
     */
    private String msgId;

}
