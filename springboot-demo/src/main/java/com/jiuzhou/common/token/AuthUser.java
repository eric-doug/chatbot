package com.jiuzhou.common.token;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Chopper
 */
@Data
@AllArgsConstructor
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 582441893336003319L;
    /**
     * id
     */
    private Long id;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 头像
     */
    private String face;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 租户id
     */
    private int tenantId;


    /**
     * 长期有效（用于手机app登录场景或者信任场景等）
     */
    private Boolean longTerm = false;

    public AuthUser() {
    }

    public AuthUser(String mobile, Long id, String userName, String face) {
        this.mobile= mobile;
        this.face = face;
        this.id = id;
        this.userName = userName;

    }
    public AuthUser(String mobile, Long id, String userName, String face, Integer isVip, Integer istTest) {
        this.mobile= mobile;
        this.face = face;
        this.id = id;
        this.userName = userName;

    }
}
