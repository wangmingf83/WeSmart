package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfCustomerStat;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfPool;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfUserStat;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.query.WeKfRecordQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo.WeKfRecordListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客服接待池表(WeKfPool)
 *
 * @author danmo
 * @since 2022-04-15 15:53:37
 */
@Repository()
@Mapper
public interface WeKfPoolMapper extends BaseMapper<WeKfPool> {

    @DataScope(type = "2", value = @DataColumn(alias = "record", name = "user_id", userid = "we_user_id"))
    List<WeKfRecordListVo> getRecordList(WeKfRecordQuery query);

    /**
     * 获取客服客户统计
     * @param dateTime 日期
     */
    WeKfCustomerStat getKfCustomerStat(@Param("dateTime") String dateTime);

    /**
     * 客服员工统计
     * @param dateTime
     * @return
     */
    List<WeKfUserStat> getKfUserStat(@Param("dateTime") String dateTime);
}

