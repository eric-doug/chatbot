package com.jiuzhou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 【用户】账户表
 * 
 * @author yushu
 * @email 
 * @date 2023-04-23 14:28:10
 */
@Data
@ApiModel(value="【用户】账户表对象",description="【用户】账户表")
@TableName("t_user_account")
public class UserAccount extends Model<UserAccount> {
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value="主键id",name="userId")
    private Long userId;
	@ApiModelProperty(value="用户帐号",name="userAccount")
    private String userAccount;
	@ApiModelProperty(value="登录邮箱",name="email")
    private String email;
	@ApiModelProperty(value="登录密码md5（sha()）",name="loginPwd")
    private String loginPwd;
	@ApiModelProperty(value="交易密码",name="tradePwd")
    private String tradePwd;
	@ApiModelProperty(value="密码盐",name="pwdSalt")
    private String pwdSalt;
	@ApiModelProperty(value="是否禁止登录 0=不可用 1=可用 默认1",name="isEnable")
    private Integer isEnable;

}
