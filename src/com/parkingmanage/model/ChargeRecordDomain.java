package com.parkingmanage.model;

import java.util.Date;

/**
 * @author zhangx
 * @date 2016年1月11日
 */
public class ChargeRecordDomain {
	
	private String chargeId;
	private Date payTime;
	private Date confirmTime;
	private float actualMoney;
	private String payType;
	
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	public float getActualMoney() {
		return actualMoney;
	}
	public void setActualMoney(float actualMoney) {
		this.actualMoney = actualMoney;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	@Override
	public String toString() {
		return "ChargeRecordDomain [chargeId=" + chargeId + ", payTime="
				+ payTime + ", confirmTime=" + confirmTime + ", actualMoney="
				+ actualMoney + ", payType=" + payType + "]";
	}
}
	