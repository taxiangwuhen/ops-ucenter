package cn.sqdyy.core.authentication;

import cn.sqdyy.core.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * description: abstract security config 提供了基础的认证配置：
 * formLogin：启用表单认证
 * loginPage：当请求需要身份认证时，默认跳转的URL
 * loginProcessingUrl：默认的用户名密码请求处理URL
 * successHandler：自定义认证成功处理器
 * failureHandler：自定义认证失败处理器
 * date: 2018/7/21 15:30
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
public class AbstractSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler opsAuthenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler opsAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(opsAuthenticationSuccessHandler)
                .failureHandler(opsAuthenticationFailureHandler);
    }
}
