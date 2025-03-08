package com.jiuzhou.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuzhou.entity.QuestionInfo;

/**
 * 【问题】问题信息表
 *
 * @author yushu
 * @email
 * @date 2023-04-27 18:00:53
 */
public interface QuestionInfoService extends IService<QuestionInfo> {

    IPage page(Page<QuestionInfo> page, Wrapper<QuestionInfo> wrapper);

}

