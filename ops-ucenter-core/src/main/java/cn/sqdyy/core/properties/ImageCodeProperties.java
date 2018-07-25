package cn.sqdyy.core.properties;

import lombok.Data;

/**
 * description: 图片验证码配置文件
 * date: 2018/7/21 18:59
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Data
public class ImageCodeProperties  {
    /** 图片宽度 */
    private int width = 67;
    /** 图片高度 */
    private int height = 23;
    /** 字符最大长度 */
    private int maxLength = 6;
    /** 字符最小长度 */
    private int minLength = 4;
    /** 过期时间 */
    private int expireIn = 60;
    /** 验证码rgb */
    private int r = 16;
    /** 验证码rgb */
    private int g = 142;
    /** 验证码rgb */
    private int b = 233;
    /** 需要使用图片验证码的URL，如果有多个URL使用,分隔 */
    private String url;


}
