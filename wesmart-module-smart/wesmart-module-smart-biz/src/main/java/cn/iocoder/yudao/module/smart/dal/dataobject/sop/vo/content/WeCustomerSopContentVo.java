package cn.iocoder.yudao.module.smart.dal.dataobject.sop.vo.content;

import cn.iocoder.yudao.module.smart.dal.dataobject.sop.WeSopAttachments;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * 展示客户客群需要发送或已发送sop内容
 */
@Data
public class WeCustomerSopContentVo extends WeCustomerSopBaseContentVo {

    //目标推送附件
    private List<WeSopAttachments> weQrAttachments;

    //实际推送附件
    private List<WeSopAttachments> actualPushWeQrAttachments;


    public void addWeQrAttachments(WeSopAttachments weQrAttachment,Integer executeState){
        if(null == weQrAttachments){
            weQrAttachments=new ArrayList<>();
        }
        if(null == actualPushWeQrAttachments){
            actualPushWeQrAttachments=new ArrayList<>();
        }

        weQrAttachments.add(weQrAttachment);

        if(executeState==1){
            actualPushWeQrAttachments.add(weQrAttachment);
        }

    }

}
