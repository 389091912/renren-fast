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
@TableName("product_model")
public class ProductModelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 模具ID
	 */
	@TableId
	private Integer modelId;
	/**
	 * 架号
	 */
	private String siteNo;
	/**
	 * 模具编号
	 */
	private String modelNo;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 成模
	 */
	private String modelSuccessMo;
	/**
	 * 初模
	 */
	private String modelPrimaryMo;
	/**
	 * 口模
	 */
	private String modelMouthMo;
	/**
	 * 闷头
	 */
	private String modelMenTou;
	/**
	 * 漏斗
	 */
	private String modelFunnel;
	/**
	 * 芯子
	 */
	private String modelCore;
	/**
	 * 气头
	 */
	private String modelAirTou;
	/**
	 * 冷却
	 */
	private String modelCooling;
	/**
	 * 钳片
	 */
	private String modelClamp;
	/**
	 * 容量
	 */
	private String modelVolume;
	/**
	 * 经手人
	 */
	private String modelHandlingPeople;
	/**
	 * 发货日期
	 */
	private Date modelDeliveryTime;
	/**
	 * 收货日期
	 */
	private Date modelReceiptTime;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 是否在库
	 */
	private Integer state;
	/**
	 * 备注
	 */
	private String modelRemark;

	/**
	 * 设置：模具ID
	 */
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	/**
	 * 获取：模具ID
	 */
	public Integer getModelId() {
		return modelId;
	}
	/**
	 * 设置：架号
	 */
	public void setSiteNo(String siteNo) {
		this.siteNo = siteNo;
	}
	/**
	 * 获取：架号
	 */
	public String getSiteNo() {
		return siteNo;
	}
	/**
	 * 设置：模具编号
	 */
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	/**
	 * 获取：模具编号
	 */
	public String getModelNo() {
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
	public void setModelSuccessMo(String modelSuccessMo) {
		this.modelSuccessMo = modelSuccessMo;
	}
	/**
	 * 获取：成模
	 */
	public String getModelSuccessMo() {
		return modelSuccessMo;
	}
	/**
	 * 设置：初模
	 */
	public void setModelPrimaryMo(String modelPrimaryMo) {
		this.modelPrimaryMo = modelPrimaryMo;
	}
	/**
	 * 获取：初模
	 */
	public String getModelPrimaryMo() {
		return modelPrimaryMo;
	}
	/**
	 * 设置：口模
	 */
	public void setModelMouthMo(String modelMouthMo) {
		this.modelMouthMo = modelMouthMo;
	}
	/**
	 * 获取：口模
	 */
	public String getModelMouthMo() {
		return modelMouthMo;
	}
	/**
	 * 设置：闷头
	 */
	public void setModelMenTou(String modelMenTou) {
		this.modelMenTou = modelMenTou;
	}
	/**
	 * 获取：闷头
	 */
	public String getModelMenTou() {
		return modelMenTou;
	}
	/**
	 * 设置：漏斗
	 */
	public void setModelFunnel(String modelFunnel) {
		this.modelFunnel = modelFunnel;
	}
	/**
	 * 获取：漏斗
	 */
	public String getModelFunnel() {
		return modelFunnel;
	}
	/**
	 * 设置：芯子
	 */
	public void setModelCore(String modelCore) {
		this.modelCore = modelCore;
	}
	/**
	 * 获取：芯子
	 */
	public String getModelCore() {
		return modelCore;
	}
	/**
	 * 设置：气头
	 */
	public void setModelAirTou(String modelAirTou) {
		this.modelAirTou = modelAirTou;
	}
	/**
	 * 获取：气头
	 */
	public String getModelAirTou() {
		return modelAirTou;
	}
	/**
	 * 设置：冷却
	 */
	public void setModelCooling(String modelCooling) {
		this.modelCooling = modelCooling;
	}
	/**
	 * 获取：冷却
	 */
	public String getModelCooling() {
		return modelCooling;
	}
	/**
	 * 设置：钳片
	 */
	public void setModelClamp(String modelClamp) {
		this.modelClamp = modelClamp;
	}
	/**
	 * 获取：钳片
	 */
	public String getModelClamp() {
		return modelClamp;
	}
	/**
	 * 设置：容量
	 */
	public void setModelVolume(String modelVolume) {
		this.modelVolume = modelVolume;
	}
	/**
	 * 获取：容量
	 */
	public String getModelVolume() {
		return modelVolume;
	}
	/**
	 * 设置：经手人
	 */
	public void setModelHandlingPeople(String modelHandlingPeople) {
		this.modelHandlingPeople = modelHandlingPeople;
	}
	/**
	 * 获取：经手人
	 */
	public String getModelHandlingPeople() {
		return modelHandlingPeople;
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
	 * 设置：客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：客户名称
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：是否在库
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：是否在库
	 */
	public Integer getState() {
		return state;
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
}
