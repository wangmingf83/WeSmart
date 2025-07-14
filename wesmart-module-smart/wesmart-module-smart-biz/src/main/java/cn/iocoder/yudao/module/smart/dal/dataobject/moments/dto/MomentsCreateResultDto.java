package cn.iocoder.yudao.module.smart.dal.dataobject.moments.dto;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * 朋友圈创建结果
 */
@Data
public class MomentsCreateResultDto extends WeResultVo {

    /**
     *  任务状态，整型，1表示开始创建任务，2表示正在创建任务中，3表示创建任务已完成
     */
    private Integer status;

    /**
     * 操作类型，字节串，此处固定为add_moment_task
     */
    private String type;

    private Result result;


    @Data
    public static class Result extends WeResultVo {

        /**
         * 朋友圈id
         */
        private String momentId;

        @Schema(description = "指定的发表范围")
        private WeMomentSendVo invalidSenderList;

        @Schema(description = "可见到该朋友圈的客户列表")
        private WeMomentCustomerVo invalidExternalContactList;
    }

    @Schema
    @Data
    public static class WeMomentSendVo {

        @Schema(description = "发表任务的执行者用户列表，最多支持10万个")
        private List<String> userList;


        @Schema(description = "发表任务的执行者部门列表")
        private List<String> departmentList;
    }

    @Schema
    @Data
    public static class WeMomentCustomerVo {

        @Schema(description = "可见到该朋友圈的客户标签列表")
        private List<String> tagList;

    }

}
