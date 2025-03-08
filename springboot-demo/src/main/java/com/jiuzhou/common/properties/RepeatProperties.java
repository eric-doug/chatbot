package com.jiuzhou.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Data
@Configuration
@ConfigurationProperties(prefix = "jiuzhou.repeat")
public class RepeatProperties {

    private boolean swicth;

    private String type;

    private List<String> whiteIp;

    private Long cacheTime;

    private String requestId =  "requestId";
}
