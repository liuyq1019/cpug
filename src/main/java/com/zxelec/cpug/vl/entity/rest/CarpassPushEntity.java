package com.zxelec.cpug.vl.entity.rest;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 过车记录推送实体
 * @author liu.yongquan
 *
 */
public class CarpassPushEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8043262670346628099L;
	@JSONField(name = "MotorVehicleID")
	private String motorVehicleID;
	@JSONField(name = "SourceID")
    private String sourceID;
	@JSONField(name = "TransportID")
    private String transportID;
	@JSONField(name = "DeviceID")
    private String deviceID;
	@JSONField(name = "StorageUrl1")
    private String storageUrl1;
	@JSONField(name = "StorageUrl2")
    private String storageUrl2;
	
	
	@JSONField(name = "StorageUrl3")
    private String storageUrl3;
	@JSONField(name = "StorageUrl4")
    private String storageUrl4;
	
	@JSONField(name = "StorageUrl5")
    private String storageUrl5;
	
	@JSONField(name = "LeftTopX")
    private int leftTopX;
	@JSONField(name = "LeftTopY")
    private int leftTopY;
	@JSONField(name = "RightBtmX")
    private int rightBtmX;
	@JSONField(name = "RightBtmY")
    private int rightBtmY;
	
	@JSONField(name = "PlateClass")
    private String plateClass;
	@JSONField(name = "PlateColor")
    private String plateColor;
	@JSONField(name = "PlateNo")
    private String plateNo;
	@JSONField(name = "Speed")
    private int speed;
	@JSONField(name = "Direction")
    private String direction;
	@JSONField(name = "VehicleClass")
    private String vehicleClass;
	@JSONField(name = "VehicleBrand")
	private String vehicleBrand;
	
	@JSONField(name = "VehicleLength")
    private int vehicleLength;
	
	
	@JSONField(name = "VehicleWidth")
    private int vehicleWidth;
	@JSONField(name = "VehicleHeight")
    private int vehicleHeight;
	@JSONField(name = "VehicleColor")
    private String vehicleColor;
	@JSONField(name = "VehicleColorDepth")
    private String vehicleColorDepth;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JSONField(name = "PassTime",format="yyyy-MM-dd HH:mm:ss")
    private Date passTime;
	@JSONField(name = "LaneId")
    private int laneId;
	@JSONField(name = "PlaceCode")
    private String placeCode;
	
	
	
	public int getLeftTopX() {
		return leftTopX;
	}
	public void setLeftTopX(int leftTopX) {
		this.leftTopX = leftTopX;
	}
	public int getLeftTopY() {
		return leftTopY;
	}
	public void setLeftTopY(int leftTopY) {
		this.leftTopY = leftTopY;
	}
	public int getRightBtmX() {
		return rightBtmX;
	}
	public void setRightBtmX(int rightBtmX) {
		this.rightBtmX = rightBtmX;
	}
	public int getRightBtmY() {
		return rightBtmY;
	}
	public void setRightBtmY(int rightBtmY) {
		this.rightBtmY = rightBtmY;
	}
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public String getMotorVehicleID() {
		return motorVehicleID;
	}
	public void setMotorVehicleID(String motorVehicleID) {
		this.motorVehicleID = motorVehicleID;
	}
	public String getSourceID() {
		return sourceID;
	}
	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}
	public String getTransportID() {
		return transportID;
	}
	public void setTransportID(String transportID) {
		this.transportID = transportID;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
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
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getVehicleClass() {
		return vehicleClass;
	}
	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}
	public int getVehicleLength() {
		return vehicleLength;
	}
	public void setVehicleLength(int vehicleLength) {
		this.vehicleLength = vehicleLength;
	}
	public int getVehicleWidth() {
		return vehicleWidth;
	}
	public void setVehicleWidth(int vehicleWidth) {
		this.vehicleWidth = vehicleWidth;
	}
	public int getVehicleHeight() {
		return vehicleHeight;
	}
	public void setVehicleHeight(int vehicleHeight) {
		this.vehicleHeight = vehicleHeight;
	}
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public String getVehicleColorDepth() {
		return vehicleColorDepth;
	}
	public void setVehicleColorDepth(String vehicleColorDepth) {
		this.vehicleColorDepth = vehicleColorDepth;
	}
	public Date getPassTime() {
		return passTime;
	}
	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}
	
	
	public int getLaneId() {
		return laneId;
	}
	public void setLaneId(int laneId) {
		this.laneId = laneId;
	}
	public String getPlaceCode() {
		return placeCode;
	}
	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
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
	
	
}
