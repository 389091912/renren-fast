package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("product_model_out")
public class ProductModelOutEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 模具ID
	 */
	@TableId
	private Integer id;
	/**
	 * 模具编号
	 */
	private Integer modelNo;
	/**
	 * 仓库号
	 */
	@TableField(exist = false)
	private Integer depotId;
	/**
	 * 客户编号
	 */
	@TableField(exist = false)
	private String customerModelNo;

	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 成模
	 */
	private Integer modelSuccessMo;
	/**
	 * 初模
	 */
	private Integer modelPrimaryMo;
	/**
	 * 口模
	 */
	private Integer modelMouthMo;
	/**
	 * 闷头
	 */
	private Integer modelMenTou;
	/**
	 * 漏斗
	 */
	private Integer modelFunnel;
	/**
	 * 芯子
	 */
	private Integer modelCore;
	/**
	 * 气头
	 */
	private Integer modelAirTou;
	/**
	 * 冷却
	 */
	private Integer modelCooling;
	/**
	 * 钳片
	 */
	private Integer modelClamp;
	/**
	 * 出库数量
	 */
	@TableField(exist = false)
	private Integer modelAllNumber;
	/**
	 * 模具经手人
	 */
	private String modelHandlingPeople;
	/**
	 * 提货人名称
	 */
	private String customerName;
	/**
	 * 备注
	 */
	private String modelRemark;
	/**
	 * 发货日期
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date modelDeliveryTime;
	/**
	 * 收货日期
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date modelReceiptTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人员
	 */
	private Integer createUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 更新人员
	 */
	private Integer updateUser;
	/**
	 * 0为启用，1为禁止
	 */
	private Integer status;

	/**
	 * 0为入库，1为出库，2为新品打样，3为返厂维修记录，4为外来加工
	 */
	private Integer modelType;
	/**
	 * 瓶重
	 */
	private String bottleWeight;
	/**
	 * 退货原因
	 */
	private String reasonReturn;

	/**
	 * 设置：模具ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：模具ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：模具编号
	 */
	public void setModelNo(Integer modelNo) {
		this.modelNo = modelNo;
	}
	/**
	 * 获取：模具编号
	 */
	public Integer getModelNo() {
		return modelNo;
	}
	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：成模
	 */
	public void setModelSuccessMo(Integer modelSuccessMo) {
		this.modelSuccessMo = modelSuccessMo;
	}
	/**
	 * 获取：成模
	 */
	public Integer getModelSuccessMo() {
		return modelSuccessMo;
	}
	/**
	 * 设置：初模
	 */
	public void setModelPrimaryMo(Integer modelPrimaryMo) {
		this.modelPrimaryMo = modelPrimaryMo;
	}
	/**
	 * 获取：初模
	 */
	public Integer getModelPrimaryMo() {
		return modelPrimaryMo;
	}
	/**
	 * 设置：口模
	 */
	public void setModelMouthMo(Integer modelMouthMo) {
		this.modelMouthMo = modelMouthMo;
	}
	/**
	 * 获取：口模
	 */
	public Integer getModelMouthMo() {
		return modelMouthMo;
	}
	/**
	 * 设置：闷头
	 */
	public void setModelMenTou(Integer modelMenTou) {
		this.modelMenTou = modelMenTou;
	}
	/**
	 * 获取：闷头
	 */
	public Integer getModelMenTou() {
		return modelMenTou;
	}
	/**
	 * 设置：漏斗
	 */
	public void setModelFunnel(Integer modelFunnel) {
		this.modelFunnel = modelFunnel;
	}
	/**
	 * 获取：漏斗
	 */
	public Integer getModelFunnel() {
		return modelFunnel;
	}
	/**
	 * 设置：芯子
	 */
	public void setModelCore(Integer modelCore) {
		this.modelCore = modelCore;
	}
	/**
	 * 获取：芯子
	 */
	public Integer getModelCore() {
		return modelCore;
	}
	/**
	 * 设置：气头
	 */
	public void setModelAirTou(Integer modelAirTou) {
		this.modelAirTou = modelAirTou;
	}
	/**
	 * 获取：气头
	 */
	public Integer getModelAirTou() {
		return modelAirTou;
	}
	/**
	 * 设置：冷却
	 */
	public void setModelCooling(Integer modelCooling) {
		this.modelCooling = modelCooling;
	}
	/**
	 * 获取：冷却
	 */
	public Integer getModelCooling() {
		return modelCooling;
	}
	/**
	 * 设置：钳片
	 */
	public void setModelClamp(Integer modelClamp) {
		this.modelClamp = modelClamp;
	}
	/**
	 * 获取：钳片
	 */
	public Integer getModelClamp() {
		return modelClamp;
	}
	/**
	 * 设置：出库数量
	 */
	public void setModelAllNumber(Integer modelAllNumber) {
		this.modelAllNumber = modelAllNumber;
	}
	/**
	 * 获取：出库数量
	 */

	public Integer getModelAllNumber() {
		return modelAllNumber;
	}
	/**
	 * 设置：模具经手人
	 */
	public void setModelHandlingPeople(String modelHandlingPeople) {
		this.modelHandlingPeople = modelHandlingPeople;
	}
	/**
	 * 获取：模具经手人
	 */
	public String getModelHandlingPeople() {
		return modelHandlingPeople;
	}
	/**
	 * 设置：提货人名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：提货人名称
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：备注
	 */
	public void setModelRemark(String modelRemark) {
		this.modelRemark = modelRemark;
	}
	/**
	 * 获取：备注
	 */
	public String getModelRemark() {
		return modelRemark;
	}
	/**
	 * 设置：发货日期
	 */
	public void setModelDeliveryTime(Date modelDeliveryTime) {
		this.modelDeliveryTime = modelDeliveryTime;
	}
	/**
	 * 获取：发货日期
	 */
	public Date getModelDeliveryTime() {
		return modelDeliveryTime;
	}
	/**
	 * 设置：收货日期
	 */
	public void setModelReceiptTime(Date modelReceiptTime) {
		this.modelReceiptTime = modelReceiptTime;
	}
	/**
	 * 获取：收货日期
	 */
	public Date getModelReceiptTime() {
		return modelReceiptTime;
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
	 * 设置：创建人员
	 */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人员
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
	 * 设置：更新人员
	 */
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * 获取：更新人员
	 */
	public Integer getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置：0为启用，1为禁止
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0为启用，1为禁止
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置：0为入库，1为出库，2为新品打样，3为返厂维修记录，4为外来加工
	 */
	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}
	/**
	 * 获取：0为入库，1为出库，2为新品打样，3为返厂维修记录，4为外来加工
	 */
	public Integer getModelType() {
		return modelType;
	}
	/**
	 * 设置：瓶重
	 */
	public void setBottleWeight(String bottleWeight) {
		this.bottleWeight = bottleWeight;
	}
	/**
	 * 获取：瓶重
	 */
	public String getBottleWeight() {
		return bottleWeight;
	}
	/**
	 * 设置：退货原因
	 */
	public void setReasonReturn(String reasonReturn) {
		this.reasonReturn = reasonReturn;
	}
	/**
	 * 获取：退货原因
	 */
	public String getReasonReturn() {
		return reasonReturn;
	}
}
