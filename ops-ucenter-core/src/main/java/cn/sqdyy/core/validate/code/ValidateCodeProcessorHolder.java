package cn.sqdyy.core.validate.code;

import cn.sqdyy.core.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * description: 该类持有当前系统的所有验证码处理器
 * date: 2018/7/21 17:54
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + SecurityConstants.VALIDATE_CODE_PROCESSOR_CLASSNAME_SUFFIX;
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if(processor == null) {
            throw new ValidateCodeException("验证码处理器" + name+ "不存在");
        }
        return processor;
    }
}
