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
 * @date 2019-04-27 16:15:54
 */

@Data
@TableName("part_info")
public class PartInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	@TableId
	private Integer id;
	/**
	 * 配件名称
	 */
	private String partName;

	/**
	 * 总件数
	 */
	@TableField(exist = false )
	private double countNumber;

	/**
	 * 出库数量
	 */
	@TableField(exist = false )
	private double outNumber;

	/**
	 * 剩余数量
	 */
	private double residueNumber;
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
	private Date updateTime;

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
	 * 设置：配件名称
	 */
	public void setPartName(String partName) {
		this.partName = partName;
	}
	/**
	 * 获取：配件名称
	 */
	public String getPartName() {
		return partName;
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
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
