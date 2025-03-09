package cn.ai.chatssespringboot.dto;

import lombok.Data;

@Data
public class AiResultUsage
{
    private Integer promptTokens;
    private Integer completionTokens;
    private Integer totalTokens;
}
