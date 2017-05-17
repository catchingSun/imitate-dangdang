package com.tarena.dang.entity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -15904859112196808L;
	private Integer id;
	private Integer turn;
	private String name;
	private String enName;
	private String description;
	private Integer parentId;
	
	//追加,用于存储当前类别的子项
	private List<Category> subCats;
	
	//
	private Integer pnum;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public List<Category> getSubCats() {
		return subCats;
	}

	public void setSubCats(List<Category> subCats) {
		this.subCats = subCats;
	}
	public Integer getPnum() {
		return pnum;
	}
	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}
}
