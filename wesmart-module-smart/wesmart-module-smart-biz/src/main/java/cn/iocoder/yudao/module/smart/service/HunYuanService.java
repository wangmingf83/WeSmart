package cn.iocoder.yudao.module.smart.service;

import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.smart.config.HunYuanClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.hunyuan.v20230901.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * @author danmo
 * @date 2023-11-30 13:41
 **/
@Service
public class HunYuanService {
    @Autowired
    private GuituAiConfig GuituAiConfig;

    public void sendMsg(Message [] msgList, Consumer<String> consumer) {
        HunYuanClient client = new HunYuanClient(new Credential(GuituAiConfig.getTxAiSecretId(), GuituAiConfig.getTxAiSecretKey()), GuituAiConfig.getTxAiRegion());
        client.sendMsg(msgList, consumer);
    }
}
