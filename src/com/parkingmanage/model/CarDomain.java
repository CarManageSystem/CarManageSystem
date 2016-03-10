package com.parkingmanage.model;

import java.sql.Date;

/**
 * @author zhangx
 * @date 2015年12月31日
 */
public class CarDomain {
	
	private String carLicense;
	private int carBrand;
	private String carType;
	private String carColor;
	private Date productionDate;
	private String engineNumber;
	private String outputVolume;
	private String identifictionNumber;
	private int carDistance;
	private Date initialDate;
	private String carPhoto;
	private String owerName;
	private int owerAge;
	private int owerSex;
	private String owerAddress;
	private String owerTel;
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public int getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(int carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getOutputVolume() {
		return outputVolume;
	}
	public void setOutputVolume(String outputVolume) {
		this.outputVolume = outputVolume;
	}
	public String getIdentifictionNumber() {
		return identifictionNumber;
	}
	public void setIdentifictionNumber(String identifictionNumber) {
		this.identifictionNumber = identifictionNumber;
	}
	public int getCarDistance() {
		return carDistance;
	}
	public void setCarDistance(int carDistance) {
		this.carDistance = carDistance;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public String getCarPhoto() {
		return carPhoto;
	}
	public void setCarPhoto(String carPhoto) {
		this.carPhoto = carPhoto;
	}
	public String getOwerName() {
		return owerName;
	}
	public void setOwerName(String owerName) {
		this.owerName = owerName;
	}
	public int getOwerAge() {
		return owerAge;
	}
	public void setOwerAge(int owerAge) {
		this.owerAge = owerAge;
	}
	public int getOwerSex() {
		return owerSex;
	}
	public void setOwerSex(int owerSex) {
		this.owerSex = owerSex;
	}
	public String getOwerAddress() {
		return owerAddress;
	}
	public void setOwerAddress(String owerAddress) {
		this.owerAddress = owerAddress;
	}
	public String getOwerTel() {
		return owerTel;
	}
	public void setOwerTel(String owerTel) {
		this.owerTel = owerTel;
	}
	@Override
	public String toString() {
		return "CarDomain [carLicense=" + carLicense + ", carBrand=" + carBrand
				+ ", carType=" + carType + ", carColor=" + carColor
				+ ", productionDate=" + productionDate + ", engineNumber="
				+ engineNumber + ", outputVolume=" + outputVolume
				+ ", identifictionNumber=" + identifictionNumber
				+ ", carDistance=" + carDistance + ", initialDate="
				+ initialDate + ", carPhoto=" + carPhoto + ", owerName="
				+ owerName + ", owerAge=" + owerAge + ", owerSex=" + owerSex
				+ ", owerAddress=" + owerAddress + ", owerTel=" + owerTel + "]";
	}
	
	
}
