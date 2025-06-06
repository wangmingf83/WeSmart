package cn.iocoder.yudao.module.common.exception;

/**
 * 内部认证异常
 *
 * @author xueyi
 */
public class InnerAuthException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public InnerAuthException(String message)
    {
        super(message);
    }
}
