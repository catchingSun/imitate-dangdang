package com.tarena.dang.service;

import java.util.List;

import com.tarena.dang.entity.CartItem;


public interface CartService {
	//购买
	public boolean add(int pid)throws Exception;
	//删除
	public void delete(int pid)throws Exception;
	public void update(int pid,int pnum)throws Exception;
	//恢复购买
	public void recovery(int pid)throws Exception;
	public double cost()throws Exception;
	public List<CartItem> getBuyPros()throws Exception;
	public List<CartItem> getDelPros()throws Exception;
	//用于关闭会话后恢复购物车
	public String store(List<CartItem> Pros)throws Exception;
	public void load(String content,List<CartItem> Pros)throws Exception;
}
