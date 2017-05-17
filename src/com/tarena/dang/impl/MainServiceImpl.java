package com.tarena.dang.impl;

import java.util.ArrayList;
import java.util.List;

import com.tarena.dang.dao.CategoryDao;
import com.tarena.dang.entity.Category;
import com.tarena.dang.service.MainService;

public class MainServiceImpl implements MainService{

	@Override
	public List<Category> findLeftCategory() {

		CategoryDao dao = new JDBCCategoryDao();
		List<Category> catsList;
		List<Category> cats = null;
		try {
			catsList = dao.findAll();
			if(catsList == null){
				System.out.println("list is null.");
			}
			cats = findSubCats(catsList , 1);
			for(Category c : cats){
				List subCats = findSubCats(catsList, c.getId());
				c.setSubCats(subCats);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cats;
	}
	
	public List<Category> findSubCats(List<Category> cats,int id){
		
		List<Category> subCats = new ArrayList<Category>();
		//Category c = cats.get(id);
		for(Category cat : cats){
			if(cat.getParentId().equals(id)){
				subCats.add(cat);
			}
		}
		return subCats;
		
	}

}
