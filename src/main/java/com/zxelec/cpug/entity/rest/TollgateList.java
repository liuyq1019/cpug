package com.zxelec.cpug.entity.rest;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TollgateList {
	
	@JSONField(name = "TollgateID" )
	private String tollgateID;
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "Longtidude")
	private double longtidude;
	@JSONField(name = "Latitude")
	private double latitude;
	@JSONField(name = "PlaceCode")
	private String placeCode;
	@JSONField(name = "Place")
	private String place;
	@JSONField(name = "Status")
	private int status;
	@JSONField(name = "TollgateType")
	private int tollgateType;
	@JSONField(name = "LaneNum")
	private int laneNum;
	@JSONField(name = "OrgCode")
	private String orgCode;
	@JSONField(name = "ActiveTime",format="yyyy-MM-dd HH:mm:ss")
	private Date activeTime;
	public String getTollgateID() {
		return tollgateID;
	}
	public void setTollgateID(String tollgateID) {
		this.tollgateID = tollgateID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTollgateType() {
		return tollgateType;
	}
	public void setTollgateType(int tollgateType) {
		this.tollgateType = tollgateType;
	}
	public int getLaneNum() {
		return laneNum;
	}
	public void setLaneNum(int laneNum) {
		this.laneNum = laneNum;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public Date getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
}
