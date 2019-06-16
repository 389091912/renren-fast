package io.renren.modules.product.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * @date 2019-04-25 23:43:32
 */
@Data
@TableName("ingredient_detail")
public class IngredientDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@Excel( name = "序号" )
	private Integer id;
	/**
	 * 供货商名称
	 */
	private Integer supplierId;
	/**
	 * 原料名称

	 */
	private Integer ingredientId;
	/**
	 * 吨数
	 */
	@TableField(exist = false)
	@Excel( name = "辅料名称",width = 22)
	private String materialName;

	@TableField(exist = false)
	@Excel( name = "供应商名称" ,width = 25)
	private String supplierName;

	@Excel(name="吨数")
	private Double weight;
	/**
	 * 价格
	 */
	@Excel(name="价格")
	private Double price;
	/**
	 * 票号
	 */
	@Excel(name="票号",width = 20)
	private String tickerNumber;
	/**
	 * 类型0为入库，1为出库
	 */
	@Excel(name="类型",replace = {"入库_1", "出库_0"})
	private Integer type;

	/**
	 * 
	 */
	private Integer createUser;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer updateUser;
	/**
	 * 
	 */
	private Date updataTime;



	@Excel( name = "货款支付状态",width = 22,replace = {"未付款_0", "已付款_1"," _null"})
	private Integer isPay;



	@Excel( name = "运费单价")
	private Double freightCost;

	@Excel( name = "运费支付状态" ,width = 22,replace = {"未付款_0", "已付款_1"," _null"})
	private Integer freightCostPay;
	@Excel(name="日期",width = 23,databaseFormat = "yyyy-MM-dd",format = "yyyy-MM-dd")
	private Date detailTime;
	/**
	 * 图片
	 */
//	@Excel( name = "订单留存",type = 2)
	private String imageUrl;
	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：供货商名称
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供货商名称
	 */
	public Integer getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：原料名称

	 */
	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}
	/**
	 * 获取：原料名称

	 */
	public Integer getIngredientId() {
		return ingredientId;
	}
	/**
	 * 设置：吨数
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * 获取：吨数
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * 设置：票号
	 */
	public void setTickerNumber(String tickerNumber) {
		this.tickerNumber = tickerNumber;
	}
	/**
	 * 获取：票号
	 */
	public String getTickerNumber() {
		return tickerNumber;
	}
	/**
	 * 设置：类型0为入库，1为出库
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型0为入库，1为出库
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：图片
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * 获取：图片
	 */
	public String getImageUrl() {
		return imageUrl;
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
