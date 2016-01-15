package com.parkingmanage.model;


/**
 * @author zhangx
 * @date 2016年1月13日
 */
public class ChargeStandardDomain {
	
	private String standardId;
	private String startTime;
	private String endTime;
	private String timeBasis;
	private String perFee;
	
	public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTimeBasis() {
		return timeBasis;
	}
	public void setTimeBasis(String timeBasis) {
		this.timeBasis = timeBasis;
	}
	public String getPerFee() {
		return perFee;
	}
	public void setPerFee(String perFee) {
		this.perFee = perFee;
	}
	@Override
	public String toString() {
		return "ChargeStandardDomain [standardId=" + standardId
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", timeBasis=" + timeBasis + ", perFee=" + perFee + "]";
	}
}
	