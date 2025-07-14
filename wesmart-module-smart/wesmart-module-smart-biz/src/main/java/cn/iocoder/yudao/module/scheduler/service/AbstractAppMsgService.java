package cn.iocoder.yudao.module.scheduler.service;

import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import cn.iocoder.yudao.module.wecom.service.IQwAppMsgService;
import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.dataobject.msg.QwAppMsgBody;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.msg.WeAppMsgQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.msg.WeAppMsgVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * @author danmo
 * @description 消息发送抽象模板类
 * @date 2022/4/14 19:03
 **/
@Service
@Slf4j
public abstract class AbstractAppMsgService {

    @Resource
    private IQwAppMsgService qwAppMsgService;

    @Autowired
    private IWeCorpAccountService weCorpAccountService;

    protected JSONObject businessData;

    /**
     * 消息结果处理
     */
    protected void callBackResult(WeAppMsgVo appMsgVo) {

    }

    /**
     * 具体业务处理消息体
     *
     * @param appMsgBody
     * @return
     */
    protected abstract WeAppMsgQuery getWeAppMsg(QwAppMsgBody appMsgBody);


    public void sendAppMsg(QwAppMsgBody appMsgBody) {
        WeAppMsgVo data = new WeAppMsgVo();
        try {
            this.businessData = appMsgBody.getBusinessData();
            WeCorpAccount corpAccountVo = weCorpAccountService.getCorpAccountByCorpId(appMsgBody.getCorpId());
            WeAppMsgQuery weAppMsg = getWeAppMsg(appMsgBody);
            weAppMsg.setAgentid(corpAccountVo.getAgentId());
            data = qwAppMsgService.sendAppMsg(weAppMsg);
        } catch (Exception e) {
            log.error("sendAppMsg 执行异常: query:{}",JSONObject.toJSONString(appMsgBody),e);
            data.setErrMsg(e.getMessage());
            data.setErrCode(-1);
        }finally {
            callBackResult(data);
        }
    }


    public void sendAgentMsg(QwAppMsgBody appMsgBody) {
        WeAppMsgVo data = new WeAppMsgVo();
        try {
            WeAppMsgQuery weAppMsg = getWeAppMsg(appMsgBody);
            data = qwAppMsgService.sendAppMsg(weAppMsg);
        } catch (Exception e) {
            log.error("sendAgentMsg 执行异常: query:{}",JSONObject.toJSONString(appMsgBody),e);
            data.setErrMsg(e.getMessage());
            data.setErrCode(-1);
        }finally {
            callBackResult(data);
        }

    }

    public JSONObject getBusinessData() {
        return businessData;
    }

    public void setBusinessData(JSONObject businessData) {
        this.businessData = businessData;
    }
}
