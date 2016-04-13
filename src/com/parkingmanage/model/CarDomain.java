package com.parkingmanage.model;

//import java.sql.Date;
import java.util.Date;
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
	private String ownerName;
	private int ownerAge;
	private int ownerSex;
	private String ownerAddress;
	private String ownerTel;
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
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public int getOwnerAge() {
		return ownerAge;
	}
	public void setOwnerAge(int ownerAge) {
		this.ownerAge = ownerAge;
	}
	public int getOwnerSex() {
		return ownerSex;
	}
	public void setOwnerSex(int ownerSex) {
		this.ownerSex = ownerSex;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getOwnerTel() {
		return ownerTel;
	}
	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}
	@Override
	public String toString() {
		return "CarDomain [carLicense=" + carLicense + ", carBrand=" + carBrand
				+ ", carType=" + carType + ", carColor=" + carColor
				+ ", productionDate=" + productionDate + ", engineNumber="
				+ engineNumber + ", outputVolume=" + outputVolume
				+ ", identifictionNumber=" + identifictionNumber
				+ ", carDistance=" + carDistance + ", initialDate="
				+ initialDate + ", carPhoto=" + carPhoto + ", ownerName="
				+ ownerName + ", ownerAge=" + ownerAge + ", ownerSex=" + ownerSex
				+ ", ownerAddress=" + ownerAddress + ", ownerTel=" + ownerTel + "]";
	}
	
	
}
