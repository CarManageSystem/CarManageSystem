package com.parkingmanage.model;

/**
 * @author zhangx
 * @date 2015年12月25日
 */

public class UserDomain {
	private String userId;
	private String userPwd;
	private String userName;
	private String userTel;
	private int userType;
	private String name;
	private int userAge;
	private int userSex;
	private String userAddress;
	private String addFlag;
	private int onlineFlag;
	private String roleName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public int getUserSex() {
		return userSex;
	}
	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}
	public int getOnlineFlag() {
		return onlineFlag;
	}
	public void setOnlineFlag(int onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "UserDomain [userId=" + userId + ", userPwd=" + userPwd
				+ ", userName=" + userName + ", userTel=" + userTel
				+ ", userType=" + userType + ", name=" + name + ", userAge="
				+ userAge + ", userSex=" + userSex + ", userAddress="
				+ userAddress + ", addFlag=" + addFlag + ", onlineFlag="
				+ onlineFlag + ", roleName=" + roleName + "]";
	}


	
	
}