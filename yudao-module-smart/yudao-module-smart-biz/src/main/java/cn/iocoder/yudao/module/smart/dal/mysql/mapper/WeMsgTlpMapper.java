package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.query.WeMsgTlpQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.vo.WeMsgTlpVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeMsgTlp;

/**
 * 欢迎语模板表(WeMsgTlp)
 *
 * @author danmo
 * @since 2022-03-28 10:21:23
 */
@Repository()
@Mapper
public interface WeMsgTlpMapper extends BaseMapper<WeMsgTlp> {



    List<WeMsgTlpVo> getList(@Param("query") WeMsgTlpQuery query, @Param("userIds") List<String> userIds);
}

