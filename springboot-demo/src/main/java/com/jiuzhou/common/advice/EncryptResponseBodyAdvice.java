package com.jiuzhou.common.advice;
import com.alibaba.fastjson.JSON;
import com.jiuzhou.common.annotation.Encrypt;
import com.jiuzhou.common.configure.SecretKeyConfig;
import com.jiuzhou.utils.AESEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 * @author yushu
 * @email 921784721@qq.com
 * 对请求的参数进行加密
 **/
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private boolean encrypt;

    @Autowired
    private SecretKeyConfig secretKeyConfig;

    private static ThreadLocal<Boolean> encryptLocal = new ThreadLocal<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        encrypt = false;
        if (returnType.getMethod().isAnnotationPresent(Encrypt.class) && secretKeyConfig.isOpen()) {
            encrypt = true;
        }
        return encrypt;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Boolean status = encryptLocal.get();
        if (null != status && !status) {
            encryptLocal.remove();
            return body;
        }
        if (encrypt) {
            String publicKey = secretKeyConfig.getPublicKey();
            try {
                String content = JSON.toJSONString(body);
                if (!StringUtils.hasText(publicKey)) {
                    throw new NullPointerException("Please configure rsa.encrypt.privatekeyc parameter!");
                }
                //加密过程
                final String result = AESEncryption.encrypt(content, publicKey);
                if(secretKeyConfig.isShowLog()) {
                    log.info("Pre-encrypted data：{}，After encryption：{}", content, result);
                }
                return result;
            } catch (Exception e) {
                log.error("Encrypted data exception", e);
            }
        }
        return body;
    }
}
