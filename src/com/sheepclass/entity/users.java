package com.sheepclass.entity;

import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * 
 * 
 * @date 2019-04-09
 */ 
public class users{
	private String nickname;
	private int userid;
	private String userpwd;
	private String email;
	private String phone;
	private int sex;
	private int identity;
	private int parentid;
	private null birthday;
	private null registTime;
	private Date loginTime;

	public void setnickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setuserid(int userid) {
		this.userid = userid;
	}

	public int getUserid() {
		return userid;
	}

	public void setuserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setphone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setsex(int sex) {
		this.sex = sex;
	}

	public int getSex() {
		return sex;
	}

	public void setidentity(int identity) {
		this.identity = identity;
	}

	public int getIdentity() {
		return identity;
	}

	public void setparentid(int parentid) {
		this.parentid = parentid;
	}

	public int getParentid() {
		return parentid;
	}

	public void setbirthday(null birthday) {
		this.birthday = birthday;
	}

	public null getBirthday() {
		return birthday;
	}

	public void setRegistTime(null registTime) {
		this.registTime = registTime;
	}

	public null getRegistTime() {
		return registTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}
}
