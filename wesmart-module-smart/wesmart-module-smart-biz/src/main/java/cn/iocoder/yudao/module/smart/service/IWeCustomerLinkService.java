package cn.iocoder.yudao.module.smart.service;

import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLink;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLinkCount;

import java.util.List;

/**
* @author robin
* @description 针对表【we_customer_link(获客助手)】的数据库操作Service
* @createDate 2023-07-04 17:41:13
*/
public interface IWeCustomerLinkService extends IService<WeCustomerLink> {


    /**
     * 获取相关链接下的客户
     * @param weCustomerLinkCount
     * @return
     */
    List<WeCustomerLinkCount> findLinkWeCustomer(WeCustomerLinkCount weCustomerLinkCount);


    /**
     *  创建获客助手
     * @param customerLink
     * @param createOrUpdate true创建 false更新
     */
    void createOrUpdateCustomerLink(WeCustomerLink customerLink,boolean createOrUpdate);


    /**
     * 根据获客链接id获取详情
     * @param id
     * @return
     */
    WeCustomerLink findWeCustomerLinkById(Long id);



    /**
     *
     * @param shortUrl
     * @return
     */
    JSONObject getShort2LongUrl(String shortUrl);


    /**
     * 删除获客链接
     * @param ids
     */
    void removeLink(List<Long> ids);







}
