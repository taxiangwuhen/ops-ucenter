package cn.sqdyy.browser.authentication;

import cn.sqdyy.core.authentication.AbstractSecurityConfig;
import cn.sqdyy.core.constants.SecurityConstants;
import cn.sqdyy.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * description: browser security config
 * date: 2018/7/21 15:27
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Configuration
public class BrowserSecurityConfig  extends AbstractSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.applyPasswordAuthenticationConfig(http);
        http.authorizeRequests()
                    .antMatchers(
                            //SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                            securityProperties.getBrowser().getLoginPage(),
                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                            SecurityConstants.DEFAULT_UNAUTHENTICATION_URL
                    ).permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .csrf().disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
