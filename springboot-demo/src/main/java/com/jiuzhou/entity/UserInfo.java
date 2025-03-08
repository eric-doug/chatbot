package com.jiuzhou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 【用户】信息表
 * 
 * @author yushu
 * @email 
 * @date 2023-04-27 18:00:53
 */
@Data
@ApiModel(value="【用户】信息表对象",description="【用户】信息表")
@TableName("t_user_info")
public class UserInfo extends Model<UserInfo> {
	private static final long serialVersionUID = 1L;

	@TableId(type= IdType.INPUT)
	@ApiModelProperty(value="用户id  取帐号表id",name="userId")
    private Long userId;
	@ApiModelProperty(value="用户帐号",name="userAccount")
    private String userAccount;
	@ApiModelProperty(value="昵称",name="userName")
    private String userName;
	@ApiModelProperty(value="用户头像",name="avatar")
    private String avatar;
	@ApiModelProperty(value="联系手机",name="linkPhone")
    private String linkPhone;
	@ApiModelProperty(value="邮箱",name="email")
    private String email;
	@ApiModelProperty(value="性别",name="sex")
	private Integer sex;
	@ApiModelProperty(value="真实姓名",name="realName")
	private String realName;
	@ApiModelProperty(value="状态",name="status")
	private Integer status;
	@ApiModelProperty(value="创建时间",name="createDate")
    private Date createDate;
	@ApiModelProperty(value="更新时间",name="updateTime")
	private Date updateTime;

}
