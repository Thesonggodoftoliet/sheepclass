package com.sheepclass.entity;

public class Learninginfo implements Comparable{
    private int key;
    private int userid;
    private long logintime;
    private long logouttime;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public long getLogintime() {
        return logintime;
    }

    public void setLogintime(long logintime) {
        this.logintime = logintime;
    }

    public long getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(long logouttime) {
        this.logouttime = logouttime;
    }

    @Override
    public int compareTo(Object o) {
        Learninginfo p = (Learninginfo)o;
        return (int)(this.logintime - p.logintime);
    }
}
