package com.jiuzhou.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 * @author yushu
 * @email 921784721@qq.com
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "system.auth")
public class JwtProperties {

    /**
     * token默认过期时间
     */
    private long tokenExpireTime = 2;
    /**
     * 密钥
     */
    private String jwtKey;
}
