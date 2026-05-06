package cn.iocoder.yudao.module.smart.framework.rpc.config;

import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import cn.iocoder.yudao.module.rag.api.RagApi;
import cn.iocoder.yudao.module.rag.api.RagKfConversationApi;
import cn.iocoder.yudao.module.rag.api.RagKfMessageApi;
import cn.iocoder.yudao.module.smart.api.CorpAccountApi;
import cn.iocoder.yudao.module.system.api.area.SysAreaCrmApi;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.PostApi;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.SysUserCrmApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;


@Configuration(value = "smartRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {DictDataApi.class, FileApi.class, AdminUserApi.class,
        BpmProcessInstanceApi.class, SysUserCrmApi.class, SysAreaCrmApi.class, DeptApi.class, PostApi.class,
        RagApi.class, RagKfConversationApi.class, RagKfMessageApi.class, CorpAccountApi.class})
public class RpcConfiguration {
}
