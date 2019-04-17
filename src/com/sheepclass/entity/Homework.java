package com.sheepclass.entity;

import com.sheepclass.dao.KnowledgeDao;
import com.sheepclass.dao.implement.KnowledgeDaoImpl;
import com.sheepclass.utils.PraseUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class Homework implements Comparable{
	private int homeworkid;
	private String a;
	private String b;
	private String c;
	private String d;
	private String correct;
	private String content;
	private String sets;
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level){
		this.level = level;
	}
	public void setLevel() {
		int count = 0;
		List<Integer> list = PraseUtils.sToi(this.sets);
		KnowledgeDao knowledgeDao = new KnowledgeDaoImpl();
		for (int i = 0;i<list.size();i++)
			count+=knowledgeDao.getKnowledgeByid(list.get(i)).getLevel();
		this.level = count;
	}

	public int getHomeworkid(){
		return homeworkid;
	}

	public void setHomeworkid(int homeworkid) {
		this.homeworkid = homeworkid;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSets() {
		return sets;
	}

	public void setSets(String sets) {
		this.sets = sets;
	}

	@Override
	public int compareTo(Object o) {
		Homework p = (Homework)o;
		return this.homeworkid-p.homeworkid;
	}
}
