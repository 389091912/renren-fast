package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@Data
@TableName("product_order_detail")
public class ProductOrderDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 等待生产
	 */
	public static final int WAITER_PRODUCT = 0;

	/**
	 * 生产中
	 */
	public static final int PROCESS_PRODUCT = 1;

	/**
	 * 生产完成
	 */
	public static final int SUCCESS_PRODUCT = 2;

	/**
	 * 取消生产
	 */
	public static final int CANCEL_PRODUCT = 3;
	/**
	 * 纸箱供应方式 自供
	 */
	public static final int BOX_SUPPLY_SELF = 1;
	/**
	 * 纸箱供应方式 客供
	 */
	public static final int BOX_SUPPLY_CUSTOMER = 0;



	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 订单id
	 */
	private Integer orderId;

	/**
	 * 订单编号
	 */

	@TableField(exist = false)
	private String orderNo;
	/**
	 * 产品ID
	 */
	private Integer productId;

	/**
	 * 产品名称
	 */
	@TableField(exist = false)
	private String productName;

	/**
	 * 产品克数
	 */
	private String productWeight;

	/**
	 * 纸箱供货方式
	 */
	private Integer boxSupplyWay;

	/**
	 * 订单数量
	 */
	private Double productNumber;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人员id
	 */
	private Integer createUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新人员id
	 */
	private Integer updateUser;
	/**
	 * 0为等待生产，1为取消生产，2为生产中，3为生产完成。
	 */
	private Integer status;


	/**
	 * 生产计划
	 *
	 */
	private Integer planId;

	/**
	 * 纸箱厂
	 */
	private Integer boxFactoryId;

	@TableField(exist = false)
	private String factoryName;


	/**
	 * 需求纸箱数量
	 */
	private Integer needBoxNumber;

	/**
	 * 纸箱入库数量
	 */
	@TableField(exist = false)
	private Integer entryBoxNumber;

	/**
	 * 订单状态
	 */
	@TableField(exist = false)
	private Integer orderStatus;
	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
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
	 * 设置：产品ID
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品ID
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：订单数量
	 */
	public void setProductNumber(Double productNumber) {
		this.productNumber = productNumber;
	}
	/**
	 * 获取：订单数量
	 */
	public Double getProductNumber() {
		return productNumber;
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
	 * 设置：创建人员id
	 */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人员id
	 */
	public Integer getCreateUser() {
		return createUser;
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
	 * 设置：更新人员id
	 */
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：更新人员id
	 */
	public Integer getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：0为等待生产，1为取消生产，2为生产中，3为生产完成。
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0为等待生产，1为取消生产，2为生产中，3为生产完成。
	 */
	public Integer getStatus() {
		return status;
	}
}
