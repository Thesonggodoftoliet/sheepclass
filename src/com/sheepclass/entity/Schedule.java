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
	private long breaktime;
	private int finish;

	public float getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(float serialnum) {
		this.serialnum = serialnum;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public long getBreaktime() {
		return breaktime;
	}

	public void setBreaktime(long breaktime) {
		this.breaktime = breaktime;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}
}
