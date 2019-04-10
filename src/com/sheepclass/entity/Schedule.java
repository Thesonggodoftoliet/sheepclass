package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class Schedule{
	private float serialnum;
	private int courseid;
	private int userid;
	private Date breaktime;
	private int finish;

	public void setserialnum(float serialnum) {
		this.serialnum = serialnum;
	}

	public float getSerialnum() {
		return serialnum;
	}

	public void setcourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setuserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return userid;
	}

	public void setbreaktime(Date breaktime) {
		this.breaktime = breaktime;
	}

	public Date getBreaktime() {
		return breaktime;
	}

	public void setfinish(int finish) {
		this.finish = finish;
	}

	public int getFinish() {
		return finish;
	}
}
