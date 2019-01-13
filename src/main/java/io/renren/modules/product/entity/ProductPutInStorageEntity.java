package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-14 00:02:12
 */
@TableName("product_put_in_storage")
public class ProductPutInStorageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 产品ID
	 */
	private Integer productId;
	/**
	 * 只数
	 */
	private Integer zhiNumber;
	/**
	 * 箱数
	 */
	private Integer boxNumber;
	/**
	 * 入库数量
	 */
	private Integer inNumber;
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
	 * 设置：只数
	 */
	public void setZhiNumber(Integer zhiNumber) {
		this.zhiNumber = zhiNumber;
	}
	/**
	 * 获取：只数
	 */
	public Integer getZhiNumber() {
		return zhiNumber;
	}
	/**
	 * 设置：箱数
	 */
	public void setBoxNumber(Integer boxNumber) {
		this.boxNumber = boxNumber;
	}
	/**
	 * 获取：箱数
	 */
	public Integer getBoxNumber() {
		return boxNumber;
	}
	/**
	 * 设置：入库数量
	 */
	public void setInNumber(Integer inNumber) {
		this.inNumber = inNumber;
	}
	/**
	 * 获取：入库数量
	 */
	public Integer getInNumber() {
		return inNumber;
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
}
