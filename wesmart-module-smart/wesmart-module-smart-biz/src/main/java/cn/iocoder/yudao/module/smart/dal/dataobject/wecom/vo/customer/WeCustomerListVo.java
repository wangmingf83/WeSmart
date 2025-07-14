package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @Description 获取客户列表入参
 * @date 2021/12/2 16:11
 **/
@Data
public class WeCustomerListVo extends WeResultVo {
    /**
     * 企业成员的userid
     */
    private List<String> externalUserId;
}
