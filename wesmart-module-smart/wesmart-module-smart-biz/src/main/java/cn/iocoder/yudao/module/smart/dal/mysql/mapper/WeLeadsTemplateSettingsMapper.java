package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.leads.template.entity.WeLeadsTemplateSettings;
import cn.iocoder.yudao.module.smart.dal.dataobject.leads.template.vo.WeLeadsTemplateSettingsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 公海模版配置表(WeSeaLeadsTemplateSettings)表数据库访问层
 *
 * @author zhaoyijie
 * @since 2023-04-04 09:49:29
 */
@Mapper
public interface WeLeadsTemplateSettingsMapper extends BaseMapper<WeLeadsTemplateSettings> {

    List<WeLeadsTemplateSettingsVO> queryWithTableEntryContent();

}