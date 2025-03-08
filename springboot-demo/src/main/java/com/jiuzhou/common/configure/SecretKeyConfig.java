package com.jiuzhou.common.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author:Bobby
 * DateTime:2019/4/9
 **/
@Data
@ConfigurationProperties(prefix = "aes.encrypt")
@Configuration
public class SecretKeyConfig {

    private String privateKey;

    private String publicKey;

    private String charset = "UTF-8";

    private boolean open = false;

    private boolean showLog = false;

    private Integer time;

}
