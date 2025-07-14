package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleNoticeListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleNoticeListVo;
import cn.iocoder.yudao.module.smart.service.IWeQiRuleMsgNoticeService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeQiRuleMsgNoticeMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleMsgNotice;

import java.util.List;

/**
 * 质检规则通知表(WeQiRuleMsgNotice)
 *
 * @author danmo
 * @since 2023-05-10 09:51:51
 */
@Service
public class WeQiRuleMsgNoticeServiceImpl extends ServiceImpl<WeQiRuleMsgNoticeMapper, WeQiRuleMsgNotice> implements IWeQiRuleMsgNoticeService {


    @Override
    public List<WeQiRuleNoticeListVo> getNoticeList(WeQiRuleNoticeListQuery query) {
       return this.baseMapper.getNoticeList(query);
    }
}
