package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.entity.WeStoreCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.query.WeStoreCodeQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.query.WxStoreCodeQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.WeStoreCodeTableVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.WeStoreCodesVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.datareport.WeStoreGroupReportVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.datareport.WeStoreShopGuideReportVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.drum.WeStoreGroupDrumVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.drum.WeStoreShopGuideDrumVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.tab.WeStoreGroupTabVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.tab.WeStoreShopGuideTabVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.tab.WeStoreTabVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.trend.WeStoreGroupTrendVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.vo.trend.WeStoreShopGuideTrendVo;

import java.util.List;

public interface IWeStoreCodeService extends IService<WeStoreCode> {


    /**
     * 获取门店活码
     * @param weStoreCode
     * @return
     */
   List<WeStoreCode> storeCodes(WeStoreCode weStoreCode);

    /**
     * 新增或更新门店导购码
     * @param weStoreCode
     */
    void createOrUpdateStoreCode(WeStoreCode weStoreCode);


    /**
     * 门店导购码统计-头部tab
     * @return
     */
    WeStoreShopGuideTabVo countWeStoreShopGuideTab();


    /**
     * 门店码统计-头部tab
     * @param storeCodeId
     * @return
     */
    WeStoreTabVo countWeStoreTab(Long storeCodeId);


    /**
     *  门店群活码统计-头部tab
     * @return
     */
    WeStoreGroupTabVo countWeStoreGroupTab();


    /**
     * 门店导购码趋势图
     * @param weStoreCode
     * @return
     */
    List<WeStoreShopGuideTrendVo> countStoreShopGuideTrend(WeStoreCode weStoreCode);


    /**
     *  门店群码趋势图
     * @param weStoreCode
     * @return
     */
    List<WeStoreGroupTrendVo> countStoreGroupTrend(WeStoreCode weStoreCode);


    /**
     *  门店导购码统计-门店新增客户top10
     * @param beginTime
     * @param endTime
     * @return
     */
    List<WeStoreShopGuideDrumVo> countStoreShopGuideDrum(String beginTime,String endTime);




    /**
     *  门店群码统计-门店客群新增客户top10
     * @param beginTime
     * @param endTime
     * @return
     */
    List<WeStoreGroupDrumVo> countStoreShopGroupDrum(String beginTime,String endTime);




    /**
     * 门店导购统计-数据报表
     * @param weStoreCode
     * @return
     */
    List<WeStoreShopGuideReportVo> countShopGuideReport(WeStoreCode weStoreCode);



    /**
     * 门店群码统计-数据报表
     * @param weStoreCode
     * @return
     */
    List<WeStoreGroupReportVo> countStoreGroupReport(WeStoreCode weStoreCode);


    /**
     * 根据定位获取门店
     * @param wxStoreCodeQuery
     * @return
     */
    WeStoreCodesVo findStoreCode(WxStoreCodeQuery wxStoreCodeQuery);


    /**
     * 记录用户扫码行为
     * @param wxStoreCodeQuery
     */
    void countUserBehavior(WxStoreCodeQuery wxStoreCodeQuery);


  /**
   * 数据明细获取
   * @param weStoreCodeQuery
   * @return
   */
  List<WeStoreCodeTableVo> findWeStoreCodeTables(WeStoreCodeQuery weStoreCodeQuery);


 /**
  * 批量更新状态
  * @param storeState
  * @param ids
  */
  void batchUpdateState(Integer storeState,List<Long> ids);
}
