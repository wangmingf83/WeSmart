package cn.iocoder.yudao.module.smart.api;

import cn.iocoder.yudao.module.smart.api.vo.WeCorpAccountVo;
import cn.iocoder.yudao.module.smart.api.vo.WeLoginUserVo;
import cn.iocoder.yudao.module.smart.api.vo.WxLoginUser;
import cn.iocoder.yudao.module.smart.api.vo.WxUserVo;
import com.fhs.core.trans.anno.AutoTrans;
import com.fhs.trans.service.AutoTransable;
import feign.FeignIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cn.iocoder.yudao.module.smart.enums.ApiConstants;

import java.util.Map;

import static cn.iocoder.yudao.module.smart.api.CorpAccountApi.PREFIX;

@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - Scrm企微配置服务")
@AutoTrans(namespace = PREFIX, fields = {"nickname"})
public interface CorpAccountApi extends AutoTransable<WeCorpAccountVo> {

    String PREFIX = ApiConstants.PREFIX + "/corp";

    @GetMapping(PREFIX + "/getCorpAccountByCorpId")
    @Operation(summary = "通过用户 corpId 查询")
    @Parameter(name = "corpId", description = "编号", example = "1", required = true)
    WeCorpAccountVo getCorpAccountByCorpId(@RequestParam("corpId") String corpId);

    @GetMapping(PREFIX + "/getCorpAccountByTenantId")
    @Operation(summary = "通过用户 tenantId 查询")
    @Parameter(name = "tenantId", description = "编号", example = "1", required = true)
    WeCorpAccountVo getCorpAccountByTenantId(@RequestParam("tenantId") Long tenantId);

    @GetMapping(PREFIX + "/getWxWkLoginUser")
    @Operation(summary = "通过用户 authCode 查询")
    @Parameter(name = "authCode", description = "编号", example = "1", required = true)
    WeLoginUserVo getWxWkLoginUser(@RequestParam("authCode") String authCode);

    @PostMapping(PREFIX + "/getWxLoginUser")
    @Operation(summary = "获取登录的微信用户信息")
    WxUserVo getWxLoginUser(@RequestBody Map wxuser);

    @GetMapping(PREFIX + "/getWxCpTicket")
    @Operation(summary = "获取微信服务号js-sdk授权ticket")
    @Parameter(name = "url", description = "应用域名", example = "guituai.cn", required = true)
//    Map<String, Object> getWxCpTicket(@RequestParam("url") String url);
    String getWxCpTicket(@RequestParam("url") String url);

    /**
     * 微信H5登录，同时会将用户保持或更新到wx_user表中，可以做为扩新用户更新到公海。
     *
     * @param authCode
     * @return
     */
    @GetMapping(PREFIX + "/getWxToken")
    @Operation(summary = "通过用户 authCode 查询")
    @Parameter(name = "authCode", description = "编号", example = "1", required = true)
    WxLoginUser getWxToken(@RequestParam("authCode") String authCode);

    @GetMapping(PREFIX + "/customerLogin")
    @Operation(summary = "移动端h5登录")
    @Parameter(name = "authCode", description = "编号", example = "1", required = true)
    WeLoginUserVo customerLogin(@RequestParam("authCode") String authCode);

    @GetMapping(PREFIX + "/getMobileDomain")
    @Operation(summary = "通过H5移动端域名 查询")
    String getMobileDomain();

    @Override
    @FeignIgnore
    default WeCorpAccountVo selectById(Object id) {
        return null;
    }
}
