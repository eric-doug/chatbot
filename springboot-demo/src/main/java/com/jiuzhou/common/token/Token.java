package com.jiuzhou.common.token;

import lombok.Data;

import java.io.Serializable;

/**
 * Token 实体类
 *
 * @author Chopper
 * @version v1.0
 * 2020-11-13 10:02
 */
@Data
public class Token implements Serializable {


    private static final long serialVersionUID = -5019510245371173278L;
    /**
     * 访问token
     */
    private String accessToken;

    /**
     * 刷新token
     */
    private String refreshToken;

}
