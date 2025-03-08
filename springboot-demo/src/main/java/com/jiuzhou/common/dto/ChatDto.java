package com.jiuzhou.common.dto;

import lombok.Data;

/**
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 *
 * @author yushu
 * @email 921784721@qq.com
 * @date 2024/1/3 9:34
 */
@Data
public class ChatDto {

    /**
     * 向客户端发送的
     */
    private Long groupId;

    /**
     * 客户端发送的问题参数
     */
    private String question;
}
