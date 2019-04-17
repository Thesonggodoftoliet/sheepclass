package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class Mistakes {
	private int userid;
	private int homeworkid;
	private int courseid;
	private int reviewtimes;//复习次数且正确次数
	private int wrongtimes;//错误次数

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getHomeworkid() {
		return homeworkid;
	}

	public void setHomeworkid(int homeworkid) {
		this.homeworkid = homeworkid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public int getReviewtimes() {
		return reviewtimes;
	}

	public void setReviewtimes(int reviewtimes) {
		this.reviewtimes = reviewtimes;
	}

	public int getWrongtimes() {
		return wrongtimes;
	}

	public void setWrongtimes(int wrongtimes) {
		this.wrongtimes = wrongtimes;
	}
}
