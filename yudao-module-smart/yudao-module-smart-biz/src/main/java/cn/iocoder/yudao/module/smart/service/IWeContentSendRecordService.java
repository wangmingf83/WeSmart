package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.dto.WeContentSendViewDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeContentSendRecord;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.ContentDetailQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.query.WeContentSendRecordQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.ContentAxisVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.ContentDataDetailVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.WeContentCountVo;

import java.util.Date;
import java.util.List;

public interface IWeContentSendRecordService extends IService<WeContentSendRecord> {

    List<WeContentSendRecord> getSendTotal(Long id);

    List<WeContentSendRecord> getSendTotal(List<Long> ids);

    List<WeContentSendRecord> getSendTotal(Long id, Integer resourceType);

    List<WeContentSendRecord> getSendTotal(List<Long> ids, Integer resourceType,Long talkId);

    /**
     * 发送明细
     *
     * @param contentDetailQuery
     * @return
     */
    List<ContentDataDetailVo> getSendDetail(ContentDetailQuery contentDetailQuery);

    int getSendTotalToday(List<WeContentSendRecord> weContentSendRecordList);

    List<ContentAxisVo> getContentAxis(Date beginTime, Date endTime, List<WeContentSendRecord> weContentSendRecordList, List<ContentAxisVo> contentAxisVoList);

    List<ContentAxisVo> initContentAxisDate(Date beginTime, Date endTime);

    /**
     * 发送次数统计
     *
     * @param contentDetailQuery
     * @param weContentCountVo
     */
    void setWeContentCountVoForSendRecord(ContentDetailQuery contentDetailQuery, WeContentCountVo weContentCountVo);

    /**
     * 查询话术发送和查看数据
     *
     * @return
     */
    List<WeContentSendViewDto> getSendViewDataTotal(Integer resourceType);

    /**
     * 单个素材发送
     *
     * @param weContentSendRecordQuery
     * @return
     * @author WangYX
     * @date 2022/10/27 10:55
     */
    void singleSend(WeContentSendRecordQuery weContentSendRecordQuery);

    /**
     * 一键发送
     *
     * @param weContentSendRecordQuery
     * @return
     * @author WangYX
     * @date 2022/10/27 10:55
     */
    void withOneTouchSend(WeContentSendRecordQuery weContentSendRecordQuery);


}
