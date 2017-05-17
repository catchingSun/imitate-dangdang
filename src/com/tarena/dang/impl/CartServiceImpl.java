package com.tarena.dang.impl;

import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.ProductDao;
import com.tarena.dang.entity.CartItem;
import com.tarena.dang.entity.Product;
import com.tarena.dang.service.CartService;

public class CartServiceImpl implements CartService{
	private List<CartItem> buyPros=new ArrayList<CartItem>();
	private List<CartItem> delPros=new ArrayList<CartItem>();
	//private List<CartItem> pros=new ArrayList<CartItem>();
	@Override
	public boolean add(int pid) {
		try {
			//将产品对象封装成Item,存入buyPros
			ProductDao productDao=new JDBCProductDao();
			Product pro=productDao.findById(pid);
			CartItem item=new CartItem();
			item.setPro(pro);
			item.setQty(1);
			if(!buyPros.contains(item)){
				//没有购买过,加入购物车
				buyPros.add(item);
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public double cost() throws Exception{
		//统计buyPros列表中的产品金额
		double totalCost=0;
		for(int i=0;i<buyPros.size();i++){
			CartItem item=buyPros.get(i);
			totalCost+=item.getPro().getDangPrice()*item.getQty();
		}
		return totalCost;
	}
	@Override
	public void delete(int pid) throws Exception{
		//将Item元素,从buyPros删除,添加到delPros
		for(int i=0;i<buyPros.size();i++){
			if(buyPros.get(i).getPro().getId()==pid){
				CartItem item=buyPros.remove(i);
				if(!delPros.contains(item)){
					delPros.add(item);
				}
			}
		}
	}
	@Override
	public List<CartItem> getBuyPros() throws Exception{
		//返回buyPros集合
		return this.buyPros;
	}
	
	
	
	
	@Override
	public List<CartItem> getDelPros() throws Exception{
		//返回删除列表集合
		return this.delPros;
	}
	@Override
	public void recovery(int pid) throws Exception{
		//将Item元素,从delPros删除,添加到buyPros
		for(int i=0;i<delPros.size();i++){
			if(delPros.get(i).getPro().getId()==pid){
				CartItem item=delPros.remove(i);
				if(!buyPros.contains(item)){
					buyPros.add(item);
				}
			}
		}
	}
	@Override
	public void update(int pid, int pnum) throws Exception{
		//将buyPros集合中的Item对象的qty修改为pnum
		for(int i=0;i<buyPros.size();i++){
			if(buyPros.get(i).getPro().getId()==pid){
				buyPros.get(i).setQty(pnum);
			}
		}
	}
	public void setBuyPros(List<CartItem> buyPros) {
		this.buyPros = buyPros;
	}

	public void setDelPros(List<CartItem> delPros) {
		this.delPros = delPros;
	}
	/*public List<CartItem> getPros() {
		return pros;
	}
	public void setPros(List<CartItem> pros) {
		this.pros = pros;
	}*/

	/**
	 * 依据一个字符串(1,11;3,8)<br/>
	 * 将集合中的商品放入购物车中已购买商品区域<br/>
	 * 或<br/>
	 * 将集合中的商品放入购物车中已删除商品区域<br/>
	 */
	@Override
	public void load(String content,List<CartItem> Pros) throws Exception {
		//如果content内容为空或者为0,没必要恢复
		if(content==null||content.equals("0")){
			return;
		}
		String[] contents=content.split(";");
		ProductDao productDAO=new JDBCProductDao();
		for(int i=0;i<contents.length;i++){
			String[] itemsStr=contents[i].split(",");
			int id=Integer.parseInt(itemsStr[0]);
			int qty=Integer.parseInt(itemsStr[1]);
			Product pro=productDAO.findById(id);
			CartItem item=new CartItem();
			item.setPro(pro);
			item.setQty(qty);
			//将商品条目对象添加到购物车
			if(!Pros.contains(item)){
				Pros.add(item);
			}
		}
	}

	/**
	 * 将购物车中的方法转换成一个字符串
	 * 如:1,11;3,8
	 * 表示id为1的商品买了11件,依此类推
	 */
	@Override
	public String store(List<CartItem> Pros) throws Exception {
		if(Pros.size()==0){
			return "0";
		}
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<Pros.size();i++){
			CartItem item=Pros.get(i);
			sb.append(item.getPro().getId()+","+item.getQty()+";");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
}
