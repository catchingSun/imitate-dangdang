package com.tarena.dang.entity;

import java.io.Serializable;
import java.util.Date;

public class Book extends Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -92848084475314764L;
	private Integer id;
	private String author;
	private String publishing;
	private Long publishTime;
	private String wordNumber;
	private String whichEdtion;
	private String totalPage;
	private Integer printTime;
	private String printNumber;
	private String isbn;
	private String authorSummary;
	private String catalogue;
	
	//追加方法,用于获取时间对象
	public Date getPublishingDt(){
		return new Date(publishTime);
	}
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishing() {
		return publishing;
	}
	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}
	public Long getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Long publishTime) {
		this.publishTime = publishTime;
	}
	public String getWordNumber() {
		return wordNumber;
	}
	public void setWordNumber(String wordNumber) {
		this.wordNumber = wordNumber;
	}
	public String getWhichEdtion() {
		return whichEdtion;
	}
	public void setWhichEdtion(String whichEdtion) {
		this.whichEdtion = whichEdtion;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPrintTime() {
		return printTime;
	}
	public void setPrintTime(Integer printTime) {
		this.printTime = printTime;
	}
	public String getPrintNumber() {
		return printNumber;
	}
	public void setPrintNumber(String printNumber) {
		this.printNumber = printNumber;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAuthorSummary() {
		return authorSummary;
	}
	public void setAuthorSummary(String authorSummary) {
		this.authorSummary = authorSummary;
	}
	public String getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(obj==this){
			return true;
		}
		if(obj instanceof Book){
			Book o=(Book)obj;
			return o.id==this.id;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.id;
	}
}
