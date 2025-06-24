package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 客服知识库
 * @date 2022/10/11 10:57
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeKfAddKnowledgeGroupVo extends WeResultVo {

    /**
     * 	分组ID
     */
    private String groupId;
}
