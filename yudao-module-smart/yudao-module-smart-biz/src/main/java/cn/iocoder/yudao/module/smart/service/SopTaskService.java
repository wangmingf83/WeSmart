package cn.iocoder.yudao.module.smart.service;

import cn.iocoder.yudao.module.smart.dal.dataobject.sop.dto.WeSopBaseDto;

public interface SopTaskService {


    /**
     * 更新或新建sop执行计划
     * @param weSopBaseDto
     */
    void createOrUpdateSop(WeSopBaseDto weSopBaseDto);

    /**
     * 构建新客计划
     */
    void builderXkPlan();
}
