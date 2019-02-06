package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.renren.modules.product.entity.vo.ProductDetailVo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@Data
@TableName("product_order")
public class ProductOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 订单时间
	 */
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date orderTime;
	/**
	 * 员工id
	 */
	private Integer employeeId;
	/**
	 * 客户
	 */
	private String customer;
	/**
	 * 交货时间
	 */
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deliveryTime;

	/**
	 * 商品列表Str
	 */
	@TableField(exist = false)
	private String productList;

	@TableField(exist = false)
	private List<ProductOrderDetailEntity> productOrderDetailList;

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
	private Integer updateId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 订单状态 0为正常订单正常生产，1为取消订单，2为订单加急，3为订单挂起
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remark;

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
	 * 设置：订单编号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取：订单编号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置：订单时间
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * 获取：订单时间
	 */
	public Date getOrderTime() {
		return orderTime;
	}

	/**
	 * 设置：员工id
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * 获取：员工id
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}

	/**
	 * 设置：客户
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * 获取：客户
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * 设置：交货时间
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * 获取：交货时间
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
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
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	/**
	 * 获取：更新者
	 */
	public Integer getUpdateId() {
		return updateId;
	}

	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置：订单状态 0为正常订单正常生产，1为取消订单，2为订单加急，3为订单挂起
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：订单状态 0为正常订单正常生产，1为取消订单，2为订单加急，3为订单挂起
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
