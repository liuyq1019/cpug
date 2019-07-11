package com.zxelec.cpug.entity.rest;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class DafaCamPushEntity {
	@JSONField(name="NotificationID")
	private String notificationID;
	@JSONField(name="SubscribeID")
	private String subscribeID;
	@JSONField(name="Title")
	private String title;
	@JSONField(name="TriggerTime",format="yyyy-MM-dd HH:mm:ss")
	private Date triggerTime;
	@JSONField(name="DeviceList")
	private List<DeviceList> deviceList;
	public String getNotificationID() {
		return notificationID;
	}
	public void setNotificationID(String notificationID) {
		this.notificationID = notificationID;
	}
	public String getSubscribeID() {
		return subscribeID;
	}
	public void setSubscribeID(String subscribeID) {
		this.subscribeID = subscribeID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTriggerTime() {
		return triggerTime;
	}
	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}
	public List<DeviceList> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<DeviceList> deviceList) {
		this.deviceList = deviceList;
	}

	
}
