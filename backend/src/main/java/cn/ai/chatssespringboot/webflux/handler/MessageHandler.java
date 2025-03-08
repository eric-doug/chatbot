package cn.ai.chatssespringboot.webflux.handler;

import cn.ai.chatssespringboot.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是一个WebFlux处理模块
 * 在WebFlux编程里面如果要进行响应的话会区分单个响应(单一对象)以及多个响应(集合)
 *
 */

@Component
@Slf4j
public class MessageHandler
{

    /**
     * 返回单一数据类
     * @param message
     * @return
     */
    public Mono<Message> echoHandler(Message message) {
        log.info("【{}】业务层接受处理数据: {}", Thread.currentThread().getName(), message);
        message.setTitle("【"+Thread.currentThread().getName() + "】" + message.getTitle());
        message.setContent("【" + Thread.currentThread().getName() + "】" + message.getContent());
        return Mono.create(monoSink -> monoSink.success(message));
    }

    /**
     * 返回集合数据
     * @param message
     * @return
     */
    public Flux<Message> list(Message message) {
        List<Message> messageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message();
            msg.setTitle("【" + i + "】" + message.getTitle());
            msg.setContent("【" + i + "】" + message.getContent());
            msg.setPublicDate(message.getPublicDate());
        }

        return Flux.fromIterable(messageList);
    }


    public Flux<Map.Entry<String, Message>> map(Message message) {
        Map<String, Message> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message();
            msg.setTitle("【" + i + "】" + message.getTitle());
            msg.setContent("【" + i + "】" + message.getContent());
            msg.setPublicDate(message.getPublicDate());
            map.put("x-" + i, msg);
        }

        return Flux.fromIterable(map.entrySet());
    }
}
