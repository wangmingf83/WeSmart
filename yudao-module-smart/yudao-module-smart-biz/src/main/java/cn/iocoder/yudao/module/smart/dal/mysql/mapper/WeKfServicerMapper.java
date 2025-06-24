package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfServicer;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.WeKfUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.query.WeKfServicerListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo.WeKfServicerListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客服接待人员表(WeKfServicer)
 *
 * @author danmo
 * @since 2022-04-15 15:53:38
 */
@Repository()
@Mapper
public interface WeKfServicerMapper extends BaseMapper<WeKfServicer> {
    /**
     * 根据客服id查询接待人员
     * @param kfId 客服id
     * @return
     */
    List<WeKfUser> getServicerByKfId(@Param("kfId") Long kfId);

    /**
     * 客服接待人员列表
     * @param query
     * @return
     */
    @DataScope(type = "2", value = @DataColumn(alias = "wks", name = "user_id", userid = "we_user_id"))
    List<WeKfServicerListVo> getKfServicerList(WeKfServicerListQuery query);

    List<WeKfUser> getKfUserIdList(@Param("kfId") Long kfId);
}

