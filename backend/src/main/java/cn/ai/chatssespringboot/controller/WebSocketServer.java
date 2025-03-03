package cn.ai.chatssespringboot.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
@ServerEndpoint(value = "/ws/asset")
public class WebSocketServer {
    // 用来统计连接客户端的数量
    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        sessionSet.add(session);
        int cnt = onlineCount.incrementAndGet();
        log.info("有链接加入，当前 连接数为: {}", cnt);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("来自客户端的消息: {}", message);
        sendMessage(session, "Echo消息内容: " + message);

    }

    @OnClose
    public void onClose(Session session) {
        sessionSet.remove(session);
        int cnt = onlineCount.decrementAndGet();
        log.info("有链接关闭，当前 连接数为: {}", cnt);
    }

    @OnError
    public void onError(Session session, Throwable error) {
       log.error("发生错误: {}, Session ID: {}", error.getMessage(), session.getId());
    }

    private static void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(String.format("%s (From Server, Session ID=%s)", message, session.getId()));
    }
}
