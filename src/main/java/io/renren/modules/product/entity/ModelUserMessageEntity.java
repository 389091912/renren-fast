package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-29 16:12:11
 */
@TableName("model_user_message")
public class ModelUserMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final Integer IS_READ = 1;

	public static final Integer IS_NOT_READ = 0;


	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 模具消息id
	 */
	private Integer modelMsgId;
	/**
	 * 用户
	 */
	private Integer userId;
	/**
	 * 是否已读
	 */
	private Integer isRead;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer createUser;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：模具消息id
	 */
	public void setModelMsgId(Integer modelMsgId) {
		this.modelMsgId = modelMsgId;
	}
	/**
	 * 获取：模具消息id
	 */
	public Integer getModelMsgId() {
		return modelMsgId;
	}
	/**
	 * 设置：用户
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：是否已读
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取：是否已读
	 */
	public Integer getIsRead() {
		return isRead;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateUser() {
		return createUser;
	}
}
