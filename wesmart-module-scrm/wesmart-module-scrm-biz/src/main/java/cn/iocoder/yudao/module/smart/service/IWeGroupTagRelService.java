package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupTagRel;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.query.WeMakeGroupTagQuery;

import java.util.List;

/**
 * 群标签关系(WeGroupTagRel)
 *
 * @author 圭图智能
 * @since 2022-04-06 11:09:57
 */
public interface IWeGroupTagRelService extends IService<WeGroupTagRel> {

    /**
     * 客群标签编辑
     * @param query
     */
    void makeGroupTag(WeMakeGroupTagQuery query);

    List<WeGroupTagRel> selectGroupTagList(WeMakeGroupTagQuery query);
}
