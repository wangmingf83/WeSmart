package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeGroupSopMaterial;
import java.util.List;

/**
 * 群SOP - 素材 关联 Mapper
 */
public interface WeGroupSopMaterialMapper extends BaseMapper<WeGroupSopMaterial> {

    /**
     * 批量保存 sop规则与素材的绑定
     * @param sopMaterialList 待绑定列表
     * @return 结果
     */
    int batchBindsSopMaterial(List<WeGroupSopMaterial> sopMaterialList);
}
