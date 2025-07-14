package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLinkCount;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerLinkCountTabVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerLinkCountTableVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerLinkCountTrendVo;

import java.util.List;

/**
* @author robin
* @description 针对表【we_customer_link_count】的数据库操作Service
* @createDate 2023-07-26 14:51:19
*/
public interface IWeCustomerLinkCountService extends IService<WeCustomerLinkCount> {

    /**
     * 获客助手统计
     */
    void synchWeCustomerLinkCount(String linkId);


    /**
     * 同步链接剩余量
     */
    void synchAcquisitionQuota();


    /**
     * 获取折线图统计数据
     * @param linkId
     * @param beginTime
     * @param endTime
     * @return
     */
    List<WeCustomerLinkCountTrendVo> selectLinkCountTrend(String linkId,String beginTime,String endTime);


    /**
     * 获取表格统计数据
     * @param linkId
     * @param beginTime
     * @param endTime
     * @return
     */
    List<WeCustomerLinkCountTableVo> selectLinkCountTable(String linkId,String beginTime,String endTime);

    /**
     * 获取头部统计数据
     * @param linkId
     * @return
     */
    WeCustomerLinkCountTabVo selectLinkCountTab(String linkId);




}
