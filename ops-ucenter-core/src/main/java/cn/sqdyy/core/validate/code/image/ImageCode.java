package cn.sqdyy.core.validate.code.image;

import cn.sqdyy.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * description: 图形验证码
 * date: 2018/7/21 17:48
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }
    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }
}
