package cn.sqdyy.core.authentication;

import cn.sqdyy.core.enums.ResponseType;
import cn.sqdyy.core.properties.SecurityProperties;
import cn.sqdyy.core.vo.DefaultResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: 自定义认证成功处理器
 * 问：为什么继承SavedRequestAwareAuthenticationSuccessHandler类而不是实现AuthenticationSuccessHandler接口
 * 答：SavedRequestAwareAuthenticationSuccessHandler中的savedRequest保存了上一次请求的路径，如果认证成功，会自动跳转到上一次请求的路径上
 * date: 2018/7/21 15:59
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Component("opsAuthenticationSuccessHandler")
@Slf4j
public class OpsAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("login success: {}", objectMapper.writeValueAsString(authentication));
        if (ResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            // 需要响应JSON类型
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new DefaultResponse<>(HttpStatus.OK, "请求成功", authentication)));
        } else {
            // 需要自动跳转到上一次请求的路径
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
