package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeGroupSopPic;

import java.util.List;

/**
 * 图片语sop规则绑定信息 mapper
 */
public interface WeGroupSopPicMapper extends BaseMapper<WeGroupSopPic> {

    /**
     * 批量保存群SOP和图片
     *
     * @param sopPicList WeGroupSopPic对象列表
     * @return 结果
     */
    int batchSopPic(List<WeGroupSopPic> sopPicList);
}
