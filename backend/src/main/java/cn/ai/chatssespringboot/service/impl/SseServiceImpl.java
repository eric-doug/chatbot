package cn.ai.chatssespringboot.service.impl;

import cn.ai.chatssespringboot.entity.Message;
import cn.ai.chatssespringboot.service.SseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SseServiceImpl implements SseService
{
    private static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    @Override
    public SseEmitter connect(String uuid)
    {
        SseEmitter sseEmitter = new SseEmitter();
        try {
            sseEmitter.send(SseEmitter.event().comment("welcome"));
        } catch (IOException e) {
            log.error("connect failed", e);
        }

        sseEmitter.onCompletion(() -> {
            sseEmitterMap.remove(uuid);
        });

        sseEmitter.onTimeout(() -> {
            sseEmitterMap.remove(uuid);
        });

        sseEmitter.onError((throwable) -> {
            sseEmitterMap.remove(uuid);
        });

        sseEmitterMap.put(uuid, sseEmitter);
        return sseEmitter;
    }


    @Override
    public void sendMessage(Message message) {
        message.setTotal(sseEmitterMap.size());
        sseEmitterMap.forEach((uuid, sseEmitter) -> {
            try {
                sseEmitter.send(message, MediaType.APPLICATION_JSON);
            }
            catch (IOException e) {
                log.error("send failed", e);
            }
        });
    }
}
