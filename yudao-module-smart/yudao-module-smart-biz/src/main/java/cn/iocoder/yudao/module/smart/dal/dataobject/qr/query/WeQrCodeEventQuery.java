package cn.iocoder.yudao.module.smart.dal.dataobject.qr.query;

import org.springframework.context.ApplicationEvent;

/**
 * @author danmo
 * @description
 * @date 2021/11/21 19:52
 **/
public class WeQrCodeEventQuery extends ApplicationEvent {

    private String qrId;

    public WeQrCodeEventQuery(Object source, String qrId) {
        super(source);
        this.qrId = qrId;
    }

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }
}
