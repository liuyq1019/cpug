package com.zxelec.cpug.vl.entity;

///////////////////////////////////////////////////////////////////////
// Copyright(C) 2018 Vitech Corporation. All Rights Reserved.
///////////////////////////////////////////////////////////////////////


import java.util.Date;

public class MotionVehicleType {


    private String id;

    private String motorVehicleID;

    private String tollgateID;

    private String tollgateName;

    private Integer laneNo;

    private String deviceID;

    private String equipmentType;

    private Date passTime;

    private String nameOfPassedRoad;

    private boolean hasPlate;

    private String plateNo;

    private String plateClass;

    private String plateColor;

    private Integer speed;

    private String direction;

    private String drivingStatusCode;

    private String vehicleClass;

    private String vehicleBrand;

    private String vehicleModel;

    private String vehicleStyles;

    private String vehicleLength;

    private Integer vehicleWidth;

    private Integer vehicleHeight;

//    private Integer vehicleColor;  暂时改为string
    
    private String vehicleColor;

    private Integer vehicleColorDepth;

    private Integer numOfPassenger;

    private Integer sunvisor;

    private Integer safetyBelt;

    private Integer calling;

    private String plateReliability;

    private String plateCharReliability;

    private String brandReliability;

    private Integer plateNumber;

    private String plateCoincide;

    private String rearPlateNo;

    private String rearPlateReliability;

    private String rearPlateCharReliability;

    private String rearPlateColor;

    private String rearPlateClass;

    private Integer limitedSpeed;

    private Integer markedSpeed;

    private Integer vehicleWeight;

    private Long redLightStartTime;

    private Long redLightEndTime;

    private Date redLightTime;

    private Date dealTag;

    private Integer applicationType;

    private Integer dispositionID;

    private VehiclePictureType[] pictures;

    private Integer lastLostCounter;

    private String carType;
    
    
    private String storageUrl1;//近景照片
    private String storageUrl2;//车牌照片
    private String storageUrl3;//远景照片
    private String storageUrl4;//合成图
    private String storageUrl5;//缩略图
    
    private String serialID;
    
    

    public String getSerialID() {
		return serialID;
	}

	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}

	public String getStorageUrl1() {
		return storageUrl1;
	}

	public void setStorageUrl1(String storageUrl1) {
		this.storageUrl1 = storageUrl1;
	}

	public String getStorageUrl2() {
		return storageUrl2;
	}

	public void setStorageUrl2(String storageUrl2) {
		this.storageUrl2 = storageUrl2;
	}

	public String getStorageUrl3() {
		return storageUrl3;
	}

	public void setStorageUrl3(String storageUrl3) {
		this.storageUrl3 = storageUrl3;
	}

	public String getStorageUrl4() {
		return storageUrl4;
	}

	public void setStorageUrl4(String storageUrl4) {
		this.storageUrl4 = storageUrl4;
	}

	public String getStorageUrl5() {
		return storageUrl5;
	}

	public void setStorageUrl5(String storageUrl5) {
		this.storageUrl5 = storageUrl5;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotorVehicleID() {
        return motorVehicleID;
    }

    public void setMotorVehicleID(String motorVehicleID) {
        this.motorVehicleID = motorVehicleID;
    }

    public String getTollgateID() {
        return tollgateID;
    }

    public void setTollgateID(String tollgateID) {
        this.tollgateID = tollgateID;
    }

    public String getTollgateName() {
        return tollgateName;
    }

    public void setTollgateName(String tollgateName) {
        this.tollgateName = tollgateName;
    }

    public Integer getLaneNo() {
        return laneNo;
    }

    public void setLaneNo(Integer laneNo) {
        this.laneNo = laneNo;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public String getNameOfPassedRoad() {
        return nameOfPassedRoad;
    }

    public void setNameOfPassedRoad(String nameOfPassedRoad) {
        this.nameOfPassedRoad = nameOfPassedRoad;
    }

    public boolean isHasPlate() {
        return hasPlate;
    }

    public void setHasPlate(boolean hasPlate) {
        this.hasPlate = hasPlate;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getPlateClass() {
        return plateClass;
    }

    public void setPlateClass(String plateClass) {
        this.plateClass = plateClass;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDrivingStatusCode() {
        return drivingStatusCode;
    }

    public void setDrivingStatusCode(String drivingStatusCode) {
        this.drivingStatusCode = drivingStatusCode;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleStyles() {
        return vehicleStyles;
    }

    public void setVehicleStyles(String vehicleStyles) {
        this.vehicleStyles = vehicleStyles;
    }

    public String getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(String vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public Integer getVehicleWidth() {
        return vehicleWidth;
    }

    public void setVehicleWidth(Integer vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public Integer getVehicleHeight() {
        return vehicleHeight;
    }

    public void setVehicleHeight(Integer vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }

//    public Integer getVehicleColor() {
//        return vehicleColor;
//    }
//
//    public void setVehicleColor(Integer vehicleColor) {
//        this.vehicleColor = vehicleColor;
//    }
    
    

    public Integer getVehicleColorDepth() {
        return vehicleColorDepth;
    }

    public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	public void setVehicleColorDepth(Integer vehicleColorDepth) {
        this.vehicleColorDepth = vehicleColorDepth;
    }

    public Integer getNumOfPassenger() {
        return numOfPassenger;
    }

    public void setNumOfPassenger(Integer numOfPassenger) {
        this.numOfPassenger = numOfPassenger;
    }

    public Integer getSunvisor() {
        return sunvisor;
    }

    public void setSunvisor(Integer sunvisor) {
        this.sunvisor = sunvisor;
    }

    public Integer getSafetyBelt() {
        return safetyBelt;
    }

    public void setSafetyBelt(Integer safetyBelt) {
        this.safetyBelt = safetyBelt;
    }

    public Integer getCalling() {
        return calling;
    }

    public void setCalling(Integer calling) {
        this.calling = calling;
    }

    public String getPlateReliability() {
        return plateReliability;
    }

    public void setPlateReliability(String plateReliability) {
        this.plateReliability = plateReliability;
    }

    public String getPlateCharReliability() {
        return plateCharReliability;
    }

    public void setPlateCharReliability(String plateCharReliability) {
        this.plateCharReliability = plateCharReliability;
    }

    public String getBrandReliability() {
        return brandReliability;
    }

    public void setBrandReliability(String brandReliability) {
        this.brandReliability = brandReliability;
    }

    public Integer getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(Integer plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateCoincide() {
        return plateCoincide;
    }

    public void setPlateCoincide(String plateCoincide) {
        this.plateCoincide = plateCoincide;
    }

    public String getRearPlateNo() {
        return rearPlateNo;
    }

    public void setRearPlateNo(String rearPlateNo) {
        this.rearPlateNo = rearPlateNo;
    }

    public String getRearPlateReliability() {
        return rearPlateReliability;
    }

    public void setRearPlateReliability(String rearPlateReliability) {
        this.rearPlateReliability = rearPlateReliability;
    }

    public String getRearPlateCharReliability() {
        return rearPlateCharReliability;
    }

    public void setRearPlateCharReliability(String rearPlateCharReliability) {
        this.rearPlateCharReliability = rearPlateCharReliability;
    }

    public String getRearPlateColor() {
        return rearPlateColor;
    }

    public void setRearPlateColor(String rearPlateColor) {
        this.rearPlateColor = rearPlateColor;
    }

    public String getRearPlateClass() {
        return rearPlateClass;
    }

    public void setRearPlateClass(String rearPlateClass) {
        this.rearPlateClass = rearPlateClass;
    }

    public Integer getLimitedSpeed() {
        return limitedSpeed;
    }

    public void setLimitedSpeed(Integer limitedSpeed) {
        this.limitedSpeed = limitedSpeed;
    }

    public Integer getMarkedSpeed() {
        return markedSpeed;
    }

    public void setMarkedSpeed(Integer markedSpeed) {
        this.markedSpeed = markedSpeed;
    }

    public Integer getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(Integer vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public Long getRedLightStartTime() {
        return redLightStartTime;
    }

    public void setRedLightStartTime(Long redLightStartTime) {
        this.redLightStartTime = redLightStartTime;
    }

    public Long getRedLightEndTime() {
        return redLightEndTime;
    }

    public void setRedLightEndTime(Long redLightEndTime) {
        this.redLightEndTime = redLightEndTime;
    }

    public Date getRedLightTime() {
        return redLightTime;
    }

    public void setRedLightTime(Date redLightTime) {
        this.redLightTime = redLightTime;
    }

    public Date getDealTag() {
        return dealTag;
    }

    public void setDealTag(Date dealTag) {
        this.dealTag = dealTag;
    }

    public Integer getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(Integer applicationType) {
        this.applicationType = applicationType;
    }

    public Integer getDispositionID() {
        return dispositionID;
    }

    public void setDispositionID(Integer dispositionID) {
        this.dispositionID = dispositionID;
    }

    public VehiclePictureType[] getPictures() {
        return pictures;
    }

    public void setPictures(VehiclePictureType[] pictures) {
        this.pictures = pictures;
    }

    public Integer getLastLostCounter() {
        return lastLostCounter;
    }

    public void setLastLostCounter(Integer lastLostCounter) {
        this.lastLostCounter = lastLostCounter;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}



