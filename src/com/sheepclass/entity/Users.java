package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class Users {
	private String nickname;
	private int userid;
	private String userpwd;
	private String email;
	private String phone;
	private int sex;
	private int identity;
	private int parentid;
	private long birthday;
	private long regist_time;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public long getRegistTime() {
		return regist_time;
	}

	public void setRegistTime(long registTime) {
		this.regist_time = registTime;
	}

}
