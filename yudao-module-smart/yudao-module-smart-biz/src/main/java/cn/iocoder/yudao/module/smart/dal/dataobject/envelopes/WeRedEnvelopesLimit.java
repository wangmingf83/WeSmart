package cn.iocoder.yudao.module.smart.dal.dataobject.envelopes;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("we_red_envelopes_limit")
@Schema(description = "红包限制")
@AllArgsConstructor
@NoArgsConstructor
public class WeRedEnvelopesLimit extends BaseEntity {
    @TableId
    @Schema(description = "主键")
    private Long id;
    @Schema(description = "单日付款总额")
    private int singleDayPay;
    @Schema(description = "单日客户收红包次数")
    private int singleCustomerReceiveNum;
    @Schema(description = "单日每客户收红包总额")
    private int singleCustomerReceiveMoney;
}
