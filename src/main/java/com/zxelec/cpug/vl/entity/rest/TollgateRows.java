package com.zxelec.cpug.vl.entity.rest;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TollgateRows {
	@JSONField(name = "Id")
	private String id;
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "Longitude")
	private double longitude;
	@JSONField(name = "Latitude")
	private double latitude;
	@JSONField(name = "PlaceCode")
	private String placeCode;
	@JSONField(name = "Place")
	private String place;
	@JSONField(name = "Status")
	private int status;
	@JSONField(name = "TollgateCat")
	private int tollgateCat;
	@JSONField(name = "TollgateCat2")
	private int tollgateCat2;
	@JSONField(name = "LaneNum")
	private int laneNum;
	@JSONField(name = "OrgCode")
	private String orgCode;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JSONField(name = "ActiveTime",format="yyyy-MM-dd HH:mm:ss")
	private Date activeTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JSONField(name = "ModTime",format="yyyy-MM-dd HH:mm:ss")
	private Date modTime;
	@JSONField(name = "Lanes")
	private List<Lanes> lanes ;
	@JSONField(name = "ShortName")
	private String shortName;
	@JSONField(name = "TollgateType")
	private String tollgateType;
	@JSONField(name = "RoadCode")
	private String roadCode;
	@JSONField(name = "IntersectionCode")
	private String intersectionCode;
	@JSONField(name = "RoadOffset")
	private String roadOffset;
	@JSONField(name = "UpDirection")
	private String upDirection;
	@JSONField(name = "DownDirection")
	private String downDirection;
	@JSONField(name = "RoadType")
	private String roadType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
	public int getTollgateCat() {
		return tollgateCat;
	}
	public void setTollgateCat(int tollgateCat) {
		this.tollgateCat = tollgateCat;
	}
	public int getTollgateCat2() {
		return tollgateCat2;
	}
	public void setTollgateCat2(int tollgateCat2) {
		this.tollgateCat2 = tollgateCat2;
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
	public Date getModTime() {
		return modTime;
	}
	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}
	public List<Lanes> getLanes() {
		return lanes;
	}
	public void setLanes(List<Lanes> lanes) {
		this.lanes = lanes;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getTollgateType() {
		return tollgateType;
	}
	public void setTollgateType(String tollgateType) {
		this.tollgateType = tollgateType;
	}
	public String getRoadCode() {
		return roadCode;
	}
	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}
	public String getIntersectionCode() {
		return intersectionCode;
	}
	public void setIntersectionCode(String intersectionCode) {
		this.intersectionCode = intersectionCode;
	}
	public String getRoadOffset() {
		return roadOffset;
	}
	public void setRoadOffset(String roadOffset) {
		this.roadOffset = roadOffset;
	}
	public String getUpDirection() {
		return upDirection;
	}
	public void setUpDirection(String upDirection) {
		this.upDirection = upDirection;
	}
	public String getDownDirection() {
		return downDirection;
	}
	public void setDownDirection(String downDirection) {
		this.downDirection = downDirection;
	}
	public String getRoadType() {
		return roadType;
	}
	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	
}
