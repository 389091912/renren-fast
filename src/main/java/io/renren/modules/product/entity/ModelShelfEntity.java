package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-03-02 11:00:17
 */
@Data
@TableName("model_shelf")
public class ModelShelfEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 为空
	 */
	public static final String IS_EMPTY = "0";

	/**
	 * 不为空
	 */
	public static final String IS_NOT_EMPTY = "1";
	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 仓库号
	 */
	private Integer warehouseId;
	/**
	 * 架号
	 */
	private String shelfNo;
	/**
	 * 是否有货
	 */
	private String isEmpty;
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
	private Integer updateUser;
	/**
	 * 
	 */
	private Date updataTime;

	private Integer modelId;

	@TableField(exist = false )
	private String modelName;

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
	 * 设置：仓库号
	 */
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	/**
	 * 获取：仓库号
	 */
	public Integer getWarehouseId() {
		return warehouseId;
	}
	/**
	 * 设置：架号
	 */
	public void setShelfNo(String shelfNo) {
		this.shelfNo = shelfNo;
	}
	/**
	 * 获取：架号
	 */
	public String getShelfNo() {
		return shelfNo;
	}
	/**
	 * 设置：是否有货
	 */
	public void setIsEmpty(String isEmpty) {
		this.isEmpty = isEmpty;
	}
	/**
	 * 获取：是否有货
	 */
	public String getIsEmpty() {
		return isEmpty;
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
	 * 设置：
	 */
	public void setUpdataTime(Date updataTime) {
		this.updataTime = updataTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdataTime() {
		return updataTime;
	}
}
