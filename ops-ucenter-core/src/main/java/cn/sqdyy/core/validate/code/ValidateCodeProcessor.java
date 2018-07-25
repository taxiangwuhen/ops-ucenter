package cn.sqdyy.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * description: 验证码处理器接口
 * date: 2018/7/21 17:55
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
public interface ValidateCodeProcessor {

    /** 验证码放入session时的前缀 */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /** 创建验证码 */
    void create(ServletWebRequest request) throws Exception;

    /** 校验验证码 */
    void validate(ServletWebRequest request);
}
