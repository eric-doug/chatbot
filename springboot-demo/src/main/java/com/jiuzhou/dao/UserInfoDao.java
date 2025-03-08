package com.jiuzhou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuzhou.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 【用户】信息表
 * 
 * @author yushu
 * @email 921784721@qq.com
 * @date 2023-04-22 15:02:12
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {
	
}
