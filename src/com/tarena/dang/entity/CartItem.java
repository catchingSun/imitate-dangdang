package com.tarena.dang.entity;

import java.io.Serializable;

public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1216052951214292295L;
	private Product pro;
	private int qty=1;
	//是否购买  true:购买  false:删除
	private boolean buy=true;
	public Product getPro() {
		return pro;
	}
	public void setPro(Product pro) {
		this.pro = pro;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public boolean isBuy() {
		return buy;
	}
	public void setBuy(boolean buy) {
		this.buy = buy;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(obj==this){
			return true;
		}
		if(obj instanceof CartItem){
			CartItem o=(CartItem)obj;
			return o.getPro().getId()==this.getPro().getId();
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.pro.getId();
	}
}
