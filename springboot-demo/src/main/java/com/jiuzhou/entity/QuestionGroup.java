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
 * 【问题】群组表
 * 
 * @author yushu
 * @email 
 * @date 2023-04-27 18:00:53
 */
@Data
@ApiModel(value="【问题】群组表对象",description="【问题】群组表")
@TableName("t_question_group")
public class QuestionGroup extends Model<QuestionGroup> {
	private static final long serialVersionUID = 1L;

	@TableId(type= IdType.AUTO)
	@ApiModelProperty(value="问题组id",name="id")
    private Long id;
	@ApiModelProperty(value="归属用户id",name="userId")
    private Long userId;
	@ApiModelProperty(value="问题组标题",name="groupName",required = true)
    private String groupName;
	@TableLogic(value="0",delval = "1")
	@ApiModelProperty(value="是否删除 0否 1是",name="isDel")
    private Integer isDel;
	@ApiModelProperty(value="创建时间",name="createTime")
    private Date createTime;
	@ApiModelProperty(value="修改时间",name="updateTime")
    private Date updateTime;

}
