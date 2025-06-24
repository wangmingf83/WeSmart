package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.query.WeKfCustomerStatisticQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author danmo
 * @description 客服统计mapper
 * @date 2022/1/9 17:07
 **/
@InterceptorIgnore(tenantLine = "true")
@Mapper
public interface WeKfStatisticMapper {

    /**
     * 场景客户分析
     *
     * @return
     */
    WeKfSceneAnalysisVo getSceneCustomerAnalysis();

    /**
     * 场景客户实时数据
     * @return
     */
    //List<WeKfSceneRealCntVo> getSceneCustomerRealCnt1(WeKfCustomerStatisticQuery query);

    List<WeKfSceneRealCntVo> getSceneCustomerRealCnt(WeKfCustomerStatisticQuery query);
    List<WeKfSceneRealCntVo> getSceneCustomerRealPageCnt(WeKfCustomerStatisticQuery query);

    List<WeKfSceneRankCntVo> getSceneCustomerVisitRank(WeKfCustomerStatisticQuery query);

    List<WeKfSceneRankCntVo> getSceneCustomerConsultRank(WeKfCustomerStatisticQuery query);

    WeKfConsultAnalysisVo getConsultCustomerAnalysis();

    List<WeKfConsultRankCntVo> getConsultUserReplyRank(WeKfCustomerStatisticQuery query);

    List<WeKfConsultRankCntVo> getConsultUserAvgReplyDurationRank(WeKfCustomerStatisticQuery query);

    List<WeKfConsultRealCntVo> getReplyRealCnt(WeKfCustomerStatisticQuery query);

    List<WeKfConsultRealCntVo> getReplyRealPageCnt(WeKfCustomerStatisticQuery query);

    List<WeKfConsultDurationVo> getReplyRealDurationCnt(WeKfCustomerStatisticQuery query);
}
