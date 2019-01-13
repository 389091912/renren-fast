package io.renren.modules.product.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wsy
 * @email 389091912@qq.com
 * @date 2019-01-14 00:02:12
 */
@TableName("product_info")
public class ProductInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 模具编号
	 */
	private String modelNo;
	/**
	 * 客户产品编号
	 */
	private String customerProductNo;
	/**
	 * 纸箱编号
	 */
	private Integer cartonId;
	/**
	 * 库存数量
	 */
	private Integer productNum;
	/**
	 * 产品克数
	 */
	private Integer productWeight;
	/**
	 * 产品图片
	 */
	private String productImage;
	/**
	 * 产品图纸
	 */
	private String productDrawing;
	/**
	 * 产品容量
	 */
	private Integer productVolume;
	/**
	 * 产品批次
	 */
	private String productBatch;
	/**
	 * 产品问题
	 */
	private String productQuestion;
	/**
	 * 产品组合套
	 */
	private String productAssort;
	/**
	 * 产品后续加工
	 */
	private String productTrailingProcess;
	/**
	 * 产品备注
	 */
	private String productRemark;
	/**
	 * 产品成品率
	 */
	private BigDecimal yield;
	/**
	 * 产品分类
	 */
	private Integer productCategory;

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
	 * 设置：客户产品编号
	 */
	public void setCustomerProductNo(String customerProductNo) {
		this.customerProductNo = customerProductNo;
	}
	/**
	 * 获取：客户产品编号
	 */
	public String getCustomerProductNo() {
		return customerProductNo;
	}
	/**
	 * 设置：纸箱编号
	 */
	public void setCartonId(Integer cartonId) {
		this.cartonId = cartonId;
	}
	/**
	 * 获取：纸箱编号
	 */
	public Integer getCartonId() {
		return cartonId;
	}
	/**
	 * 设置：库存数量
	 */
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	/**
	 * 获取：库存数量
	 */
	public Integer getProductNum() {
		return productNum;
	}
	/**
	 * 设置：产品克数
	 */
	public void setProductWeight(Integer productWeight) {
		this.productWeight = productWeight;
	}
	/**
	 * 获取：产品克数
	 */
	public Integer getProductWeight() {
		return productWeight;
	}
	/**
	 * 设置：产品图片
	 */
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	/**
	 * 获取：产品图片
	 */
	public String getProductImage() {
		return productImage;
	}
	/**
	 * 设置：产品图纸
	 */
	public void setProductDrawing(String productDrawing) {
		this.productDrawing = productDrawing;
	}
	/**
	 * 获取：产品图纸
	 */
	public String getProductDrawing() {
		return productDrawing;
	}
	/**
	 * 设置：产品容量
	 */
	public void setProductVolume(Integer productVolume) {
		this.productVolume = productVolume;
	}
	/**
	 * 获取：产品容量
	 */
	public Integer getProductVolume() {
		return productVolume;
	}
	/**
	 * 设置：产品批次
	 */
	public void setProductBatch(String productBatch) {
		this.productBatch = productBatch;
	}
	/**
	 * 获取：产品批次
	 */
	public String getProductBatch() {
		return productBatch;
	}
	/**
	 * 设置：产品问题
	 */
	public void setProductQuestion(String productQuestion) {
		this.productQuestion = productQuestion;
	}
	/**
	 * 获取：产品问题
	 */
	public String getProductQuestion() {
		return productQuestion;
	}
	/**
	 * 设置：产品组合套
	 */
	public void setProductAssort(String productAssort) {
		this.productAssort = productAssort;
	}
	/**
	 * 获取：产品组合套
	 */
	public String getProductAssort() {
		return productAssort;
	}
	/**
	 * 设置：产品后续加工
	 */
	public void setProductTrailingProcess(String productTrailingProcess) {
		this.productTrailingProcess = productTrailingProcess;
	}
	/**
	 * 获取：产品后续加工
	 */
	public String getProductTrailingProcess() {
		return productTrailingProcess;
	}
	/**
	 * 设置：产品备注
	 */
	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}
	/**
	 * 获取：产品备注
	 */
	public String getProductRemark() {
		return productRemark;
	}
	/**
	 * 设置：产品成品率
	 */
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
	/**
	 * 获取：产品成品率
	 */
	public BigDecimal getYield() {
		return yield;
	}
	/**
	 * 设置：产品分类
	 */
	public void setProductCategory(Integer productCategory) {
		this.productCategory = productCategory;
	}
	/**
	 * 获取：产品分类
	 */
	public Integer getProductCategory() {
		return productCategory;
	}
}
