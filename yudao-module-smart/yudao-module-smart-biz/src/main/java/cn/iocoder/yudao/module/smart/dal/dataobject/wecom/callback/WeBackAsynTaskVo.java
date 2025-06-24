package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 异步任务完成通知
 * @date 2021/11/20 13:14
 **/
@Schema
@Data
public class WeBackAsynTaskVo extends WeBackBaseVo {

    @Schema(description = "异步任务")
    private BatchJobVo BatchJob;

    @Schema
    @Data
   public class  BatchJobVo{
       @Schema(description = "异步任务id")
       private String JobId;

       @Schema(description = "操作类型 目前分别有：sync_user(增量更新成员)、 replace_user(全量覆盖成员）、invite_user(邀请成员关注）、replace_party(全量覆盖部门)")
       private String JobType;

       @Schema(description = "返回码")
       private Integer ErrCode;

       @Schema(description = "对返回码的文本描述内容")
       private String ErrMsg;
   }

}
