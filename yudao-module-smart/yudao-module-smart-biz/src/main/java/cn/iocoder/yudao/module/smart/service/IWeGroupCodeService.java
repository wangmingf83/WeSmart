package cn.iocoder.yudao.module.smart.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.vo.WeGroupChatInfoVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.vo.WeGroupCodeCountTrendVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrCodeScanCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrCodeScanLineCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.groupchat.WeGroupChatAddJoinWayVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.groupchat.WeGroupChatGetJoinWayVo;

import java.util.List;

/**
 * @author leejoker
 * @date 2022/4/6 23:23
 */
public interface IWeGroupCodeService extends IService<WeGroupCode> {


    /**
     * 查询客户群活码列表
     *
     * @param weGroupCode 客户群活码
     * @return 客户群活码集合
     */
    List<WeGroupCode> selectWeGroupCodeList(WeGroupCode weGroupCode);


    /**
     * 获取客群详情
     * @param groupCodeId
     * @return
     */
    WeGroupCode findWeGroupCodeById(Long groupCodeId);

    /**
     * 新增客户群活码
     *
     * @param weGroupCode 客户群活码
     */
    void insertWeGroupCode(WeGroupCode weGroupCode);

    /**
     * 修改客户群活码
     *
     * @param weGroupCode 客户群活码
     * @return 结果
     */
    WeGroupCode updateWeGroupCode(WeGroupCode weGroupCode);


    /**
     * 单个或批量删除群活码,同时同步企业微信端
     * @param ids
     */
    void batchRemoveByIds(List<Long> ids);


    /**
     *  获取指定活码下，群相关信息
     * @param groupId
     * @return
     */
    List<WeGroupChatInfoVo> findWeGroupChatInfoVos(Long groupId);


    /**
     *  获取指定活码加群退群指定时间段内相关数据
     * @param state
     * @param beginTime
     * @param endTime
     * @return
     */
    List<WeGroupCodeCountTrendVo> findWeGroupCodeCountTrend(String state,String beginTime,String endTime);

    /**
     * 构建群活码url
     * @param weGroupCode
     * @return
     */
    WeGroupChatGetJoinWayVo builderGroupCodeUrl(WeGroupCode weGroupCode);


    /**
     * 构建群活码config
     * @param weGroupCode
     * @return
     */
    WeGroupChatAddJoinWayVo builderGroupCodeConfig(WeGroupCode weGroupCode);

    JSONObject getShort2LongUrl(String shortUrl);

    WeGroupCode getDetail(String id);

    WeQrCodeScanCountVo getWeQrCodeScanTotalCount(WeGroupCode weGroupCode);

    List<WeQrCodeScanLineCountVo> getWeQrCodeScanLineCount(WeGroupCode weGroupCode);

    List<WeQrCodeScanLineCountVo> getWeQrCodeScanSheetCount(WeGroupCode weGroupCode);

}
