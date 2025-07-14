package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.moment;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.moment.WeMomentEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @Description 获取企业全部的发表列表
 * @date 2021/12/2 16:11
 **/
@Schema
@Data
public class WeMomentListVo extends WeResultVo {
    @Schema(description = "朋友圈列表")
    private List<WeMomentEntity> momentList;
}
