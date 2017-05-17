package com.tarena.dang.entity;

import java.io.Serializable;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3054133882435437782L;
	private Integer id;
	private String productName;
	private String description;
	private Long addTime;
	private Double fixedPrice;
	private Double dangPrice;
	private String keywords;
	private Integer hasDeleted;
	private String productPic;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getAddTime() {
		return addTime;
	}
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Double getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(Double fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public Double getDangPrice() {
		return dangPrice;
	}
	public void setDangPrice(Double dangPrice) {
		this.dangPrice = dangPrice;
	}
	public Integer getHasDeleted() {
		return hasDeleted;
	}
	public void setHasDeleted(Integer hasDeleted) {
		this.hasDeleted = hasDeleted;
	}
	public String getProductPic() {
		return productPic;
	}
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(obj==this){
			return true;
		}
		if(obj instanceof Product){
			Product o=(Product)obj;
			return o.id==this.id;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.id;
	}
}
