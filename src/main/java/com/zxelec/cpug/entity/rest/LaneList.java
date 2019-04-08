package com.zxelec.cpug.entity.rest;

import com.alibaba.fastjson.annotation.JSONField;

public class LaneList {
	@JSONField(name = "TollgateID")
	private String tollgateID;
	@JSONField(name = "LaneId")
	private int laneId;
	@JSONField(name = "LaneNo")
	private int laneNo;
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "Direction")
	private String direction;
	@JSONField(name = "CityPass")
	private int cityPass;
	@JSONField(name = "DeviceID")
	private String deviceID;
	public String getTollgateID() {
		return tollgateID;
	}
	public void setTollgateID(String tollgateID) {
		this.tollgateID = tollgateID;
	}
	
	
	public int getLaneId() {
		return laneId;
	}
	public void setLaneId(int laneId) {
		this.laneId = laneId;
	}
	public int getLaneNo() {
		return laneNo;
	}
	public void setLaneNo(int laneNo) {
		this.laneNo = laneNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public int getCityPass() {
		return cityPass;
	}
	public void setCityPass(int cityPass) {
		this.cityPass = cityPass;
	}
	
	

}
