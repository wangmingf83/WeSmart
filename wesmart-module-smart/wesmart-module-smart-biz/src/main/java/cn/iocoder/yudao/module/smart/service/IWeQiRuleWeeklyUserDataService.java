package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleWeeklyUserData;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleWeeklyDetailListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleWeeklyDetailListVo;

import java.util.List;

/**
 * 会话质检周报员工数据表(WeQiRuleWeeklyUserData)
 *
 * @author danmo
 * @since 2023-05-18 17:36:35
 */
public interface IWeQiRuleWeeklyUserDataService extends IService<WeQiRuleWeeklyUserData> {

    List<WeQiRuleWeeklyDetailListVo> getWeeklyDetailList(WeQiRuleWeeklyDetailListQuery query);
}
