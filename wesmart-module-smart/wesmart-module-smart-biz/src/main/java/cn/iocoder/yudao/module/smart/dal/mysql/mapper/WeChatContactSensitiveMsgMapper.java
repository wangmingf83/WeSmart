package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeChatContactSensitiveMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.query.WeSensitiveHitQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.vo.WeChatContactSensitiveMsgVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会话触发敏感词记录(WeChatContactSensitiveMsg)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
@Repository()
@Mapper
public interface WeChatContactSensitiveMsgMapper extends BaseMapper<WeChatContactSensitiveMsg> {


    List<WeChatContactSensitiveMsgVo> getListByQuery(WeSensitiveHitQuery query);
}

