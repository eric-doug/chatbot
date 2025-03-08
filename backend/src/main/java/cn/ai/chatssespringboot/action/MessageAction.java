package cn.ai.chatssespringboot.action;

import cn.ai.chatssespringboot.vo.Message;
import cn.ai.chatssespringboot.webflux.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/message")
@Slf4j
public class MessageAction
{
    @Autowired
    private MessageHandler messageHandler;

    @RequestMapping("/echo")
    public Object echo(Message message) {
        log.info("接收用户范围信息，用户发送的参数为: message = {}", message);

        return this.messageHandler.echoHandler(message);
    }


    @RequestMapping("/list")
    public Object list(Message message) {
        log.info("接收用户范围信息，用户发送的参数为: message = {}", message);

        return this.messageHandler.list(message);
    }

    @RequestMapping("/map")
    public Object map(Message message) {
        log.info("接收用户范围信息，用户发送的参数为: message = {}", message);

        return this.messageHandler.map(message);
    }

}
