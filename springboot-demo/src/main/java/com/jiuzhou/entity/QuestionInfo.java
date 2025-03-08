package com.jiuzhou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 【问题】问题信息表
 * 
 * @author yushu
 * @email 
 * @date 2023-04-27 18:00:53
 */
@Data
@ApiModel(value="【问题】问题信息表对象",description="【问题】问题信息表")
@TableName("t_question_info")
public class QuestionInfo extends Model<QuestionInfo> {
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value="",name="id")
    private Long id;
	@ApiModelProperty(value="用户id",name="userId")
    private Long userId;
	@ApiModelProperty(value="消耗token值",name="token")
    private Integer token;
	@ApiModelProperty(value="问题组id",name="groupId")
    private Long groupId;
	@ApiModelProperty(value="问题",name="question")
    private String question;
	@ApiModelProperty(value="答案",name="answer")
    private String answer;
	@ApiModelProperty(value="创建时间",name="createTime")
    private Date createTime;
	@ApiModelProperty(value="更新时间",name="updateTime")
    private Date updateTime;
	@TableLogic(delval = "1",value="0")
	@ApiModelProperty(value="是否删除 0否 1是",name="isDel")
	private Integer isDel;
}
