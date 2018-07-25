package cn.sqdyy.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * description: security 配置信息，可以将properties或者yml配置的对应参数直接转到本对象中
 * date: 2018/7/21 15:44
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@ConfigurationProperties(prefix = "ops-ucenter")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
}
