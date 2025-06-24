package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleNoticeListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleNoticeListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleMsgNotice;

/**
 * 质检规则通知表(WeQiRuleMsgNotice)
 *
 * @author danmo
 * @since 2023-05-10 09:51:51
 */
@Repository()
@Mapper
public interface WeQiRuleMsgNoticeMapper extends BaseMapper<WeQiRuleMsgNotice> {


    List<WeQiRuleNoticeListVo> getNoticeList(WeQiRuleNoticeListQuery query);

}

