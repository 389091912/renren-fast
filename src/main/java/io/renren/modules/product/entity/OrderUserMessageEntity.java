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
@TableName("order_user_message")
public class OrderUserMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final Integer IS_READ = 1;

	public static final Integer IS_NOT_READ = 0;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 订单消息id
	 */
	private Integer orderMsgId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 判断是否已读
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
	 * 设置：订单消息id
	 */
	public void setOrderMsgId(Integer orderMsgId) {
		this.orderMsgId = orderMsgId;
	}
	/**
	 * 获取：订单消息id
	 */
	public Integer getOrderMsgId() {
		return orderMsgId;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：判断是否已读
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取：判断是否已读
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
