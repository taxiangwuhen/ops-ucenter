package cn.sqdyy.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * description: 验证码异常处理类
 * date: 2018/7/21 18:30
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
