package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-25 23:35:04
 */
@Data
@TableName("product_plan_notice")
public class ProductPlanNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 设备编号
	 */
	private Integer deviceId;

	@TableField(exist = false)
	private String deviceName;
	/**
	 * 产品编号
	 */
	private Integer productId;

	@TableField(exist = false)
	private String productName;
	/**
	 * 模具编号
	 */
	private Integer modelId;

	@TableField(exist = false)
	private String modelNo;

	/**
	 * 客户编号
	 */
	private String customerProductNo;
	/**
	 * 料重
	 */
	private Integer materialWeight;
	/**
	 * 容量
	 */
	private Integer volume;
	/**
	 * 订单id
	 */
	private Integer orderId;

	/**
	 * 订单编号
	 */
	@TableField(exist = false)
	private String orderNo;
	/**
	 * 订单数量
	 */
	private Double orderNumber;
	/**
	 * 库存数量
	 */
	private Integer repertoryNumber;
	/**
	 * 实际需求数量
	 */
	private Integer needNumber;
	/**
	 * 客户样品 有 无
	 */
	private String customerProductSytle;
	/**
	 * 瓶盖套装 有 无
	 */
	private String bottleCapSuit;
	/**
	 * 后续加工
	 */
	private String followUpProcess;
	/**
	 * 包装要求
	 */
	private String packRequire;
	/**
	 * 纸箱编号
	 */
	private Integer boxId;
	/**
	 * 客户要求
	 */
	private String customerRequire;
	/**
	 * 瓶身总高度
	 */
	private String bottleHight;
	/**
	 * 瓶口内径
	 */
	private String bottleInDiameter;
	/**
	 * 头径高度
	 */
	private String headNeckHeight;
	/**
	 * 瓶口外径
	 */
	private String bottleOutDiameter;
	/**
	 * 外观
	 */
	private String facadeRequire;
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
	 * 0为启用,1为禁止
	 */
	private Integer status;

	private Integer groupNumber;

	private Integer isPriority;
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
	 * 设置：设备编号
	 */
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备编号
	 */
	public Integer getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：产品编号
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品编号
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：模具编号
	 */
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	/**
	 * 获取：模具编号
	 */
	public Integer getModelId() {
		return modelId;
	}
	/**
	 * 设置：客户编号
	 */
	public void setCustomerProductNo(String customerProductNo) {
		this.customerProductNo = customerProductNo;
	}
	/**
	 * 获取：客户编号
	 */
	public String getCustomerProductNo() {
		return customerProductNo;
	}
	/**
	 * 设置：料重
	 */
	public void setMaterialWeight(Integer materialWeight) {
		this.materialWeight = materialWeight;
	}
	/**
	 * 获取：料重
	 */
	public Integer getMaterialWeight() {
		return materialWeight;
	}
	/**
	 * 设置：容量
	 */
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	/**
	 * 获取：容量
	 */
	public Integer getVolume() {
		return volume;
	}
	/**
	 * 设置：订单id
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * 设置：订单数量
	 */
	public void setOrderNumber(Double orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：订单数量
	 */
	public Double getOrderNumber() {
		return orderNumber;
	}
	/**
	 * 设置：库存数量
	 */
	public void setRepertoryNumber(Integer repertoryNumber) {
		this.repertoryNumber = repertoryNumber;
	}
	/**
	 * 获取：库存数量
	 */
	public Integer getRepertoryNumber() {
		return repertoryNumber;
	}
	/**
	 * 设置：实际需求数量
	 */
	public void setNeedNumber(Integer needNumber) {
		this.needNumber = needNumber;
	}
	/**
	 * 获取：实际需求数量
	 */
	public Integer getNeedNumber() {
		return needNumber;
	}
	/**
	 * 设置：客户样品 有 无
	 */
	public void setCustomerProductSytle(String customerProductSytle) {
		this.customerProductSytle = customerProductSytle;
	}
	/**
	 * 获取：客户样品 有 无
	 */
	public String getCustomerProductSytle() {
		return customerProductSytle;
	}
	/**
	 * 设置：瓶盖套装 有 无
	 */
	public void setBottleCapSuit(String bottleCapSuit) {
		this.bottleCapSuit = bottleCapSuit;
	}
	/**
	 * 获取：瓶盖套装 有 无
	 */
	public String getBottleCapSuit() {
		return bottleCapSuit;
	}
	/**
	 * 设置：后续加工
	 */
	public void setFollowUpProcess(String followUpProcess) {
		this.followUpProcess = followUpProcess;
	}
	/**
	 * 获取：后续加工
	 */
	public String getFollowUpProcess() {
		return followUpProcess;
	}
	/**
	 * 设置：包装要求
	 */
	public void setPackRequire(String packRequire) {
		this.packRequire = packRequire;
	}
	/**
	 * 获取：包装要求
	 */
	public String getPackRequire() {
		return packRequire;
	}
	/**
	 * 设置：纸箱编号
	 */
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}
	/**
	 * 获取：纸箱编号
	 */
	public Integer getBoxId() {
		return boxId;
	}
	/**
	 * 设置：客户要求
	 */
	public void setCustomerRequire(String customerRequire) {
		this.customerRequire = customerRequire;
	}
	/**
	 * 获取：客户要求
	 */
	public String getCustomerRequire() {
		return customerRequire;
	}
	/**
	 * 设置：瓶身总高度
	 */
	public void setBottleHight(String bottleHight) {
		this.bottleHight = bottleHight;
	}
	/**
	 * 获取：瓶身总高度
	 */
	public String getBottleHight() {
		return bottleHight;
	}
	/**
	 * 设置：瓶口内径
	 */
	public void setBottleInDiameter(String bottleInDiameter) {
		this.bottleInDiameter = bottleInDiameter;
	}
	/**
	 * 获取：瓶口内径
	 */
	public String getBottleInDiameter() {
		return bottleInDiameter;
	}
	/**
	 * 设置：头径高度
	 */
	public void setHeadNeckHeight(String headNeckHeight) {
		this.headNeckHeight = headNeckHeight;
	}
	/**
	 * 获取：头径高度
	 */
	public String getHeadNeckHeight() {
		return headNeckHeight;
	}
	/**
	 * 设置：瓶口外径
	 */
	public void setBottleOutDiameter(String bottleOutDiameter) {
		this.bottleOutDiameter = bottleOutDiameter;
	}
	/**
	 * 获取：瓶口外径
	 */
	public String getBottleOutDiameter() {
		return bottleOutDiameter;
	}
	/**
	 * 设置：外观
	 */
	public void setFacadeRequire(String facadeRequire) {
		this.facadeRequire = facadeRequire;
	}
	/**
	 * 获取：外观
	 */
	public String getFacadeRequire() {
		return facadeRequire;
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
	 * 设置：0为启用,1为禁止
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0为启用,1为禁止
	 */
	public Integer getStatus() {
		return status;
	}
}
