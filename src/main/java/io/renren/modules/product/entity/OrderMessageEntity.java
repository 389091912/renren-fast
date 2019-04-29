package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-27 16:30:52
 */
@Data
@TableName("order_message")
public class OrderMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	public static final Integer IS_NOT_READ = 0;


	public static final Integer IS_READ = 1;


	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 订单id
	 */
	private Integer orderDetailId;
	/**
	 * 人员id
	 */
	private Integer userId;
	/**
	 * 消息状态
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
	 *
	 */
	private Date updateTime;
	/**
	 *
	 */
	private Integer updateUser;

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
	 * 设置：订单id
	 */
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	/**
	 * 获取：订单id
	 */
	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	/**
	 * 设置：人员id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取：人员id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置：消息状态
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	/**
	 * 获取：消息状态
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

	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置：
	 */
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 获取：
	 */
	public Integer getUpdateUser() {
		return updateUser;
	}
}
