package com.parkingmanage.model;

/**
 * @author zhangx
 * @date 2016年1月18日
 */
public class RoleDomain {
	
	private int userType;
	private String roleName;
	
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "RoleDomain [userType=" + userType + ", roleName=" + roleName
				+ "]";
	}
	
}
