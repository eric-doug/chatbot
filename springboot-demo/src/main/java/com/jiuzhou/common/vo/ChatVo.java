package com.jiuzhou.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 描述：
 *
 * @author https:www.unfbx.com
 * @sine 2023-04-08
 */
@Data
public class ChatVo {
    /**
     * 问题消耗tokens
     */
    @JsonProperty("question_tokens")
    private long questionTokens = 0;

}
