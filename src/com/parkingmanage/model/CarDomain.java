package com.parkingmanage.model;

//import java.sql.Date;
import java.util.Date;
/**
 * @author zhangx
 * @date 2015年12月31日
 */
public class CarDomain {
	
	private String carLicense;
	private String carBrand;
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
	private String drivingLicense;
	private String nation;
	private Date ownerBirthday;
	private String drivingLicenseType;
	private Date licenseIssueDate;
	private Date validStartDate;
	private String validTerm;
	private Date carIssueDate;
	private Date carValid;
	private String carCode;
	private String cardNum;
	
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
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
	
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	public Date getOwnerBirthday() {
		return ownerBirthday;
	}
	public void setOwnerBirthday(Date ownerBirthday) {
		this.ownerBirthday = ownerBirthday;
	}
	
	public String getDrivingLicenseType() {
		return drivingLicenseType;
	}
	public void setDrivingLicenseType(String drivingLicenseType) {
		this.drivingLicenseType = drivingLicenseType;
	}
	
	public Date getLicenseIssueDate() {
		return licenseIssueDate;
	}
	public void setLicenseIssueDate(Date licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}
	
	
	public Date getValidStartDate() {
		return validStartDate;
	}
	public void setValidStartDate(Date validStartDate) {
		this.validStartDate = validStartDate;
	}
	
	public String getValidTerm() {
		return validTerm;
	}
	public void setValidTerm(String validTerm) {
		this.validTerm = validTerm;
	}
	
	public Date getCarIssueDate() {
		return carIssueDate;
	}
	public void setCarIssueDate(Date carIssueDate) {
		this.carIssueDate = carIssueDate;
	}
	
	public Date getCarValid() {
		return carValid;
	}
	public void setCarValid(Date carValid) {
		this.carValid = carValid;
	}
	
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
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
				+ ", ownerAddress=" + ownerAddress + ", ownerTel=" + ownerTel + ",ownerBirthday=" + ownerBirthday
				+ ", drivingLicenseType=" + drivingLicenseType + ", licenseIssueDate=" + licenseIssueDate 
				+ ", validStartDate=" + validStartDate + ", validTerm=" + validTerm 
				+ ", carIssueDate=" + carIssueDate + ", carValid=" + carValid + ", carCode=" + carCode + ", cardNum=" + cardNum +"]";
	}
	
	
}
