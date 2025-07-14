package cn.iocoder.yudao.module.smart.dal.dataobject.form.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @date 2022年09月20日 18:24
 */
@Data
public class WeAddFormSurveyAnswerQuery {


    @Schema(description = "id(修改是传)")
    private Long id;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    //@NotNull(message = "手机号不能为空")
    private String mobile;


    /**
     * 用户姓名
     */
    @Schema(description = "用户姓名")
    private String name;


    /**
     * 用户头像
     */
    @Schema(description = "用户头像")
    private String avatar;


    /**
     * 详细地址
     */
    @Schema(description = "详细地址")
    private String addr;


    /**
     * 城市
     */
    @Schema(description = "城市")
    private String city;


    /**
     * 微信openID
     */
    @Schema(description = "微信openID")
    private String openId;


    /**
     * 微信unionID
     */
    @Schema(description = "微信unionID")
//    @NotEmpty(message = "用户ID不能为空")
    private String unionId;


    /**
     * 答题开始时间
     */
    @Schema(description = "答题开始时间")
    private Date anTime;


    /**
     * 答题用时
     */
    @Schema(description = "答题用时")
    private Float totalTime;


    /**
     * ip地址
     */
    @Schema(description = "ip地址")
    private String ipAddr;


    /**
     * 答案
     */
    @Schema(description = "答案")
    private String answer;


    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    private Long belongId;


    /**
     * 是否完成;0完成，1未完成
     */
    @Schema(description = "是否完成;0完成，1未完成")
    private Integer anEffective;


    /**
     * 答题数
     */
    @Schema(description = "答题数")
    private Integer quNum;


    /**
     * 数据来源
     */
    @Schema(description = "数据来源")
    private String dataSource;
}
