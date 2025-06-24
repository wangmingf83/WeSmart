package cn.iocoder.yudao.module.smart.dal.dataobject.qr.query;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.common.enums.WelcomeMsgTypeEnum;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeLxQrCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.qr.WeAddWayQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author danmo
 * @description 拉新活码新增入参
 * @date 2023/03/07 13:49
 **/
@Schema
@Data
public class WeLxQrAddQuery {

    @Schema(description = "活码Id",hidden = true)
    private Long qrId;

    @Schema(description = "名称")
    @NotEmpty(message = "活码名称不能为空")
    private String qrName;

    @Schema(description = "拉新方式 1：红包 2：卡券")
    @NotNull(message = "拉新方式不能为空")
    private Integer qrType;

    @Schema(description = "业务ID")
    private String businessId;

    @Schema(description = "业务数据")
    private String businessData;

    @Schema(description = "标签id列表")
    private List<String> qrTags;

    @Schema(description = "活码员工列表")
    @NotNull(message = "员工列表不能为空")
    private List<WeLxQrUserInfoQuery> qrUserInfos;

    @Schema(description = "欢迎语素材列表")
    @NotNull(message = "欢迎语素材不能为空")
    @Size(min = 1,max = 9,message = "欢迎语素材不能超过限制")
    private List<WeMessageTemplate> attachments;

    @Schema(description = "渠道",hidden = true)
    private String state;

    /**
     * 企微接口参数生成方法
     *
     * @return 企微接口参数实体类
     */
    public WeAddWayQuery getWeContactWay() {
        WeAddWayQuery weContactWay = new WeAddWayQuery();
        //根据类型生成相应的活码
        if(this.qrId == null){
            Snowflake snowflake = IdUtil.getSnowflake(RandomUtil.randomLong(6), RandomUtil.randomInt(6));
            this.state = WelcomeMsgTypeEnum.WE_LX_QR_CODE_PREFIX.getType() + snowflake.nextIdStr();
            weContactWay.setState(state);
        }
        weContactWay.setType(2);
        weContactWay.setScene(WeConstans.QR_CODE_EMPLE_CODE_SCENE);
        if (CollectionUtil.isNotEmpty(qrUserInfos)) {
            //员工列表
            List<String> userIdArr = qrUserInfos.stream().map(WeLxQrUserInfoQuery::getUserIds)
                    .filter(CollectionUtil::isNotEmpty).flatMap(Collection::stream).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(userIdArr)) {
                weContactWay.setUser(userIdArr);
            }
            //部门列表
            List<Long> partyArr = qrUserInfos.stream().map(WeLxQrUserInfoQuery::getPartys)
                    .filter(CollectionUtil::isNotEmpty).flatMap(Collection::stream).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(partyArr)) {
                weContactWay.setParty(partyArr);
            }
        }
        return weContactWay;
    }


    /**
     * 获取数据表实体
     * @return
     * @param configId 二维码配置ID
     * @param qrCode 二维码链接
     */
    public WeLxQrCode getLxQrCodeEntity(String configId, String qrCode){
        WeLxQrCode weLxQrCode = new WeLxQrCode();
        weLxQrCode.setId(this.qrId);
        weLxQrCode.setName(this.qrName);
        weLxQrCode.setType(this.qrType);
        if(this.qrId == null){
            weLxQrCode.setState(state);
        }
        weLxQrCode.setConfigId(configId);
        weLxQrCode.setQrCode(qrCode);
        weLxQrCode.setBusinessId(this.businessId);
        weLxQrCode.setBusinessData(this.businessData);
        return weLxQrCode;
    }

}
