package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.wecom.service.IQwTicketService;
import cn.iocoder.yudao.module.common.utils.wecom.TicketUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danmo
 * @description h5 ticket校验接口
 * @date 2021/1/6 11:23
 **/
@Tag(name = " h5 ticket校验接口")
@Slf4j
@RequestMapping(value = "ticket")
@RestController
public class WeTicketController extends BaseController {

    @Autowired
    private IQwTicketService qwTicketService;




    /**
     * 获取企业的jsapi_ticket
     *
     * @param url JS接口页面的完整URL
     * @return
     */
    @GetMapping("/getAppTicket")
    public AjaxResult getAppTicket(String url) {
        WeBaseQuery query = new WeBaseQuery();
        query.setCorpid(ScrmSecurityUtils.getLoginUser().getCorpId());
        String ticket = qwTicketService.getCorpTicket(query);
        return AjaxResult.success(TicketUtils.getSignatureMap(ticket,url));
    }

    /**
     * 获取应用的jsapi_ticket
     *
     * @param url JS接口页面的完整URL
     * @return
     */
    @GetMapping("/getAgentTicket")
    public AjaxResult getAgentTicket(String url) {
        WeBaseQuery query = new WeBaseQuery();
        query.setCorpid(ScrmSecurityUtils.getCorpId());
        String ticket = qwTicketService.getAgentTicket(query);
        return AjaxResult.success(TicketUtils.getSignatureMap(ticket,url));
    }

}
