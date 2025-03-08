package cn.ai.chatssespringboot.controller;

import cn.ai.chatssespringboot.service.SseService;
import cn.ai.chatssespringboot.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
@RestController("/sse")
public class SseController
{
    @Autowired
    private SseService sseService;

    @GetMapping("/")
    public String index() {
        return "/sse";
    }

    /**
     * 连接
     * @return
     * @throws IOException
     */
    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sse()
            throws IOException
    {
        String uuid = UUID.randomUUID().toString();
        log.info("新用户连接：{}", uuid);
        return sseService.connect(uuid);
    }

    @PostMapping("/sendMessage")
    @ResponseBody
    public void sendMessage(@RequestBody Message message) {
        sseService.sendMessage(message);
    }
}
