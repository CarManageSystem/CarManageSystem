package com.parkingmanage.model;

import java.util.Date;

/**
 * @author zhangx
 * @date 2016年1月7日
 */

public class ParkIoDomain {
	private String parkioId;
	private String carLicense;
	private Date timeIn;
	private Date timeOut;
	private String photolocIn;
	private String photolocOut;
	private String carportId;
	public String getParkioId() {
		return parkioId;
	}
	public void setParkioId(String parkioId) {
		this.parkioId = parkioId;
	}
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public Date getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}
	public Date getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}
	public String getPhotolocIn() {
		return photolocIn;
	}
	public void setPhotolocIn(String photolocIn) {
		this.photolocIn = photolocIn;
	}
	public String getPhotolocOut() {
		return photolocOut;
	}
	public void setPhotolocOut(String photolocOut) {
		this.photolocOut = photolocOut;
	}
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	@Override
	public String toString() {
		return "ParkIoDomain [parkioId=" + parkioId + ", carLicense="
				+ carLicense + ", timeIn=" + timeIn + ", timeOut=" + timeOut
				+ ", photolocIn=" + photolocIn + ", photolocOut=" + photolocOut
				+ ", carportId=" + carportId + "]";
	}
}