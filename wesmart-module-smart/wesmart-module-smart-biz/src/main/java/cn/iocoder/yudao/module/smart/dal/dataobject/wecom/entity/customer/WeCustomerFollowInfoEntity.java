package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @Description
 * @date 2021/12/3 0:15
 **/
@Data
public class WeCustomerFollowInfoEntity {
    /**
     * 添加了此外部联系人的企业成员userid
     */
    private String userId;
    /**
     * 该成员对此外部联系人的备注
     */
    private String remark;
    /**
     * 该成员对此外部联系人的描述
     */
    private String description;
    /**
     * 该成员添加此外部联系人的时间
     */
    private Long createTime;
    /**
     * 该成员对此客户备注的企业名称
     */
    private String remarkCompany;
    /**
     * 该成员对此客户备注的手机号码
     */
    private List<String> remarkMobiles;
    /**
     * 该成员添加此客户的来源
     */
    private Integer addWay;
    /**
     * 发起添加的userid，如果成员主动添加，为成员的userid；如果是客户主动添加，则为客户的外部联系人userid；如果是内部成员共享/管理员分配，则为对应的成员/管理员userid
     */
    private String operUserId;
    /**
     * 标签
     **/
    private List<String> tagId;

    /** 企业自定义的state参数 */
    private String state;
}
