package com.parkingmanage.model;

public class ChargeRuleDomain {
	
	private int freetime;
	private int dayunit;
	private int nightunit;
	private String daystart;
	private String dayend;
	private float bwi_dayfee;
	private float bwo_dayfee;
	private float bwi_nightfee;
	private float bwo_nightfee;
	private float bri_dayfee; 
	private float bro_dayfee;
	private float bri_nightfee;
	private float bro_nightfee;
	private float swi_dayfee;
	private float swo_dayfee;
	private float swi_nightfee;
	private float swo_nightfee;
	private float sri_dayfee; 
	private float sro_dayfee;
	private float sri_nightfee;
	private float sro_nightfee;
	
	public int getFreeTime() {
		return freetime;
	}
	public void setFreeTime(int freetime) {
		this.freetime = freetime;
	}
	
	public int getDayUnit() {
		return dayunit;
	}
	public void setDayUnit(int dayunit) {
		this.dayunit = dayunit;
	}
	
	public int getNightUnit() {
		return nightunit;
	}
	public void setNightUnit(int nightunit) {
		this.nightunit = nightunit;
	}
	
	public String getDayStart() {
		return daystart;
	}
	public void setDayStart(String daystart) {
		this.daystart = daystart;
	}
	
	public String getDayEnd() {
		return dayend;
	}
	public void setDayEnd(String dayend) {
		this.dayend = dayend;
	}
	
	public float getBwiDayFee() {
		return bwi_dayfee;
	}
	public void setBwiDayFee(float bwi_dayfee) {
		this.bwi_dayfee = bwi_dayfee;
	}
	
	public float getBwoDayFee() {
		return bwo_dayfee;
	}
	public void setBwoDayFee(float bwo_dayfee) {
		this.bwo_dayfee = bwo_dayfee;
	}
	
	public float getBwiNightFee() {
		return bwi_nightfee;
	}
	public void setBwiNightFee(float bwi_nightfee) {
		this.bwi_nightfee = bwi_nightfee;
	}
	
	public float getBwoNightFee() {
		return bwo_nightfee;
	}
	public void setBwoNightFee(float bwo_nightfee) {
		this.bwo_nightfee = bwo_nightfee;
	}
	
	public float getBriDayFee() {
		return bri_dayfee;
	}
	public void setBriDayFee(float bri_dayfee) {
		this.bri_dayfee = bri_dayfee;
	}
	
	public float getBroDayFee() {
		return bro_dayfee;
	}
	public void setBroDayFee(float bro_dayfee) {
		this.bro_dayfee = bro_dayfee;
	}
	
	public float getBriNightFee() {
		return bri_nightfee;
	}
	public void setBriNightFee(float bri_nightfee) {
		this.bri_nightfee = bri_nightfee;
	}
	
	public float getBroNightFee() {
		return bro_nightfee;
	}
	public void setBroNightFee(float bro_nightfee) {
		this.bro_nightfee = bro_nightfee;
	}
	////////////
	public float getSwiDayFee() {
		return swi_dayfee;
	}
	public void setSwiDayFee(float swi_dayfee) {
		this.swi_dayfee = swi_dayfee;
	}
	
	public float getSwoDayFee() {
		return swo_dayfee;
	}
	public void setSwoDayFee(float swo_dayfee) {
		this.swo_dayfee = swo_dayfee;
	}
	
	public float getSwiNightFee() {
		return swi_nightfee;
	}
	public void setSwiNightFee(float swi_nightfee) {
		this.swi_nightfee = swi_nightfee;
	}
	
	public float getSwoNightFee() {
		return swo_nightfee;
	}
	public void setSwoNightFee(float swo_nightfee) {
		this.swo_nightfee = swo_nightfee;
	}
	
	public float getSriDayFee() {
		return sri_dayfee;
	}
	public void setSriDayFee(float sri_dayfee) {
		this.sri_dayfee = sri_dayfee;
	}
	
	public float getSroDayFee() {
		return sro_dayfee;
	}
	public void setSroDayFee(float sro_dayfee) {
		this.sro_dayfee = sro_dayfee;
	}
	
	public float getSriNightFee() {
		return sri_nightfee;
	}
	public void setSriNightFee(float sri_nightfee) {
		this.sri_nightfee = sri_nightfee;
	}
	
	public float getSroNightFee() {
		return sro_nightfee;
	}
	public void setSroNightFee(float sro_nightfee) {
		this.sro_nightfee = sro_nightfee;
	}
	
	@Override
	public String toString() {
		return "ChargeRuleDomain [freetime=" + freetime
				+ ", dayunit=" + dayunit + ", nightunit=" + nightunit
				+ ", daystart=" + daystart + ", dayend=" + dayend 
				+ ", bwi_dayfee=" + bwi_dayfee + ", bwo_dayfee=" + bwo_dayfee + ", bwi_nightfee=" + bwi_nightfee + ", bwo_nightfee=" + bwo_nightfee 
				+ ", bri_dayfee=" + bri_dayfee + ", bro_dayfee=" + bro_dayfee + ", bri_nightfee=" + bri_nightfee + ", bro_nightfee=" + bro_nightfee
				+ ", swi_dayfee=" + swi_dayfee + ", swo_dayfee=" + swo_dayfee + ", swi_nightfee=" + swi_nightfee + ", swo_nightfee=" + swo_nightfee 
				+ ", sri_dayfee=" + sri_dayfee + ", sro_dayfee=" + sro_dayfee + ", sri_nightfee=" + sri_nightfee + ", sro_nightfee=" + sro_nightfee +"]";
	}

}
