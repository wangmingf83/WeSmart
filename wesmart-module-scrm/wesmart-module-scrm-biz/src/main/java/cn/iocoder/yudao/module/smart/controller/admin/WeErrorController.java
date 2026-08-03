package cn.iocoder.yudao.module.smart.controller.admin;

import cn.iocoder.yudao.module.smart.service.IWeErrorMsgService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeErrorMsg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 企业微信发生的错误信息
 * @author 圭图智能
 * @date 2024-05-02
 */
@Tag(name = "企业微信发生的错误信息")
@RestController
@RequestMapping("/smart/error")
public class WeErrorController extends BaseController {

    @Autowired
    private IWeErrorMsgService iWeErrorMsgService;


    /**
     *  获取错误信息列表
     * @param weErrorMsg
     * @return
     */
    @Operation(summary = "获取错误信息列表")
    @GetMapping("/findWeError")
    public TableDataInfo findWeError(WeErrorMsg weErrorMsg){
        startPage();
        return getDataTable(
                iWeErrorMsgService.list(
                        new LambdaQueryWrapper<WeErrorMsg>()
                                .like(StringUtils.isNotEmpty(weErrorMsg.getUrl()), WeErrorMsg::getUrl, weErrorMsg.getUrl())
                                .apply(weErrorMsg.getBeginTime() != null && weErrorMsg.getEndTime() != null,
                                        "date_format(create_time,'%Y-%m-%d') BETWEEN '"+
                                                weErrorMsg.getBeginTime()
                                                +"' AND '"+
                                                weErrorMsg.getEndTime()+"'")
                                .orderByDesc(WeErrorMsg::getCreateTime)
                )
        );
    }


    /**
     * 清空所有数据
     * @return
     */
    @Operation(summary = "清空所有数据")
    @PostMapping("/removeWeError")
    public AjaxResult removeWeError(){
        iWeErrorMsgService.remove(new LambdaQueryWrapper<>());

        return AjaxResult.success();
    }





}
