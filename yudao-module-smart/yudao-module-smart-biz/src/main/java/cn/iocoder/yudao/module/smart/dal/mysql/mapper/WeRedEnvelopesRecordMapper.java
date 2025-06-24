package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.WeRedEnvelopesRecord;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.dto.H5RedEnvelopesDetailDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.vo.WeCutomerRedEnvelopesVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.vo.WeGroupRedEnvelopesVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.envelopes.vo.WeRedEnvelopesCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WeRedEnvelopesRecordMapper extends BaseMapper<WeRedEnvelopesRecord> {

    List<WeCutomerRedEnvelopesVo> findRedEnveForUser(WeRedEnvelopesRecord redEnvelopesRecord);


    List<WeGroupRedEnvelopesVo> findRedEnveForGroup(WeRedEnvelopesRecord redEnvelopesRecord);

    List<WeCutomerRedEnvelopesVo> findRedEnveForGroupUser(@Param("chatId") String chatId, @Param("orderNo") String orderNo);

    WeRedEnvelopesCountVo countTab();

    List<WeRedEnvelopesCountVo> countLineChart(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("dates") List<String> dates);


    List<WeRedEnvelopesCountVo> findRecordGroupByUserId(@Param("startTime") String startTime,@Param("endTime") String endTime);


    int findAccpectMoney(@Param("orderNo")String orderNo);

    List<H5RedEnvelopesDetailDto.AccpestCustomer> findAccpectCustomers(@Param("orderNo") String orderNo);

}
