package cn.iocoder.yudao.module.system.active_sso_client;


import cn.iocoder.yudao.config.RagAgentProperties;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;


/**
 * 这不是标准的SSO实现方式，是为了主动去触发客户端登录服务的。
 * 因为如下OAuth2的授权模式下不会在系统登录后主动去触发客户端SSO
 *
 * #仅适用于 在 OAuth2 授权模式下，直接从 http://ruoyi.example.com/auth/oauth/authorize 访问时会生效。
 * #如果你是从系统自己的 /login 进行普通用户登录，则不会自动回调。如果想让系统在普通模式下登录即调用，让客户端同时完成登录。见AuthController类
 * #security:
 * #  oauth2:
 * #    clients:
 * #      ragagent:
 * #        client-id: "ragagent"
 * #        client-secret: "ragagent"
 * #        authorized-grant-types: authorization_code
 * #        scope: read
 * #        redirect-uri: "http://192.168.17.9:9380/v1/ssoclient/ruoyi_callback"  # Flask 客户端的回调地址
 */
@Component
@Slf4j
public class ActiveSsoClientHandler {

    @Resource
    private AdminUserService userService;
    @Resource
    private RagAgentProperties ragAgentProperties;


    public void ragAgent(AuthLoginRespVO authResp, HttpServletResponse response) {
        System.out.println(ragAgentProperties.getCallbackUrl());
        response.setHeader("Authorization", "Bearer " + authResp.getAccessToken());
        String callbackUrl = ragAgentProperties.getCallbackUrl();  // Flask 处理的回调地址

        try {
            // 4. 发送 HTTP 请求到 Flask 端，通知用户已登录
            URL url = new URL(callbackUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送 JSON 数据
            String jsonInputString = JsonUtils.toJsonString(authResp);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
//            System.out.println(url.getContent());

            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();
            if (responseCode != 200) {
                log.error("Flask 回调失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("回调 Flask 失败: " + e.getMessage());
        }
    }

    public void userSynchronize(Long userId) {
        AdminUserDO userDO = userService.getUser(userId);
        System.out.println(ragAgentProperties.getRegisterUrl());
        String register = ragAgentProperties.getRegisterUrl();  // Flask 处理的回调地址

        try {
            // 4. 发送 HTTP 请求到 Flask 端，通知用户已登录
            URL url = new URL(register);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 发送 JSON 数据
            String jsonInputString = JsonUtils.toJsonString(userDO);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
//            System.out.println(url.getContent());

            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();
            if (responseCode != 200) {
                log.error("Flask 回调失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("回调 Flask 失败: " + e.getMessage());
        }
    }
}
