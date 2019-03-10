package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.modules.product.entity.vo.BoxFactoryVo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@Data
@TableName("product_box")
public class ProductBoxEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 纸箱编号
	 */
	private String boxNo;
	/**
	 * 箱体
	 */
	private String body;
	@TableField(exist = false)
	private Integer bodyNumber;
	/**
	 * 格挡
	 */
	private String parry;
	@TableField(exist = false)
	private Integer parryNumber;
	/**
	 * 垫片
	 */
	private String spacer;
	@TableField(exist = false)
	private Integer spacerNumber;
	/**
	 * 数量
	 */
	private Integer boxNumber;

	/**
	 * 纸箱价格
	 */
	private BigDecimal boxPrice;

	/**
	 * 每箱只数
	 */
	private Integer zhiShu;

	private String boxAddFactoryId;

	private Integer productId;

	@TableField(exist = false)
	private List<BoxFactoryVo> boxFactoryVoList;

	@TableField(exist = false)
	private String boxFactoryVoStr;
	/**
	 * 客户
	 */
	private String costomer;
	/**
	 * 位置
	 */
	private String location;
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
	 * 0为开启，1为禁止
	 */
	private Integer status;
	/**
	 *  出库数量
	 */
	private Integer leaveNumber;

	/**
	 * 纸箱入库批次
	 */
	private String boxAddBatch;

	/**
	 * 纸箱入库价格
	 */
	private String boxAddPrice;

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
	 * 设置：箱体
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * 获取：箱体
	 */
	public String getBody() {
		return body;
	}
	/**
	 * 设置：格挡
	 */
	public void setParry(String parry) {
		this.parry = parry;
	}
	/**
	 * 获取：格挡
	 */
	public String getParry() {
		return parry;
	}
	/**
	 * 设置：垫片
	 */
	public void setSpacer(String spacer) {
		this.spacer = spacer;
	}
	/**
	 * 获取：垫片
	 */
	public String getSpacer() {
		return spacer;
	}
	/**
	 * 设置：数量
	 */
	public void setBoxNumber(Integer boxNumber) {
		this.boxNumber = boxNumber;
	}
	/**
	 * 获取：数量
	 */
	public Integer getBoxNumber() {
		return boxNumber;
	}
	/**
	 * 设置：客户
	 */
	public void setCostomer(String costomer) {
		this.costomer = costomer;
	}
	/**
	 * 获取：客户
	 */
	public String getCostomer() {
		return costomer;
	}
	/**
	 * 设置：位置
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * 获取：位置
	 */
	public String getLocation() {
		return location;
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
	/**
	 * 设置：0为开启，1为禁止
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0为开启，1为禁止
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置： 出库数量
	 */
	public void setLeaveNumber(Integer leaveNumber) {
		this.leaveNumber = leaveNumber;
	}
	/**
	 * 获取： 出库数量
	 */
	public Integer getLeaveNumber() {
		return leaveNumber;
	}
}
