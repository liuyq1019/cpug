package com.zxelec.cpug.vl.entity.rest;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceList {
	@JSONField(name="DeviceID")
	private String deviceID;
	@JSONField(name="Name")
	private String name;
	@JSONField(name="Model")
	private String model;
	@JSONField(name="IPAddr")
	private String iPAddr;
	@JSONField(name="Longtidude")
	private double longtidude;
	@JSONField(name="Latitude")
	private double latitude;
	@JSONField(name="PlaceCode")
	private String placeCode;
	@JSONField(name="Place")
	private String place;
	@JSONField(name="OrgCode")
	private String orgCode;
	@JSONField(name="Capdirection")
	private int capdirection;
	@JSONField(name="updateTime",format="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getiPAddr() {
		return iPAddr;
	}
	public void setiPAddr(String iPAddr) {
		this.iPAddr = iPAddr;
	}
	public double getLongtidude() {
		return longtidude;
	}
	public void setLongtidude(double longtidude) {
		this.longtidude = longtidude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getPlaceCode() {
		return placeCode;
	}
	public void setPlaceCode(String placeCode) {
		this.placeCode = placeCode;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public int getCapdirection() {
		return capdirection;
	}
	public void setCapdirection(int capdirection) {
		this.capdirection = capdirection;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
