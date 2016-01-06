package com.parkingmanage.model;

/**
 * @author zhangx
 * @date 2016年1月5日
 */
public class CarportStateDomain {
	
	private int parkId;
	private int carportSum;
	private int fixedSum;
	private int tempSum;
	private int fixedUsing;
	private int tempUsing;
	private int fixedUnused;
	private int tempUnused;
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public int getCarportSum() {
		return carportSum;
	}
	public void setCarportSum(int carportSum) {
		this.carportSum = carportSum;
	}
	public int getFixedSum() {
		return fixedSum;
	}
	public void setFixedSum(int fixedSum) {
		this.fixedSum = fixedSum;
	}
	public int getTempSum() {
		return tempSum;
	}
	public void setTempSum(int tempSum) {
		this.tempSum = tempSum;
	}
	public int getFixedUsing() {
		return fixedUsing;
	}
	public void setFixedUsing(int fixedUsing) {
		this.fixedUsing = fixedUsing;
	}
	public int getTempUsing() {
		return tempUsing;
	}
	public void setTempUsing(int tempUsing) {
		this.tempUsing = tempUsing;
	}
	public int getFixedUnused() {
		return fixedUnused;
	}
	public void setFixedUnused(int fixedUnused) {
		this.fixedUnused = fixedUnused;
	}
	public int getTempUnused() {
		return tempUnused;
	}
	public void setTempUnused(int tempUnused) {
		this.tempUnused = tempUnused;
	}
	@Override
	public String toString() {
		return "CarportStateDomain [parkId=" + parkId + ", carportSum="
				+ carportSum + ", fixedSum=" + fixedSum + ", tempSum="
				+ tempSum + ", fixedUsing=" + fixedUsing + ", tempUsing="
				+ tempUsing + ", fixedUnused=" + fixedUnused + ", tempUnused="
				+ tempUnused + "]";
	}
	
	
}
