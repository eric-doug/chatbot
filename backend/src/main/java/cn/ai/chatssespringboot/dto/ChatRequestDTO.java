package cn.ai.chatssespringboot.dto;

import lombok.Data;

import java.util.List;


@Data
public class ChatRequestDTO {
    private String model;

    private List<ReqMessage> messages;

    private Boolean stream;

    @Data
    public static class ReqMessage{
        private String role;
        private String content;
    }

}
