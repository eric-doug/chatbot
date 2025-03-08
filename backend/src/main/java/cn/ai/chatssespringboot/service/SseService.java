package cn.ai.chatssespringboot.service;

import cn.ai.chatssespringboot.entity.Message;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService
{
    SseEmitter connect(String uuid);

    void sendMessage(Message message);
}
