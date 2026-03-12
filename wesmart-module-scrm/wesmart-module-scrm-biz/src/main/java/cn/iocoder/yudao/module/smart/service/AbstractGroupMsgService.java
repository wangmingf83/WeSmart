package cn.iocoder.yudao.module.smart.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 群发消息发送业务回调
 * @author 圭图智能
 * @date 2024/4/14 19:03
 **/
@Service
@Slf4j
public abstract class AbstractGroupMsgService {
    /**
     * 具体业务处理消息
     *
     * @param query
     * @return
     */
    public abstract void callBackHandler(JSONObject query);

}
