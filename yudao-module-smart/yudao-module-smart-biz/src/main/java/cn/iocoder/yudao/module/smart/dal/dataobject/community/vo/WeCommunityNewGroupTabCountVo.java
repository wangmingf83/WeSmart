package cn.iocoder.yudao.module.smart.dal.dataobject.community.vo;

import lombok.Data;

@Data
public class WeCommunityNewGroupTabCountVo {

    //添加客户总数
    private int addCustomerNumber;

    //进群客户总数
    private int joinGroupCustomerNumber;

    //今日添加客户总数
    private int tdAddCustomerNumber;


    //今日进群客户总数
    private int tdJoinGroupCustomerNumber;
}
