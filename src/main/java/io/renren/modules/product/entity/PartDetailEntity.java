package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-04-27 16:30:52
 */
@Data
@TableName("part_detail")
public class PartDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 配件名称
	 */
	private Integer partId;

	@TableField(exist = false)
	private String partName;
	/**
	 * 配件数量
	 */
	private Integer partNumber;
	/**
	 * 配件的方式
	 */
	private Integer type;
	/**
	 * 入库单留存
	 */
	private String imageUrl;
	/**
	 * 采购人员
	 */
	private String purchaseName;
	/**
	 * 采购日期
	 */
	private Date purchaseTime;
	/**
	 * 使用人员
	 */
	private String userName;
	/**
	 * 使用日期
	 */
	private Date userTime;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer createUser;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Integer updateUser;

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
	 * 设置：配件名称
	 */
	public void setPartId(Integer partId) {
		this.partId = partId;
	}
	/**
	 * 获取：配件名称
	 */
	public Integer getPartId() {
		return partId;
	}
	/**
	 * 设置：配件数量
	 */
	public void setPartNumber(Integer partNumber) {
		this.partNumber = partNumber;
	}
	/**
	 * 获取：配件数量
	 */
	public Integer getPartNumber() {
		return partNumber;
	}
	/**
	 * 设置：配件的方式
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：配件的方式
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：入库单留存
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 获取：入库单留存
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * 设置：采购人员
	 */
	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}
	/**
	 * 获取：采购人员
	 */
	public String getPurchaseName() {
		return purchaseName;
	}
	/**
	 * 设置：采购日期
	 */
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	/**
	 * 获取：采购日期
	 */
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	/**
	 * 设置：使用人员
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：使用人员
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：使用日期
	 */
	public void setUserTime(Date userTime) {
		this.userTime = userTime;
	}
	/**
	 * 获取：使用日期
	 */
	public Date getUserTime() {
		return userTime;
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
}
