package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.query.WeLxQrCodeQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeLxQrCodeReceiveListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeLxQrCodeReceiveVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeLxQrCodeLog;

/**
 * 拉新活码领取记录表(WeLxQrCodeLog)
 *
 * @author danmo
 * @since 2023-03-16 16:19:02
 */
@Repository()
@Mapper
public interface WeLxQrCodeLogMapper extends BaseMapper<WeLxQrCodeLog> {


    List<WeLxQrCodeReceiveListVo> receiveListStatistics(WeLxQrCodeQuery query);

    WeLxQrCodeReceiveVo receiveTotalStatistics(WeLxQrCodeQuery query);
}

