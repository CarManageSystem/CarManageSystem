package com.parkingmanage.model;

/**
 * @author zhangx
 * @date 2016年1月4日
 */
public class CarportDomain {
	
	private int carportId;
	private int parkId;
	private int carportState;
	private String carLicense;
	private int carportProperty;
	
	public int getCarportId() {
		return carportId;
	}
	public void setCarportId(int carportId) {
		this.carportId = carportId;
	}
	public int getParkId() {
		return parkId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public int getCarportState() {
		return carportState;
	}
	public void setCarportState(int carportState) {
		this.carportState = carportState;
	}
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public int getCarportProperty() {
		return carportProperty;
	}
	public void setCarportProperty(int carportProperty) {
		this.carportProperty = carportProperty;
	}
	@Override
	public String toString() {
		return "CarportDomain [carportId=" + carportId + ", parkId=" + parkId
				+ ", carportState=" + carportState + ", carLicense="
				+ carLicense + ", carportProperty=" + carportProperty + "]";
	}
}
