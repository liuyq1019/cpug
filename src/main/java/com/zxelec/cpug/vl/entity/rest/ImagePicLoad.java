package com.zxelec.cpug.vl.entity.rest;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class ImagePicLoad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5855324134946433843L;
	
	@JSONField(name="serialID")
	private String serialID;
	@JSONField(name="PoolID")
	private String poolID;
	@JSONField(name="TimeStamp",format="yyyyMMddHHmmssSSS")
	private Date timeStamp;
	@JSONField(name="PictureType")
	private String pictureType;
	@JSONField(name="Token")
	private String token;
	@JSONField(name="PictureLength")
	private String pictureLength;
	@JSONField(name="Picture")
	private String picture;
	@JSONField(name="LunId")
	private String lunId;
	@JSONField(name="Path")
	private String path;
	@JSONField(name="Encoding")
	private String encoding;
	public String getSerialID() {
		return serialID;
	}
	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getLunId() {
		return lunId;
	}
	public void setLunId(String lunId) {
		this.lunId = lunId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	
	public String getPoolID() {
		return poolID;
	}
	public void setPoolID(String poolID) {
		this.poolID = poolID;
	}
	public String getPictureType() {
		return pictureType;
	}
	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}
	public String getPictureLength() {
		return pictureLength;
	}
	public void setPictureLength(String pictureLength) {
		this.pictureLength = pictureLength;
	}
	@Override
	public String toString() {
		return "ImagePidLoad [serialID=" + serialID + ", poolID=" + poolID + ", timeStamp=" + timeStamp
				+ ", pictureType=" + pictureType + ", token=" + token + ", pictureLength=" + pictureLength
				+ ", picture=" + picture + ", lunId=" + lunId + ", path=" + path + ", encoding=" + encoding + "]";
	}
	
	
}
