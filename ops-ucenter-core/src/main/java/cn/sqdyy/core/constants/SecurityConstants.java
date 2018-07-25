package cn.sqdyy.core.constants;

import cn.sqdyy.core.validate.code.ValidateCodeGenerator;

/**
 * description: security 公共常量
 * date: 2018/7/21 15:31
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
public interface SecurityConstants {
    /** 当请求需要身份认证时，默认跳转的URL */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
    /** 默认的用户名密码请求处理URL */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    /** 默认登录页面 */
    String DEFAULT_LOGIN_PAGE_URL = "/ops-signIn.html";
    /** 默认验证码处理映射的URL前缀 */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
    /** 默认图片验证码参数名 */
    String DEFAULT_PARAMETER_NAME_IMAGE_CODE = "imageCode";
    /** 以.html为后缀 */
    String HTML_SUFFIX = ".html";
    /** 验证码处理器类名后缀，验证码处理器一律需要使用此后缀 */
    String VALIDATE_CODE_PROCESSOR_CLASSNAME_SUFFIX = "ValidateCodeProcessor";
    /** 验证码generator类名后缀，验证码处理器一律需要使用此后缀 */
    String VALIDATE_CODE_GENERATOR_CLASSNAME_SUFFIX = "ValidateCodeGenerator";

}
