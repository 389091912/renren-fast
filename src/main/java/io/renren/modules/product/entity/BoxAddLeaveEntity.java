package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-30 02:41:40
 */
@Data
@TableName("box_add_leave")
public class BoxAddLeaveEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 入库
	 */
	public static final String ADD = "1";

	/**
	 * 出库
	 */
	public static final String LEAVE = "0";

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 纸箱编号
	 */
	private String boxNo;

	@TableField(exist = false)
	private String boxNoName;

	/**
	 * 箱体数量
	 */
	private Integer bodyNumber;
	/**
	 * 箱体数量总数
	 */
	@TableField(exist = false)
	private Integer bodyNumberCount;
	/**
	 * 格挡数量
	 */
	private Integer parryNumber;
	/**
	 * 格挡数量总数
	 */
	@TableField(exist = false)
	private Integer parryNumberCount;
	/**
	 * 垫片数量
	 */
	private Integer spacerNumber;

	/**
	 * 垫片数量总数
	 */
	@TableField(exist = false)
	private Integer spacerNumberCount;
	/**
	 * 成品入库数量
	 */
	private Integer addBoxNumber;
	/**
	 * 成品入库数量总数
	 */
	@TableField(exist = false)
	private Integer addBoxNumberCount;

	/**
	 * 成品出库数量
	 */
	private Integer outBoxNumber;

	/**
	 * 成品出库数量总数
	 */
	@TableField(exist = false)
	private Integer outBoxNumberCount;

	private Integer type;

	private Date addBoxTime;

	private Date outBoxTime;

	private BigDecimal boxPrice;

	private Integer factoryId;

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
	 * 状态

	 */
	private Integer status;

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
	 * 设置：纸箱编号
	 */
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	/**
	 * 获取：纸箱编号
	 */
	public String getBoxNo() {
		return boxNo;
	}
	/**
	 * 设置：箱体数量
	 */
	public void setBodyNumber(Integer bodyNumber) {
		this.bodyNumber = bodyNumber;
	}
	/**
	 * 获取：箱体数量
	 */
	public Integer getBodyNumber() {
		return bodyNumber;
	}
	/**
	 * 设置：格挡数量
	 */
	public void setParryNumber(Integer parryNumber) {
		this.parryNumber = parryNumber;
	}
	/**
	 * 获取：格挡数量
	 */
	public Integer getParryNumber() {
		return parryNumber;
	}
	/**
	 * 设置：垫片数量
	 */
	public void setSpacerNumber(Integer spacerNumber) {
		this.spacerNumber = spacerNumber;
	}
	/**
	 * 获取：垫片数量
	 */
	public Integer getSpacerNumber() {
		return spacerNumber;
	}
	/**
	 * 设置：成品入库数量
	 */
	public void setAddBoxNumber(Integer addBoxNumber) {
		this.addBoxNumber = addBoxNumber;
	}
	/**
	 * 获取：成品入库数量
	 */
	public Integer getAddBoxNumber() {
		return addBoxNumber;
	}
	/**
	 * 设置：成品出库数量
	 */
	public void setOutBoxNumber(Integer outBoxNumber) {
		this.outBoxNumber = outBoxNumber;
	}
	/**
	 * 获取：成品出库数量
	 */
	public Integer getOutBoxNumber() {
		return outBoxNumber;
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
