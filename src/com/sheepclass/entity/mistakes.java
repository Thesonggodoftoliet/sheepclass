package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class mistakes{
	private int userid;
	private int homeworkid;
	private int courseid;

	public void setuserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return userid;
	}

	public void sethomeworkid(int homeworkid) {
		this.homeworkid = homeworkid;
	}

	public int getHomeworkid() {
		return homeworkid;
	}

	public void setcourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getCourseid() {
		return courseid;
	}
}
