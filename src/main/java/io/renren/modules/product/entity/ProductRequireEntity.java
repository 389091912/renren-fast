package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品需求

 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-30 02:41:40
 */
@ToString
@TableName("product_require")
public class ProductRequireEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 产品id
	 */
	private Integer productId;
	/**
	 * 订单id

	 */
	private Integer orderId;
	/**
	 * 产品实际需求数量
	 */
	private Integer productRequireNumber;
	/**
	 * 纸箱id

	 */
	private Integer boxId;
	/**
	 * 纸箱实际需求数量
	 */
	private Integer boxRequireNumber;
	/**
	 * 模具id
	 */
	private Integer modelId;
	/**
	 * 准备模具数量
	 */
	private Integer modelRequireNumber;
	/**
	 * 产品生产状态
	 */
	private Integer productStatus;
	/**
	 * 订单的状态
	 */
	private Integer orderStatus;
	/**
	 * 创建者

	 */
	private Integer createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新者

	 */
	private Integer updateUser;
	/**
	 * 更新日期

	 */
	private Date updateTime;
	/**
	 * 状态

	 */
	private Integer status;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：产品id
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品id
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：订单id

	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id

	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * 设置：产品实际需求数量
	 */
	public void setProductRequireNumber(Integer productRequireNumber) {
		this.productRequireNumber = productRequireNumber;
	}
	/**
	 * 获取：产品实际需求数量
	 */
	public Integer getProductRequireNumber() {
		return productRequireNumber;
	}
	/**
	 * 设置：纸箱id

	 */
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}
	/**
	 * 获取：纸箱id

	 */
	public Integer getBoxId() {
		return boxId;
	}
	/**
	 * 设置：纸箱实际需求数量
	 */
	public void setBoxRequireNumber(Integer boxRequireNumber) {
		this.boxRequireNumber = boxRequireNumber;
	}
	/**
	 * 获取：纸箱实际需求数量
	 */
	public Integer getBoxRequireNumber() {
		return boxRequireNumber;
	}
	/**
	 * 设置：模具id
	 */
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	/**
	 * 获取：模具id
	 */
	public Integer getModelId() {
		return modelId;
	}
	/**
	 * 设置：准备模具数量
	 */
	public void setModelRequireNumber(Integer modelRequireNumber) {
		this.modelRequireNumber = modelRequireNumber;
	}
	/**
	 * 获取：准备模具数量
	 */
	public Integer getModelRequireNumber() {
		return modelRequireNumber;
	}
	/**
	 * 设置：产品生产状态
	 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * 获取：产品生产状态
	 */
	public Integer getProductStatus() {
		return productStatus;
	}
	/**
	 * 设置：订单的状态
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取：订单的状态
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
	 * 设置：创建者

	 */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建者

	 */
	public Integer getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新者

	 */
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：更新者

	 */
	public Integer getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：更新日期

	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新日期

	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：状态

	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态

	 */
	public Integer getStatus() {
		return status;
	}
}
