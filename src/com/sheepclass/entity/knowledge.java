package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class knowledge{
	private int knowledgeid;
	private String content;
	private int Level;

	public void setknowledgeid(int knowledgeid) {
		this.knowledgeid = knowledgeid;
	}

	public int getKnowledgeid() {
		return knowledgeid;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setLevel(int Level) {
		this.Level = Level;
	}

	public int getLevel() {
		return Level;
	}
}
