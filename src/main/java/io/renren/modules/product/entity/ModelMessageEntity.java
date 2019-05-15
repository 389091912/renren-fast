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
@TableName("model_message")
public class ModelMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 模具出入库id
	 */
	private Integer modelDetailId;
	/**
	 * 操作者
	 */
	private Integer userId;
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
	 * 设置：模具出入库id
	 */
	public void setModelDetailId(Integer modelDetailId) {
		this.modelDetailId = modelDetailId;
	}
	/**
	 * 获取：模具出入库id
	 */
	public Integer getModelDetailId() {
		return modelDetailId;
	}
	/**
	 * 设置：操作者
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：操作者
	 */
	public Integer getUserId() {
		return userId;
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
