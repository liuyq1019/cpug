package com.zxelec.cpug.vl.entity.rest;

import com.alibaba.fastjson.annotation.JSONField;

public class Lanes {
	@JSONField(name = "laneId")
	private int laneId;
	@JSONField(name = "LaneNo")
	private int laneNo;
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "Direction")
	private String direction;
	@JSONField(name = "Desc")
	private String desc;
	@JSONField(name = "MaxSpeed")
	private int maxSpeed;
	@JSONField(name = "CityPass")
	private int cityPass;
	@JSONField(name = "ApeID")
	private String apeID;
	@JSONField(name = "LaneType")
	private String laneType;
	@JSONField(name = "MaxSpeed2")
	private int maxSpeed2;
	@JSONField(name = "minSpeed")
	private String minSpeed;
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public int getCityPass() {
		return cityPass;
	}
	public void setCityPass(int cityPass) {
		this.cityPass = cityPass;
	}
	public String getApeID() {
		return apeID;
	}
	public void setApeID(String apeID) {
		this.apeID = apeID;
	}
	public String getLaneType() {
		return laneType;
	}
	public void setLaneType(String laneType) {
		this.laneType = laneType;
	}
	public int getMaxSpeed2() {
		return maxSpeed2;
	}
	public void setMaxSpeed2(int maxSpeed2) {
		this.maxSpeed2 = maxSpeed2;
	}
	public String getMinSpeed() {
		return minSpeed;
	}
	public void setMinSpeed(String minSpeed) {
		this.minSpeed = minSpeed;
	}
	
	
}
