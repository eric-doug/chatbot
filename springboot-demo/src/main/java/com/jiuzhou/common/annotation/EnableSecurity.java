package com.jiuzhou.common.annotation;

import com.jiuzhou.common.advice.DecryptRequestBodyAdvice;
import com.jiuzhou.common.advice.EncryptResponseBodyAdvice;
import com.jiuzhou.common.configure.SecretKeyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 * @author yushu
 * @email 921784721@qq.com
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({SecretKeyConfig.class,
        EncryptResponseBodyAdvice.class,
        DecryptRequestBodyAdvice.class})
public @interface EnableSecurity {

}
