package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 企业id相关配置(WeCorpAccount)
 *
 * @author danmo
 * @since 2022-03-08 19:01:13
 */
@Repository()
@Mapper
public interface WeCorpAccountMapper extends BaseMapper<WeCorpAccount> {

}

