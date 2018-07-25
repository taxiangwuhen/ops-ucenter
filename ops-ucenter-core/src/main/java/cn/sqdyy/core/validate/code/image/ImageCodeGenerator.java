package cn.sqdyy.core.validate.code.image;

import cn.sqdyy.core.properties.ImageCodeProperties;
import cn.sqdyy.core.properties.SecurityProperties;
import cn.sqdyy.core.validate.code.ValidateCode;
import cn.sqdyy.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.ConfigurableFilterFactory;
import org.patchca.filter.library.AbstractImageOp;
import org.patchca.filter.library.WobbleImageOp;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.text.renderer.TextRenderer;
import org.patchca.word.RandomWordFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

/**
 * description: 图片验证码生成器 使用开源验证码项目patchca生成
 * date: 2018/7/21 18:56
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator,InitializingBean {

    @Autowired
    private SecurityProperties securityProperties;

    private ConfigurableCaptchaService configurableCaptchaService;
    private ColorFactory colorFactory;
    private RandomFontFactory fontFactory;
    private RandomWordFactory wordFactory ;
    private TextRenderer textRenderer;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        // 得到验证码对象,有验证码图片和验证码字符串
        Captcha captcha = configurableCaptchaService.getCaptcha();
        return new ImageCode(captcha.getImage(), captcha.getChallenge(), securityProperties.getCode().getImage().getExpireIn());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ImageCodeProperties image = securityProperties.getCode().getImage();
        configurableCaptchaService = new ConfigurableCaptchaService();
        // 颜色创建工厂,使用RGB
        configurableCaptchaService.setColorFactory(new SingleColorFactory(new Color(image.getR(), image.getG(), image.getB())));

        // 随机字体生成器
        fontFactory = new RandomFontFactory();
        fontFactory.setMaxSize(32);
        fontFactory.setMinSize(28);
        configurableCaptchaService.setFontFactory(fontFactory);

        // 随机字符生成器,去除掉容易混淆的字母和数字,如o和0等
        wordFactory = new RandomWordFactory();
        wordFactory.setCharacters("ABCDEFGHIJKLMNPQRSTUVWXYZ123456789");
        wordFactory.setMaxLength(image.getMinLength());
        wordFactory.setMinLength(image.getMinLength());
        configurableCaptchaService.setWordFactory(wordFactory);

        // TODO 自定义验证码图片背景 configurableCaptchaService.setBackgroundFactory()

        // 图片滤镜设置
        ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
        List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
        WobbleImageOp wobbleImageOp = new WobbleImageOp();
        wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);
        wobbleImageOp.setxAmplitude(2.0);
        wobbleImageOp.setyAmplitude(1.0);
        filters.add(wobbleImageOp);
        filterFactory.setFilters(filters);

        configurableCaptchaService.setFilterFactory(filterFactory);

        // 文字渲染器设置
        textRenderer = new BestFitTextRenderer();
        textRenderer.setBottomMargin(3);
        textRenderer.setTopMargin(3);
        configurableCaptchaService.setTextRenderer(textRenderer);

        // 验证码图片的大小
        configurableCaptchaService.setWidth(image.getWidth());
        configurableCaptchaService.setHeight(image.getHeight());
    }
}
