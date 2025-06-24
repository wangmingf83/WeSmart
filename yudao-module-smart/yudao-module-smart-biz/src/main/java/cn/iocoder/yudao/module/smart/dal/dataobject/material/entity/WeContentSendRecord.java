package cn.iocoder.yudao.module.smart.dal.dataobject.material.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("we_content_send_record")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "发送人信息记录")
public class WeContentSendRecord extends BaseEntity {

    @TableId
    private Long id;

    /**
     * 话术Id
     */
    private Long talkId;

    /**
     * 素材Id
     */
    private Long contentId;

    /**
     * 发送者
     */
    private String sendBy;

    /**
     * 发送者Id
     */
    private Long sendById;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;

    /**
     * 素材来源(1素材，2话术)
     */
    private Integer resourceType;

    @TableField(exist = false)
    private Integer sendCount;




}
