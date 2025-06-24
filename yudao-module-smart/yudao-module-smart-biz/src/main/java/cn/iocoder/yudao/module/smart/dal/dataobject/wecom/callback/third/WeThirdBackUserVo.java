package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.third;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 成员变更通知
 * @date 2021/11/20 13:02
 **/
@Schema
@Data
public class WeThirdBackUserVo extends WeThirdBackBaseVo {

    @Schema(description = "成员UserID")
    private String UserID;

    @Schema(description = "对于同一个服务商，不同应用获取到企业内同一个成员的OpenUserID是相同的")
    private String OpenUserID;

    @Schema(description = "成员名称")
    private String Name;

    @Schema(description = "成员部门列表")
    private String Department;

    @Schema(description = "主部门")
    private String MainDepartment;

    @Schema(description = "表示所在部门是否为上级，0-否，1-是")
    private String IsLeaderInDept;

    @Schema(description = "职位信息")
    private String Position;

    @Schema(description = "手机号码")
    private String Mobile;

    @Schema(description = "性别")
    private Integer Gender;

    @Schema(description = "邮箱")
    private String Email;

    @Schema(description = "激活状态")
    private Integer Status;

    @Schema(description = "头像url")
    private String Avatar;

    @Schema(description = "成员别名")
    private String Alias;

    @Schema(description = "座机")
    private String Telephone;
}
