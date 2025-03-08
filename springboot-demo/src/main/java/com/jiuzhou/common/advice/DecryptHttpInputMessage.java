package com.jiuzhou.common.advice;

import com.jiuzhou.utils.AESEncryption;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DecryptHttpInputMessage implements HttpInputMessage{

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private HttpHeaders headers;
    private InputStream body;


    public DecryptHttpInputMessage(HttpInputMessage inputMessage, String publicKey, String charset, boolean showLog) throws Exception {

        if (StringUtils.isEmpty(publicKey)) {
            throw new IllegalArgumentException("privateKey is null");
        }

        this.headers = inputMessage.getHeaders();
        String content = new BufferedReader(new InputStreamReader(inputMessage.getBody()))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        String decryptBody=null;
        if (!StringUtils.isEmpty(content)) {
//            content = content.replace("\"", "");
//            decryptBody = SMCipherCaculater.instance.SM4_decrypt(content.getBytes(), publicKey.getBytes()).toString();
             decryptBody = AESEncryption.decrypt(content, publicKey);
        }
        if(showLog) {
            log.info("Encrypted data received：{},After decryption：{}", content, decryptBody);
        }
        this.body = new ByteArrayInputStream(decryptBody.getBytes());
    }

    @Override
    public InputStream getBody(){
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
