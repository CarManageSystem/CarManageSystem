package com.parkingmanage.model;

import java.util.Date;

public class CardManageDomain {
	
	private String cardtype;
	private String cardnum;
	private String applyroomnum;
	private String relowner;
	private String carshad;
	private String apply;
	private String nameowner;
	private String telowner;
	private Date timestart;
	private Date timeend;
	private String carporttype;
	private String carportnum;
	private float paymoney;
	private String paytime;
	private String nameapply;
	private String telapply;
	private String invoice;
	private String title;
	private String address;
	private String freight;
	private String post;
	
	public String getCardType() {
		return cardtype;
	}
	public void setCardType(String cardtype) {
		this.cardtype = cardtype;
	}
	
	public String getCardNum() {
		return cardnum;
	}
	public void setCardNum(String cardnum) {
		this.cardnum = cardnum;
	}
	
	public String getApplyRoomNum() {
		return applyroomnum;
	}
	public void setApplyRoomNum(String applyroomnum) {
		this.applyroomnum = applyroomnum;
	}
	
	public String getRelOwner() {
		return relowner;
	}
	public void setRelOwner(String relowner) {
		this.relowner = relowner;
	}
	
	public String getCarsHad() {
		return carshad;
	}
	public void setCarsHad(String carshad) {
		this.carshad = carshad;
	}
	
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	
	public String getNameOwner() {
		return nameowner;
	}
	public void setNameOwner(String nameowner) {
		this.nameowner = nameowner;
	}
	
	public String getTelOwner() {
		return telowner;
	}
	public void setTelOwner(String telowner) {
		this.telowner = telowner;
	}
	
	public Date getTimeStart() {
		return timestart;
	}
	public void setTimeStart(Date timestart) {
		this.timestart = timestart;
	}
	
	public Date getTimeEnd() {
		return timeend;
	}
	public void setTimeEnd(Date timeend) {
		this.timeend = timeend;
	}
	
	public String getCarportType() {
		return carporttype;
	}
	public void setCarportType(String carporttype) {
		this.carporttype = carporttype;
	}
	
	public String getCarportNum() {
		return carportnum;
	}
	public void setCarportNum(String carportnum) {
		this.carportnum = carportnum;
	}
	
    public float getPayMoney() {
    	return paymoney;
    }
    public void setPayMoney(float paymoney) {
    	this.paymoney = paymoney;
    }
	
    public String getPayTime() {
		return paytime;
	}
	public void setPayTime(String paytime) {
		this.paytime = paytime;
	}
	
	public String getNameApply() {
		return nameapply;
	}
	public void setNameApply(String nameapply) {
		this.nameapply = nameapply;
	}
	
	public String getTelApply() {
		return telapply;
	}
	public void setTelApply(String telapply) {
		this.telapply = telapply;
	}
	
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	@Override
	public String toString() {
		return "CardManageDomain [cardtype=" + cardtype + ", cardnum=" + cardnum
				+ ", applyroomnum=" + applyroomnum + ", relowner=" + relowner
				+ ", carshad=" + carshad + ", apply="+ apply 
				+ ", nameowner=" + nameowner+ ", telowner=" + telowner
				+ ", timestart=" + timestart + ", timeend="+ timeend 
				+ ", carporttype=" + carporttype + ", carportnum="+ carportnum 
				+ ", paymoney=" + paymoney + ", paytime=" + paytime
				+ ", nameapply=" + nameapply + ", telapply=" + telapply 
				+ ", invoice=" + invoice + ", title=" + title
				+ ", address=" + address + ", freight=" + freight +",post="+post+"]";
	}
	
}
