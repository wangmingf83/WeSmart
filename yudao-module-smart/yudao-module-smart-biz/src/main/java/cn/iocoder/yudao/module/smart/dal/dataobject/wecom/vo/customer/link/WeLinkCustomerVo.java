package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.link;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;

@Data
public class WeLinkCustomerVo extends WeResultVo {


    private Link link;



    @Data
    public class Link{

        //获客链接的id
        private String link_id;
        //获客链接名称
        private String link_name;
        //获客链接
        private String url;
        //获客链接创建时间
        private Long create_time;
    }
}
