package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleMsgNotice;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleNoticeListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleNoticeListVo;

import java.util.List;

/**
 * 质检规则通知表(WeQiRuleMsgNotice)
 *
 * @author danmo
 * @since 2023-05-10 09:51:51
 */
public interface IWeQiRuleMsgNoticeService extends IService<WeQiRuleMsgNotice> {

    List<WeQiRuleNoticeListVo> getNoticeList(WeQiRuleNoticeListQuery query);
}
