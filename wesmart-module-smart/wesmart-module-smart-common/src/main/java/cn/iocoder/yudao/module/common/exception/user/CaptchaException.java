package cn.iocoder.yudao.module.common.exception.user;

/**
 * 验证码错误异常类
 *
 * @author ruoyi
 */
public class CaptchaException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg)
    {
        super(msg);
    }
}
