package cn.sqdyy.core.properties;

import cn.sqdyy.core.constants.SecurityConstants;
import cn.sqdyy.core.enums.ResponseType;
import lombok.Data;

/**
 * description: 浏览器端配置信息
 * date: 2018/7/21 15:47
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Data
public class BrowserProperties {

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    private ResponseType loginType = ResponseType.JSON;
}
