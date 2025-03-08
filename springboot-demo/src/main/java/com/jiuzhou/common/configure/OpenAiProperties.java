package com.jiuzhou.common.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Data
@Configuration
@ConfigurationProperties("chatgpt")
public class OpenAiProperties {

    private List<String> apiKey;
    private String apiHost;
    private boolean isGpt4;
    private String modelName = "gpt-3.5-turbo-0301";

}
