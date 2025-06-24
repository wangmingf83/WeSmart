package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveAuditScope;

/**
 * 敏感词审计范围(WeSensitiveAuditScope)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
@Repository()
@Mapper
public interface WeSensitiveAuditScopeMapper extends BaseMapper<WeSensitiveAuditScope> {


}

