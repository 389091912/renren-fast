package io.renren.modules.finance.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 财务类别
 * 
 * @author wsy
 * @email wsy@gmail.com
 * @date 2020-12-13 03:01:58
 */
@TableName("finance_category")
public class FinanceCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 财务类别名称
	 */
	private String name;
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
	private Date createTime;
	/**
	 * 更新者
	 */
	private Integer updateUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;

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
	 * 设置：财务类别名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：财务类别名称
	 */
	public String getName() {
		return name;
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
}
