package com.jiuzhou.service;

import com.jiuzhou.common.dto.ChatDto;
import com.jiuzhou.common.vo.ChatVo;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {
    /**
     * 创建SSE
     * @param uid
     * @return
     */
    SseEmitter createSse(String uid);


    /**
     * 客户端发送消息到服务端
     * @param uid
     * @param chatDto
     */
    ChatVo sseChat(String uid, ChatDto chatDto);

    /**
     * 关闭SSE
     * @param uid
     */
    void closeSse(String uid);

}
