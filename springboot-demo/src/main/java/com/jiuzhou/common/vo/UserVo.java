package com.jiuzhou.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 【问题】群组表
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 *
 * @author yushu
 * @email 921784721@qq.com
 * @date 2023/12/30 19:34
 */
@Data
public class UserVo implements Serializable {

    private String account;

    private String password;

}
