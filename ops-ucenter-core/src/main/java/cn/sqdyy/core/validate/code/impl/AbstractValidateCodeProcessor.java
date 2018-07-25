package cn.sqdyy.core.validate.code.impl;

import cn.sqdyy.core.constants.SecurityConstants;
import cn.sqdyy.core.validate.code.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * description: <TODO>
 * date: 2018/7/21 17:59
 *
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    /** 操作session的工具类 */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /** 收集系统中所有的volidateCodeGenerator接口实现 */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    /** 创建校验码，同时包含生成验证码，保存验证码和发送验证码的逻辑，验证码的保存逻辑是一致的，验证码的发送逻辑是不一致的，需要验证码Processor自己拓展 */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /** 校验验证码 */
    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType processorType = getValidateCodeType();
        String sessionKey = getSessionKey();

        C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), processorType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("无法从请求中获取到验证码");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException(processorType + "验证码不存在");
        }
        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new ValidateCodeException(processorType + "验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(processorType + "验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, sessionKey);
    }

    /** 生成验证码 */
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType().toString().toUpperCase();
        String generatorName = type + SecurityConstants.VALIDATE_CODE_GENERATOR_CLASSNAME_SUFFIX;
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if(validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /** 根据当前验证码处理器获取验证码类型 */
    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(this.getClass().getSimpleName(), SecurityConstants.VALIDATE_CODE_PROCESSOR_CLASSNAME_SUFFIX);
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, getSessionKey(), validateCode);
    }

    /** 根据验证码类型得到不同的session key */
    private String getSessionKey() {
        return SESSION_KEY_PREFIX + getValidateCodeType();
    }

    /** 发送校验码，由子类实现 */
    protected abstract void send(ServletWebRequest request, C validateCode) throws IOException, Exception;
}
