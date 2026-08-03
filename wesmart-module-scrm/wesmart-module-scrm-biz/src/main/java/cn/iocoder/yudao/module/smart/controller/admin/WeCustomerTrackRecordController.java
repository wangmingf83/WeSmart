package cn.iocoder.yudao.module.smart.controller.admin;

import cn.iocoder.yudao.module.smart.service.IWeCustomerTrackRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerTrackRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 跟进记录相关
 */
@Tag(name = "跟进记录相关")
@RestController
@RequestMapping("/smart/trackRecord")
public class WeCustomerTrackRecordController extends BaseController {

    @Autowired
    private IWeCustomerTrackRecordService iWeCustomerTrackRecordService;

    /**
     * 跟进记录
     * @param externalUserid
     * @param weUserId
     * @return
     */
    @Operation(summary = "跟进记录")
    @GetMapping("/followUpRecord")
    public TableDataInfo followUpRecord(String externalUserid, String weUserId){

        startPage();
        List<WeCustomerTrackRecord> trackRecords = iWeCustomerTrackRecordService.list(new LambdaQueryWrapper<WeCustomerTrackRecord>()
                .eq(StringUtils.isNotEmpty(externalUserid),WeCustomerTrackRecord::getExternalUserid, externalUserid)
                .eq(StringUtils.isNotEmpty(weUserId),WeCustomerTrackRecord::getWeUserId, weUserId));

        return getDataTable(trackRecords);

    }



}
