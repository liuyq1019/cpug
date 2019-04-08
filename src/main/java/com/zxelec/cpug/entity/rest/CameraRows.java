package com.zxelec.cpug.entity.rest;

import com.alibaba.fastjson.annotation.JSONField;

public class CameraRows {
	@JSONField(name = "Id")
	private String id;
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "ExternalId")
	private String externalId;
	@JSONField(name = "Manufacturer")
	private String manufacturer;
	@JSONField(name = "Model")
	private String model;
	@JSONField(name = "Address")
	private String address;
	@JSONField(name = "Longitude")
	private int longitude;
	@JSONField(name = "Latitude")
	private int latitude;
	@JSONField(name = "PTZType")
	private int pTZType;
	@JSONField(name = "Description")
	private String description;
	@JSONField(name = "SvacExts")
	private String svacExts;
	@JSONField(name = "PositionType")
	private String positionType;
	@JSONField(name = "RoomType")
	private String roomType;
	@JSONField(name = "UseType")
	private int useType;
	@JSONField(name = "SupplyLightType")
	private int supplyLightType;
	@JSONField(name = "DirectionType")
	private String directionType;
	@JSONField(name = "ModTime")
	private String modTime;
	@JSONField(name = "Type")
	private String type;
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
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getpTZType() {
		return pTZType;
	}
	public void setpTZType(int pTZType) {
		this.pTZType = pTZType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSvacExts() {
		return svacExts;
	}
	public void setSvacExts(String svacExts) {
		this.svacExts = svacExts;
	}
	public String getPositionType() {
		return positionType;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getUseType() {
		return useType;
	}
	public void setUseType(int useType) {
		this.useType = useType;
	}
	public int getSupplyLightType() {
		return supplyLightType;
	}
	public void setSupplyLightType(int supplyLightType) {
		this.supplyLightType = supplyLightType;
	}
	public String getDirectionType() {
		return directionType;
	}
	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}
	public String getModTime() {
		return modTime;
	}
	public void setModTime(String modTime) {
		this.modTime = modTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
