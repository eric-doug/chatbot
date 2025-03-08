package cn.ai.chatssespringboot.webflux.router;

import cn.ai.chatssespringboot.webflux.handler.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 消息的路由处理类
 */
//@Configuration
public class MessageRouter
{

//    /**
//     * 路由功能注册
//     * @param messageHandler
//     * @return
//     */
//    @Bean
//    public RouterFunction<ServerResponse> routeEcho(MessageHandler messageHandler) {
//        return RouterFunctions.route(RequestPredicates.GET("/echo")
//                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN))
//                , messageHandler::echoHandler);
//
//    }
}
