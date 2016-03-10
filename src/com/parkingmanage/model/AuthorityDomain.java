package com.parkingmanage.model;

/**
 * @author zhangx
 * @date 2016年1月18日
 */
public class AuthorityDomain {
	
	private int authorityId;
	private String authorityRemark;
	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthorityRemark() {
		return authorityRemark;
	}
	public void setAuthorityRemark(String authorityRemark) {
		this.authorityRemark = authorityRemark;
	}
	@Override
	public String toString() {
		return "AuthorityDomain [authorityId=" + authorityId
				+ ", authorityRemark=" + authorityRemark + "]";
	}

}
