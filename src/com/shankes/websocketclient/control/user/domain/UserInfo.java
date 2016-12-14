package com.shankes.websocketclient.control.user.domain;

import java.io.Serializable;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String userIcon;
	private int age;
	private String gender;

	public UserInfo(String userId, String userName, String userIcon, int age, String gender) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userIcon = userIcon;
		this.age = age;
		this.gender = gender;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
