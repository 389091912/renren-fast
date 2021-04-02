package io.renren.modules.finance.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 财务-个人借款
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 20:45:18
 */
@Data
@TableName("finance_borrower_info")
public class FinanceBorrowerInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 借款人ID
	 */
	private Long userId;
	/**
	 * 借款时间
	 */
	@Excel(name = "日期",format = "yyyy.MM.dd")
	private Date borrowerDate;

	@TableField(exist = false)
	private String borrowerDateStr;

	/**
	 * 借款金额
	 */
	@Excel(name = "借款金额")
	private BigDecimal borrowerMoney;
	/**
	 * 用途信息
	 */
	@Excel(name = "用途")
	private String purposeInfo;
	/**
	 * 备注
	 */
	@Excel(name = "备注")
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建者
	 */
	private Long createUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新者
	 */
	private Long updateUser;

	/**
	 * 借款人姓名
	 */
	@TableField(exist = false)
	@Excel(name = "借款人")
	private String username;


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
	 * 设置：借款人ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：借款人ID
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：借款时间
	 */
	public void setBorrowerDate(Date borrowerDate) {
		this.borrowerDate = borrowerDate;
	}
	/**
	 * 获取：借款时间
	 */
	public Date getBorrowerDate() {
		return borrowerDate;
	}
	/**
	 * 设置：借款金额
	 */
	public void setBorrowerMoney(BigDecimal borrowerMoney) {
		this.borrowerMoney = borrowerMoney;
	}
	/**
	 * 获取：借款金额
	 */
	public BigDecimal getBorrowerMoney() {
		return borrowerMoney;
	}
	/**
	 * 设置：用途信息
	 */
	public void setPurposeInfo(String purposeInfo) {
		this.purposeInfo = purposeInfo;
	}
	/**
	 * 获取：用途信息
	 */
	public String getPurposeInfo() {
		return purposeInfo;
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
	 * 设置：创建者
	 */
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建者
	 */
	public Long getCreateUser() {
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
	 * 设置：更新者
	 */
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：更新者
	 */
	public Long getUpdateUser() {
		return updateUser;
	}
}
