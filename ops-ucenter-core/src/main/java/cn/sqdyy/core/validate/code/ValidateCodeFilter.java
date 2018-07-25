package cn.sqdyy.core.validate.code;

import cn.sqdyy.core.constants.SecurityConstants;
import cn.sqdyy.core.properties.SecurityProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * description: 验证码拦截器
 * date: 2018/7/21 18:45
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    /** 存放所有需要校验验证码的url */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        // 初始化要拦截的url配置信息
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
