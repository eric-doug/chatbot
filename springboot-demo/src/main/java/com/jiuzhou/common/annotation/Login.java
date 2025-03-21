/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.softworld.vip
 *
 * 版权所有，侵权必究！
 */

package com.jiuzhou.common.annotation;

import java.lang.annotation.*;

/**
 * 登录效验
 * @author Mark sunlightcs@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
