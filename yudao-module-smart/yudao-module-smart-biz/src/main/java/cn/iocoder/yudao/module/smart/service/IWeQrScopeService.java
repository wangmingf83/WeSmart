package cn.iocoder.yudao.module.smart.service;


import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.query.WeQrUserInfoQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrScopeVo;

import java.util.List;

/**
 * 活码使用范围表(WeQrScope)表服务接口
 *
 * @author makejava
 * @since 2021-11-07 01:29:14
 */
public interface IWeQrScopeService extends IService<WeQrScope> {

    /**
     * 通过活码id批量保存适用范围
     * @param qrId  活码id
     * @param qrUserInfos 适用范围列表
     */
    void saveBatchByQrId(Long qrId, List<WeQrUserInfoQuery> qrUserInfos);

    /**
     * 通过活码id批量删除适用范围
     * @param qrIds 活码id
     * @return
     */
    Boolean delBatchByQrIds(List<Long> qrIds);

    /**
     * 通过活码id批量修改适用范围
     * @param qrId  活码id
     * @param qrUserInfos 适用范围列表
     */
    void updateBatchByQrId(Long qrId, List<WeQrUserInfoQuery> qrUserInfos);

    /**
     * 根据活码id查询适用范围
     * @param qrIds 活码id
     * @return
     */
    List<WeQrScopeVo> getWeQrScopeByQrIds(List<Long> qrIds);

    /**
     * 查询当前时间使用范围活码数据
     * @return
     */
    List<WeQrScopeVo> getWeQrScopeByTime(String formatTime,String qrId);

    void updateScopeStatusByQrId(Long qrId, String scopeId);
}
