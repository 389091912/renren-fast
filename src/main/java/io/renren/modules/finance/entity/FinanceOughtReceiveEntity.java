package io.renren.modules.finance.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 财务—收款信息
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
@TableName("finance_ought_receive")
public class FinanceOughtReceiveEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 应收单号
	 */
	private String vouchCode;
	/**
	 * 收款日期
	 */
	private Date vouchDate;
	/**
	 * 款项类型(0-应收款;1-预收款;2-其他费用)，默认0
	 */
	private Integer vouchType;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 用途，项目名称
	 */
	private String itemName;
	/**
	 * 客户名称
	 */
	private Integer clientId;
	/**
	 * 单据留存
	 */
	private String vouchImageFile;
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
	 * 设置：应收单号
	 */
	public void setVouchCode(String vouchCode) {
		this.vouchCode = vouchCode;
	}
	/**
	 * 获取：应收单号
	 */
	public String getVouchCode() {
		return vouchCode;
	}
	/**
	 * 设置：收款日期
	 */
	public void setVouchDate(Date vouchDate) {
		this.vouchDate = vouchDate;
	}
	/**
	 * 获取：收款日期
	 */
	public Date getVouchDate() {
		return vouchDate;
	}
	/**
	 * 设置：款项类型(0-应收款;1-预收款;2-其他费用)，默认0
	 */
	public void setVouchType(Integer vouchType) {
		this.vouchType = vouchType;
	}
	/**
	 * 获取：款项类型(0-应收款;1-预收款;2-其他费用)，默认0
	 */
	public Integer getVouchType() {
		return vouchType;
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
	 * 设置：用途，项目名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * 获取：用途，项目名称
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * 设置：客户名称
	 */
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	/**
	 * 获取：客户名称
	 */
	public Integer getClientId() {
		return clientId;
	}
	/**
	 * 设置：单据留存
	 */
	public void setVouchImageFile(String vouchImageFile) {
		this.vouchImageFile = vouchImageFile;
	}
	/**
	 * 获取：单据留存
	 */
	public String getVouchImageFile() {
		return vouchImageFile;
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
