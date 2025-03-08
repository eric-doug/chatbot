package com.jiuzhou.service;

/**
 * 【问题】群组表
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 *
 * @author yushu
 * @email 921784721@qq.com
 * @date 2023/12/29 13:47
 */
public interface RepeatService {

    /**
     * 重复返回true 不重复返回false
     * @param requestId
     * @return
     */
    boolean process(String requestId);

}
