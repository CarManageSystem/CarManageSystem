package com.parkingmanage.model;

import java.util.Date;

public class ChargeCardDomain {
	private String carlicense;
	private String cardtype;
	private Date starttime;
	private Date endtime;
	
	public String getCarLicense() {
		return carlicense;
	}
	public void setCarLicense(String carlicense) {
		this.carlicense = carlicense;
	}
	
	public String getCardType() {
		return cardtype;
	}
	public void setCardType(String cardtype) {
		this.cardtype = cardtype;
	}
	
	public Date getStartTime() {
		return starttime;
	}
	public void setStartTime(Date starttime) {
		this.starttime = starttime;
	}
	
	public Date getEndTime() {
		return endtime;
	}
	public void setEndTime(Date endtime) {
		this.endtime = endtime;
	}
	
	@Override
	public String toString() {
		return "ChargeCardDomain [carlicense=" + carlicense + ", cardtype="
				+ cardtype + ", starttime=" + starttime + ", endtime="
				+ endtime + "]";
	}

}
