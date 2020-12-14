package io.renren.modules.finance.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 财务付款信息
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
@TableName("finance_pay")
public class FinancePayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 申请付款人
	 */
	private String personName;
	/**
	 * 付款单编号
	 */
	private String payCode;
	/**
	 * 付款日期
	 */
	private Date payDate;
	/**
	 * 打款信息
	 */
	private String payInfo;
	/**
	 * 收款人名称
	 */
	private String customerName;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 类别
	 */
	private Integer category;
	/**
	 * 数量
	 */
	private Integer number;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 创建者
	 */
	private Integer createUser;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 更新者
	 */
	private Integer updateUser;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 付款单留存
	 */
	private String payImageFile;

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
	 * 设置：申请付款人
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * 获取：申请付款人
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * 设置：付款单编号
	 */
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	/**
	 * 获取：付款单编号
	 */
	public String getPayCode() {
		return payCode;
	}
	/**
	 * 设置：付款日期
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	/**
	 * 获取：付款日期
	 */
	public Date getPayDate() {
		return payDate;
	}
	/**
	 * 设置：打款信息
	 */
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}
	/**
	 * 获取：打款信息
	 */
	public String getPayInfo() {
		return payInfo;
	}
	/**
	 * 设置：收款人名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：收款人名称
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：类别
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}
	/**
	 * 获取：类别
	 */
	public Integer getCategory() {
		return category;
	}
	/**
	 * 设置：数量
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：数量
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * 设置：单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
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
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
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
	 * 设置：更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：付款单留存
	 */
	public void setPayImageFile(String payImageFile) {
		this.payImageFile = payImageFile;
	}
	/**
	 * 获取：付款单留存
	 */
	public String getPayImageFile() {
		return payImageFile;
	}
}
