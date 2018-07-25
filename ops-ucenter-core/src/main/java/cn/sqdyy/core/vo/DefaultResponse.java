package cn.sqdyy.core.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * description: 统一的JSON响应结构
 * 使用REST框架实现前后端分离架构,其中应该包含两部分：
 * 元数据与返回值，其中，元数据表示操作是否成功与返回值消息，返回值对于服务端方法所返回的数据。
 * date: 2018/7/21 15:39
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Data
public class DefaultResponse<T> {
    /** 元数据 enum(响应码:响应描述) */
    private HttpStatus responseCode;
    /** 错误详细描述 */
    private String msg;
    /** 响应内容 */
    private T data;

    public DefaultResponse(HttpStatus responseCode, String msg) {
        this.responseCode = responseCode;
        this.msg = msg;
        this.data = null;
    }

    public DefaultResponse(HttpStatus responseCode, String msg, T data) {
        this.responseCode = responseCode;
        this.msg = msg;
        this.data = data;
    }
}
