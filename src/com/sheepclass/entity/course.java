package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class course{
	private int courseid;
	private String coursename;
	private String info;
	private String Subject;
	private String img;

	public void setcourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setcoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setinfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setSubject(String Subject) {
		this.Subject = Subject;
	}

	public String getSubject() {
		return Subject;
	}

	public void setimg(String img) {
		this.img = img;
	}

	public String getImg() {
		return img;
	}
}
