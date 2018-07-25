package cn.sqdyy.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * description: 验证码generate
 * date: 2018/7/21 18:03
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
public interface ValidateCodeGenerator {

    /** 生成验证码，并封装到validateCode中 */
    ValidateCode generate(ServletWebRequest request);
}
