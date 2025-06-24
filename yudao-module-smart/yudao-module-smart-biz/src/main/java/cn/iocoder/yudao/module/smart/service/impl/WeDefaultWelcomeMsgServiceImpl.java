package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeDefaultWelcomeMsgService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.welcomemsg.WeDefaultWelcomeMsg;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeDefaultWelcomeMsgMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author robin
* @description 针对表【we_default_welcome_msg(默认欢迎语附件)】的数据库操作Service实现
* @createDate 2023-10-23 11:15:10
*/
@Service
public class WeDefaultWelcomeMsgServiceImpl extends ServiceImpl<WeDefaultWelcomeMsgMapper, WeDefaultWelcomeMsg>
    implements IWeDefaultWelcomeMsgService {

    @Override
    public void saveOrUpdateBatchWeComeMsg(List<WeMessageTemplate> attachments) {
        this.remove(new LambdaQueryWrapper<>());
        List<WeDefaultWelcomeMsg> attachmentsList = Optional.ofNullable(attachments)
                .orElseGet(ArrayList::new).stream().map(weQrAttachmentsQuery -> {
                    WeDefaultWelcomeMsg weDefaultWelcomeMsg = new WeDefaultWelcomeMsg();
                    BeanUtil.copyProperties(weQrAttachmentsQuery, weDefaultWelcomeMsg);
                    return weDefaultWelcomeMsg;
                }).collect(Collectors.toList());
        saveOrUpdateBatch(attachmentsList);
    }

    @Override
    public List<WeMessageTemplate> findWeMessageTemplates() {
        List<WeMessageTemplate> messageTemplates=new ArrayList<>();
        List<WeDefaultWelcomeMsg> weDefaultWelcomeMsgs = this.list();
        if(CollectionUtil.isNotEmpty(weDefaultWelcomeMsgs)){
            weDefaultWelcomeMsgs.stream().forEach(weDefaultWelcomeMsg->{

                WeMessageTemplate weMessageTemplate = new WeMessageTemplate();

                BeanUtil.copyProperties(weDefaultWelcomeMsg, weMessageTemplate);
                messageTemplates.add(weMessageTemplate);
            });

        }
        return messageTemplates;
    }


}




