package cn.ai.chatssespringboot.controller;


import cn.ai.chatssespringboot.dto.AiResult;
import cn.ai.chatssespringboot.dto.ContentDto;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
public class DeepSeekController
{

    private static final String Done = "[DONE]";
    private static final Integer TIMEOUT = 120;
    private static final String DS_OLLAMA_URL = "http://localhost:11434/api/generate";
    private static final String MODEL_DEEPSEEK = "deepseek-r1:1.5b";


    @GetMapping("/stream")
    public void handleSse(String model, String message, HttpServletResponse response) {
        log.info("model:" + model + "\n" + "message:" + message);
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter pw = response.getWriter()) {
           getDsResult(pw, message);
           pw.write("data:end\n\n");
           pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getContent(String data) {

        return "";
    }

    private void getDsResult(PrintWriter pw, String message)
            throws InterruptedException
    {
        Map<String, Object> params = new HashMap<>();
        params.put("prompt", message);
        params.put("model", MODEL_DEEPSEEK);
        params.put("stream", true);

        String jsonParams = JSON.toJSONString(params);

        Request.Builder builder = new Request.Builder().url(DS_OLLAMA_URL);
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonParams);
        Request request = builder.post(body).build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        CountDownLatch eventLatch = new CountDownLatch(1);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                log.error("request failed.", e);
            }

            @Override
            public void onResponse(Call call, Response response)
                    throws IOException
            {
                if (response.isSuccessful()) {
                    try (ResponseBody responseBody = response.body()) {
                        if (responseBody != null) {
                            BufferedReader reader= new BufferedReader(new InputStreamReader(responseBody.byteStream(), StandardCharsets.UTF_8));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                log.info("model output:{}", line);
                                AiResult aiResult = JSON.parseObject(line, AiResult.class);

                                log.info(aiResult.getMessage());
                                pw.write("data:" + JSON.toJSONString(new ContentDto(aiResult.getMessage())) + "\n\n");
                                pw.flush();
                            }
                            eventLatch.countDown();
                        }
                    }
                }
            }
        });
        eventLatch.await();

    }
}
