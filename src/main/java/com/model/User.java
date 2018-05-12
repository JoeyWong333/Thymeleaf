package com.model;

/**
 * 用户类
 * @author wangll
 *
 */
public class User {
	private static final long	serialVersionUID	= 1L;
	private int id;
	private String userName;
	private String passwd;
	// 最后更新时间
	private long lastUpdateTime;  //最后更新时间
	
	public User(String userName,String passwd) {
		this.userName = userName;
		this.passwd = passwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
