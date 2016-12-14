package com.shankes.websocketclient.control.user.domain;

import java.util.List;

public class GroupInfo {

	private String groupId;
	private String groupName;
	private String groupDescription;
	private List<UserInfo> userInfos;

	public GroupInfo(String groupId, String groupName, String groupDescription, List<UserInfo> userInfos) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.userInfos = userInfos;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
}
