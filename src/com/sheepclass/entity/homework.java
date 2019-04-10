package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class homework{
	private int homeworkid;
	private String a;
	private String b;
	private String c;
	private String d;
	private String correct;
	private String content;
	private String sets;

	public void sethomeworkid(int homeworkid) {
		this.homeworkid = homeworkid;
	}

	public int getHomeworkid() {
		return homeworkid;
	}

	public void seta(String a) {
		this.a = a;
	}

	public String getA() {
		return a;
	}

	public void setb(String b) {
		this.b = b;
	}

	public String getB() {
		return b;
	}

	public void setc(String c) {
		this.c = c;
	}

	public String getC() {
		return c;
	}

	public void setd(String d) {
		this.d = d;
	}

	public String getD() {
		return d;
	}

	public void setcorrect(String correct) {
		this.correct = correct;
	}

	public String getCorrect() {
		return correct;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setsets(String sets) {
		this.sets = sets;
	}

	public String getSets() {
		return sets;
	}
}
