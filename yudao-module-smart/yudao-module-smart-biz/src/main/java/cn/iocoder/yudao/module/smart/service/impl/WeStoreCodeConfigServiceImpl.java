package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.fileservice.service.IFileService;
import cn.iocoder.yudao.module.smart.service.IWeQrAttachmentsService;
import cn.iocoder.yudao.module.smart.service.IWeStoreCodeConfigService;
import cn.iocoder.yudao.module.smart.service.IWeStoreCodeService;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.enums.WelcomeMsgTypeEnum;
import cn.iocoder.yudao.module.common.utils.QREncode;

import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.entity.WeStoreCodeConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.query.WxStoreCodeQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.qr.WeAddWayQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.qr.WeAddWayVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeStoreCodeConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 门店导购码或门店群活码
 */
@Service
public class WeStoreCodeConfigServiceImpl extends ServiceImpl<WeStoreCodeConfigMapper, WeStoreCodeConfig> implements IWeStoreCodeConfigService {

    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    private IWeQrAttachmentsService attachmentsService;

    @Resource
    private IFileService fileService;

    @Autowired
    private GuituAiConfig GuituAiConfig;

    @Autowired
    private IWeStoreCodeService iWeStoreCodeService;


    @Override
    public void createOrUpdate(WeStoreCodeConfig storeCodeConfig) throws IOException {


        if(StringUtils.isNotEmpty(storeCodeConfig.getConfigId())){//更新联系我
            qwCustomerService.updateContactWay(WeAddWayQuery.builder()
                    .config_id(storeCodeConfig.getConfigId())
                    .user(ListUtil.toList(storeCodeConfig.getCustomerServiceId()))
                    .build());
        }else{//新增联系我
            storeCodeConfig.setState(WelcomeMsgTypeEnum.WE_STORE_CODE_CONFIG_PREFIX.getType() + SnowFlakeUtil.nextId());
            WeAddWayVo weAddWayResult = qwCustomerService.addContactWay(WeAddWayQuery.builder()
                    .type(1)
                    .scene(2)
                    .state(storeCodeConfig.getState())
                    .user(ListUtil.toList(storeCodeConfig.getCustomerServiceId()))
                    .build());
            storeCodeConfig.setConfigId(weAddWayResult.getConfigId());
            storeCodeConfig.setCustomerServiceUrl(weAddWayResult.getQrCode());
        }

        if(StringUtils.isEmpty(storeCodeConfig.getStoreCodeConfigUrl())){//生成对应的唯一二维码


            String contentUrl =Integer.valueOf(1).equals(storeCodeConfig.getStoreCodeType())?GuituAiConfig.getGuideCodeUrl():GuituAiConfig.getGuideGroupUrl();

            String url = fileService.uploadFile(QREncode.getQRCodeMultipartFile(contentUrl, null));
            if(null != url){
                storeCodeConfig.setStoreCodeConfigQr(url);
            }
               storeCodeConfig.setStoreCodeConfigUrl(contentUrl);
        }

         if(saveOrUpdate(storeCodeConfig)){

             attachmentsService.updateBatchByQrId(storeCodeConfig.getId(),2, storeCodeConfig.getAttachments());

         }
    }

    @Override
    public WeStoreCodeConfig getWeStoreCodeConfig(WxStoreCodeQuery wxStoreCodeQuery) {

        if(wxStoreCodeQuery.getIsCount()){
            iWeStoreCodeService.countUserBehavior(wxStoreCodeQuery);
        }

        return this.baseMapper.getWeStoreCodeConfig(wxStoreCodeQuery.getStoreCodeType());
    }


}
