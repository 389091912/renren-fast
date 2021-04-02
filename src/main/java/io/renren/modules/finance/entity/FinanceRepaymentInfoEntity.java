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
 * 财务-个人还款
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2021-01-29 20:45:18
 */
@Data
@TableName("finance_repayment_info")
public class FinanceRepaymentInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 还款金额
	 */
	@Excel(name = "还款金额")
	private BigDecimal repaymentMoney;
	/**
	 * 用户ID 
	 */
	private Long userId;
	/**
	 * 还款日期
	 */
	@Excel(name = "日期",format = "yyyy.MM.dd")
	private Date repaymentDate;
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
	 * 还款姓名
	 */
	@Excel(name = "借款人")
	@TableField(exist = false)
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
	 * 设置：还款金额
	 */
	public void setRepaymentMoney(BigDecimal repaymentMoney) {
		this.repaymentMoney = repaymentMoney;
	}
	/**
	 * 获取：还款金额
	 */
	public BigDecimal getRepaymentMoney() {
		return repaymentMoney;
	}
	/**
	 * 设置：用户ID 
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID 
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：还款日期
	 */
	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	/**
	 * 获取：还款日期
	 */
	public Date getRepaymentDate() {
		return repaymentDate;
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
