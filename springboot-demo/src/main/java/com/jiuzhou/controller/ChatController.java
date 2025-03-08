package com.jiuzhou.controller;

import com.jiuzhou.common.dto.ChatDto;
import com.jiuzhou.common.vo.ChatVo;
import com.jiuzhou.service.SseService;
import com.unfbx.chatgpt.exception.BaseException;
import com.unfbx.chatgpt.exception.CommonError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 *
 * @author yushu
 * @email 921784721@qq.com
 * @date 2024/1/3 9:31
 */
@RestController
@Slf4j
public class ChatController {

    private final SseService sseService;

    public ChatController(SseService sseService) {
        this.sseService = sseService;
    }

    @GetMapping("/{text}")
    public String authGet(@PathVariable("text")String text) {

        log.info("来自微信服务器的请求:"+text);
        if(text.equals("MP_verify_1n9GCqDOpyzmzHb9.txt")){
            return "1n9GCqDOpyzmzHb9";
        }else{
            return "";
        }
    }

    /**
     * 创建sse连接
     *
     * @param headers
     * @return
     */
    @CrossOrigin
    @GetMapping("/createSse")
    public SseEmitter createConnect(@RequestHeader Map<String, String> headers) {
        String uid = getUid(headers);
        return sseService.createSse(uid);
    }

    /**
     * 聊天接口
     *
     * @param chatRequest
     * @param headers
     */
    @CrossOrigin
    @PostMapping("/chat")
    @ResponseBody
    public ChatVo sseChat(@RequestBody ChatDto chatRequest, @RequestHeader Map<String, String> headers, HttpServletResponse response) {
        String uid = getUid(headers);
        return sseService.sseChat(uid, chatRequest);
    }
    /**
     * 关闭连接
     *
     * @param headers
     */
    @CrossOrigin
    @GetMapping("/closeSse")
    public void closeConnect(@RequestHeader Map<String, String> headers) {

        try{
            String uid = getUid(headers);
            sseService.closeSse(uid);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取uid
     *
     * @param headers
     * @return
     */
    private String getUid(Map<String, String> headers) {
        String uid = headers.get("uid");
        if (null==uid) {
            throw new BaseException(CommonError.SYS_ERROR);
        }
        return uid;
    }


}
