package com.parkingmanage.model;

import java.util.Date;

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
	private int userSex;
	private Date bornDate;
	private String userAddress;
	private String idNumber;
	private String nation;
	private String nativePlace;
	private String marriage;
	private String education;
	private String emergContact;
	private String emergTel;
	private int addFlag;
	private int onlineFlag;
	private String photoPath;
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
	public int getUserSex() {
		return userSex;
	}
	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEmergContact() {
		return emergContact;
	}
	public void setEmergContact(String emergContact) {
		this.emergContact = emergContact;
	}
	public String getEmergTel() {
		return emergTel;
	}
	public void setEmergTel(String emergTel) {
		this.emergTel = emergTel;
	}
	public int getAddFlag() {
		return addFlag;
	}
	public void setAddFlag(int addFlag) {
		this.addFlag = addFlag;
	}
	public int getOnlineFlag() {
		return onlineFlag;
	}
	public void setOnlineFlag(int onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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
				+ ", userType=" + userType + ", name=" + name + ", userSex="
				+ userSex + ", bornDate=" + bornDate + ", userAddress="
				+ userAddress + ", idNumber=" + idNumber + ", nation=" + nation
				+ ", nativePlace=" + nativePlace + ", marriage=" + marriage
				+ ", education=" + education + ", emergContact=" + emergContact
				+ ", emergTel=" + emergTel + ", addFlag=" + addFlag
				+ ", onlineFlag=" + onlineFlag + ", photoPath=" + photoPath
				+ ", roleName=" + roleName + "]";
	}
}