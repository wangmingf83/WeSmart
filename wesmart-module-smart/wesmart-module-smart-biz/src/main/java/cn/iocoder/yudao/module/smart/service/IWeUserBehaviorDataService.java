package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeUserBehaviorData;
import cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo.WePageCountVo;

import java.util.List;

/**
 * 联系客户统计数据 (WeUserBehaviorData)
 *
 * @author danmo
 * @since 2022-04-30 23:28:06
 */
public interface IWeUserBehaviorDataService extends IService<WeUserBehaviorData> {

    List<WePageCountVo> getDayCountDataByTime(String beginTime, String endTime);
} 
