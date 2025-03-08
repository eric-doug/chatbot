package com.jiuzhou.common.Enum;

/**
 * token角色类型
 *
 * @author Chopper
 * @version v1.0
 * @since 2020/8/18 15:23
 */
public enum UserEnums {
    /**
     * 角色
     */
    MEMBER("用户");//或会员

    private final String role;

    UserEnums(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
