package com.jiuzhou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuzhou.entity.QuestionInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 【问题】问题信息表
 * 
 * @author yushu
 * @email 
 * @date 2023-04-27 18:00:53
 */
@Mapper
public interface QuestionInfoDao extends BaseMapper<QuestionInfo> {
    /**
     * 查询当天的token使用值
     * @param userId
     * @return
     */
    Integer countTokens(Long userId);
    /**
     * 查询该用户试用的天数
     */

}
