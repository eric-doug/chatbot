package com.jiuzhou.common.token;


import com.jiuzhou.common.Enum.UserEnums;

/**
 * 缓存前缀
 *
 * @author pikachu
 * @version 4.1
 * @since 1.0
 * 2018/3/19
 */
public enum CachePrefix {



    ACCESS_TOKEN,
    /**
     * token 信息
     */
    REFRESH_TOKEN,

    QQ_STATE;


    public static String removePrefix(String str) {
        return str.substring(str.lastIndexOf("}_") + 2);
    }

    /**
     * 通用获取缓存key值
     *
     * @return 缓存key值
     */
    public String getPrefix() {
        return "{" + this.name() + "}_";
    }

    /**

     * 获取缓存key值 + 用户端
     * 例如：三端都有用户体系，需要分别登录，如果用户名一致，则redis中的权限可能会冲突出错
     *
     * @param user 角色
     * @return 缓存key值 + 用户端
     */
    public String getPrefix(UserEnums user) {
        return this.name() + ":" + user.name() + ":";
    }
}
