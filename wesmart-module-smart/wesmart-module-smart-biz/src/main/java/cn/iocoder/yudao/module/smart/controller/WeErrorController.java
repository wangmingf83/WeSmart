package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeErrorMsgService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 获取错误信息
 */
@RestController
@RequestMapping("/error")
public class WeErrorController extends BaseController {

    @Autowired
    private IWeErrorMsgService iWeErrorMsgService;


    /**
     *  获取错误信息列表
     * @param weErrorMsg
     * @return
     */
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
    @PostMapping("/removeWeError")
    public AjaxResult removeWeError(){
        iWeErrorMsgService.remove(new LambdaQueryWrapper<>());

        return AjaxResult.success();
    }





}
