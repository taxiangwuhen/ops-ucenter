package cn.sqdyy.core.validate.code;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * description: 标准验证码
 * date: 2018/7/21 17:45
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Data
public class ValidateCode {
    /** 验证码 */
    private String code;
    /** 过期时间 */
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    public ValidateCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
