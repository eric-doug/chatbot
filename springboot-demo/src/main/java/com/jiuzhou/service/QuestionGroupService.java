package com.jiuzhou.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuzhou.entity.QuestionGroup;

/**
 * 【问题】群组表
 *
 * @author yushu
 * @email 
 * @date 2023-04-27 18:00:53
 */
public interface QuestionGroupService extends IService<QuestionGroup> {

    IPage page(Page<QuestionGroup> page, Wrapper<QuestionGroup> wrapper);


}

