package cn.sqdyy.browser.authentication;

import cn.sqdyy.core.constants.SecurityConstants;
import cn.sqdyy.core.properties.SecurityProperties;
import cn.sqdyy.core.vo.DefaultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: 提供authentication相关的mapping
 * date: 2018/7/21 16:14
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当请求需要身份认证时，默认跳转的URL
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public DefaultResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是: {}", redirectUrl);
            if(StringUtils.endsWithIgnoreCase(redirectUrl, SecurityConstants.HTML_SUFFIX)) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new DefaultResponse(HttpStatus.UNAUTHORIZED, "访问的服务需要身份认证，请引导用户到登录页");
    }
}
