package com.jiuzhou.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.jiuzhou.common.dto.ChatDto;
import com.jiuzhou.common.listener.OpenAISSEEventSourceListener;
import com.jiuzhou.common.vo.ChatVo;
import com.jiuzhou.service.SseService;
import com.jiuzhou.utils.LocalCache;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 */
@Service
@Slf4j
public class SseServiceImpl implements SseService {

    private final OpenAiStreamClient openAiStreamClient;

    public SseServiceImpl(OpenAiStreamClient openAiStreamClient) {
        this.openAiStreamClient = openAiStreamClient;
    }

    @Override
    public SseEmitter createSse(String uid) {
        //默认30秒超时,设置为0L则永不超时
        SseEmitter sseEmitter = new SseEmitter(0l);
        //完成后回调
        sseEmitter.onCompletion(() -> {
            log.info("[{}]结束连接...................", uid);
            LocalCache.CACHE.remove(uid);
        });
        //超时回调
        sseEmitter.onTimeout(() -> {
            log.info("[{}]连接超时...................", uid);
        });
        //异常回调
        sseEmitter.onError(
                throwable -> {
                    try {
                        log.info("[{}]连接异常,{}", uid, throwable.toString());
                        sseEmitter.send(SseEmitter.event()
                                .id(uid)
                                .name("发生异常！")
                                .data(Message.builder().content("发生异常请重试！").build())
                                .reconnectTime(3000));
                        LocalCache.CACHE.put(uid, sseEmitter);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        LocalCache.CACHE.put(uid, sseEmitter);
        log.info("[{}]创建sse连接成功！", uid);
        return sseEmitter;
    }

    @Override
    public void closeSse(String uid) {
        SseEmitter sse = (SseEmitter) LocalCache.CACHE.get(uid);
        if (sse != null) {
            sse.complete();
            //移除
            LocalCache.CACHE.remove(uid);
        }
    }

    @Override
    public ChatVo sseChat(String uid, ChatDto chatRequest) {
        if (StrUtil.isBlank(chatRequest.getQuestion())) {
            log.info("参数异常，msg为null", uid);
            throw new BaseException("参数异常，msg不能为空~");
        }
        String messageContext = (String) LocalCache.CACHE.get("msg" + uid);
        List<Message> messages = new ArrayList<>();
        if (StrUtil.isNotBlank(messageContext)) {
            messages = JSONUtil.toList(messageContext, Message.class);
            if (messages.size() >= 5) {
                messages = messages.subList(1, 5);
            }
            Message currentMessage = Message.builder().content(chatRequest.getQuestion()).role(Message.Role.USER).build();
            messages.add(currentMessage);
        } else {
            Message currentMessage = Message.builder().content(chatRequest.getQuestion()).role(Message.Role.USER).build();
            messages.add(currentMessage);
        }

        SseEmitter sseEmitter = (SseEmitter) LocalCache.CACHE.get(uid);

        if (sseEmitter == null) {
            log.info("聊天消息推送失败uid:[{}],没有创建连接，请重试。", uid);
            throw new BaseException("聊天消息推送失败uid:[{}],没有创建连接，请重试。~");
        }
        OpenAISSEEventSourceListener openAIEventSourceListener = new OpenAISSEEventSourceListener(sseEmitter);
        ChatCompletion completion = ChatCompletion
                .builder()
                .messages(messages)
                .model(ChatCompletion.Model.GPT_3_5_TURBO_0613.getName())
                .build();
        openAiStreamClient.streamChatCompletion(completion, openAIEventSourceListener);
        LocalCache.CACHE.put("msg" + uid, JSONUtil.toJsonStr(messages), LocalCache.TIMEOUT);
        ChatVo response = new ChatVo();
        response.setQuestionTokens(completion.tokens());
        return response;
    }
}
