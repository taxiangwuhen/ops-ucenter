package cn.sqdyy.core;

import cn.sqdyy.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * description:
 * 问：注解@EnableConfigurationProperties(SecurityProperties.class)的作用？
 * 答：为SecurityProperties提供Bean注入的支持
 * date: 2018/7/21 15:56
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}