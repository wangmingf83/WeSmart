package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.query.WeLxQrCodeListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeLxQrCodeDetailVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeLxQrCodeSheetVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeLxQrCode;

/**
 * 拉新活码信息表(WeLxQrCode)
 *
 * @author danmo
 * @since 2023-03-07 14:59:35
 */
@Repository()
@Mapper
public interface WeLxQrCodeMapper extends BaseMapper<WeLxQrCode> {


    WeLxQrCodeDetailVo getQrDetail(@Param("id") Long id);


    List<WeLxQrCodeSheetVo> getWeQrCodeListStatistics(WeLxQrCodeListQuery query);

    WeLxQrCodeDetailVo getQrDetailByState(@Param("state") String state);
}

