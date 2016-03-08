package com.parkingmanage.model;

/**
 * @author zhangx
 * @date 2016年1月18日
 */
public class AuthorityRoleDomain {
	
	private int userType;
	private int authorityId;
	
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	@Override
	public String toString() {
		return "AuthorityRoleDomain [userType=" + userType + ", authorityId="
				+ authorityId + "]";
	}

}
