package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.common.annotation.DataColumn;
import cn.iocoder.yudao.module.common.annotation.DataScope;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.query.WeQrCodeListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrCodeDetailVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrScopeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 活码信息表(WeQrCode)
 *
 * @author danmo
 * @since 2021-11-07 02:30:11
 */
@Repository()
@Mapper
public interface WeQrCodeMapper extends BaseMapper<WeQrCode> {

    /**
     * 根据活码id查询详情
     * @param qrId 活码id
     * @return
     */
    
    WeQrCodeDetailVo getQrDetailByQrId(@Param("qrId") Long qrId);

    /**
     * 根据活码id批量查询详情
     * @param qrIds 活码id
     * @return
     */
    List<WeQrCodeDetailVo> getQrDetailByQrIds(@Param("qrIds") List<Long> qrIds);

    /**
     * 查询活码列表id
     * @param qrCodeListQuery
     * @return
     */
    @DataScope(type = "2", value = @DataColumn(alias = "wqc", name = "create_by_id", userid = "user_id"))
    List<Long> getQrCodeList(WeQrCodeListQuery qrCodeListQuery);

    
    List<WeQrScopeVo> getWeQrScopeByTime(@Param("formatTime") String formatTime,@Param("qrId") Long qrId);

    WeQrCodeDetailVo getQrDetailByState(String state);
}

