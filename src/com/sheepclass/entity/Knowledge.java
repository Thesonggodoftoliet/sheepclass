package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class Knowledge {
	private int knowledgeid;
	private String content;
	private int level;

	public int getKnowledgeid() {
		return knowledgeid;
	}

	public void setKnowledgeid(int knowledgeid) {
		this.knowledgeid = knowledgeid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
