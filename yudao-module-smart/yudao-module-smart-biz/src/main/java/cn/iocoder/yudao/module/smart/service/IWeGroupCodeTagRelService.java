package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCodeTagRel;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.query.WeMakeGroupCodeTagQuery;

public interface IWeGroupCodeTagRelService extends IService<WeGroupCodeTagRel> {

    /**
     * 客群活码标签编辑
     * @param query
     */
    void makeGroupCodeTag(WeMakeGroupCodeTagQuery query);

}
