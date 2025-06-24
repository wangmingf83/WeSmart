package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.link;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;

/**
 * 查询剩余使用量
 */
@Data
public class WeLinkCustomerAcquisitionQuotaVo extends WeResultVo {

    /**
     * 历史累计使用量
     */
    private long total;

    /**
     *剩余使用量
     */
    private long balance;
}
