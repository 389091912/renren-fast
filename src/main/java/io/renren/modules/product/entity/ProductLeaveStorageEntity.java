package io.renren.modules.product.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import javax.naming.Name;
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
@TableName("product_leave_storage")
public class ProductLeaveStorageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 产品id
	 */
	private Integer productId;




	/**
	 * 纸箱id
	 */
	private Integer boxId;



	/**
	 * 订单编号
	 */
	@TableField(exist = false)
	private String orderNo;

	@TableField(exist = false)
	private String boxNo;
	/**
	 * 订单编号
	 */
	private Integer orderId;
	/**
	 * 出库时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@Excel(name="日期",width = 23,databaseFormat = "yyyy-MM-dd",format = "yyyy.MM.dd")
	private Date outTime;
	/**
	 * 签收时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date signTime;

	private Integer tray;
	@Excel(name="票号",width = 22)
	private String ticketNo;
	@Excel(name="客户")
	private String customer;
	/**
	 *  产品名称
	 */
	@Excel(name="型号",width = 22)
	private String productName;
	/**
	 * 箱子数
	 */
	@Excel(name="件数")
	private Double boxNumber;

	@Excel(name="只数")
	private Double zhiNumber;
	/**
	 * 出库数量
	 */
	@Excel(name="数量")
	private Double productOutNumber;

	/**
	 * 产品重量
	 */
	@Excel(name="克数")
	private Integer productWeight;


	/**
	 * 
	 */
	private Integer createUser;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer updateUser;

	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 0为正常，1为禁用
	 */
	private Integer status;

	/**
	 * 产品总克数
	 */
	@Excel(name="总克数",width = 25)
	private Long weightCount;

	private String orderImage;
	@Excel(name="销售员")
	private String salesman;
	/**
	 * 备注
	 */
	@Excel(name="备注")
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
	 * 设置：出库数量
	 */

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
	 * 设置：箱子数
	 */

	/**
	 * 设置：订单编号
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单编号
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * 设置：出库时间
	 */
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	/**
	 * 获取：出库时间
	 */
	public Date getOutTime() {
		return outTime;
	}
	/**
	 * 设置：签收时间
	 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	/**
	 * 获取：签收时间
	 */
	public Date getSignTime() {
		return signTime;
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
	 * 设置：0为正常，1为禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0为正常，1为禁用
	 */
	public Integer getStatus() {
		return status;
	}
}
