package cn.sqdyy.core.validate.code;

import cn.sqdyy.core.properties.SecurityProperties;
import cn.sqdyy.core.validate.code.image.ImageCodeGenerator;
import org.patchca.service.ConfigurableCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: 验证码bean注入
 * date: 2018/7/21 19:33
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(ImageCodeGenerator.class)
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
}
