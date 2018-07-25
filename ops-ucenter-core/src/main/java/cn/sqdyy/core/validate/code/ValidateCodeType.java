package cn.sqdyy.core.validate.code;

import cn.sqdyy.core.constants.SecurityConstants;

/**
 * description: 验证码类型枚举
 * date: 2018/7/21 18:13
 * @author <a href="http://sqdyy.cn">神奇的鸭鸭·韦耿林</a>
 */
public enum ValidateCodeType {

    /** 图片验证码 */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_IMAGE_CODE;
        }
    };

    /** 校验时从请求中获取的参数名字 */
    public abstract String getParamNameOnValidate();
}
