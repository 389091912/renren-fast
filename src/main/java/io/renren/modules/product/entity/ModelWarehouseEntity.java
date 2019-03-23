package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-03-02 11:00:17
 */
@TableName("model_warehouse")
public class ModelWarehouseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId
	private Integer id;
	/**
	 * 仓库号
	 */
	@TableField(exist = false)
	private Integer warehouseNo;
	/**
	 * 仓库名称
	 */
	private String warehouseName;

	/**
	 * 设置：序号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：序号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：仓库号
	 */
	public void setWarehouseNo(Integer warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	/**
	 * 获取：仓库号
	 */
	public Integer getWarehouseNo() {
		return warehouseNo;
	}
	/**
	 * 设置：仓库名称
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	/**
	 * 获取：仓库名称
	 */
	public String getWarehouseName() {
		return warehouseName;
	}
}
