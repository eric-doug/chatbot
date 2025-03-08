package cn.ai.chatssespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChatSseSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatSseSpringbootApplication.class, args);
    }

}
