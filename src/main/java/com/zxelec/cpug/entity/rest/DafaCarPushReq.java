package com.zxelec.cpug.entity.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 过车记录推送大华
 * @author liu.yongquan
 *
 */
public class DafaCarPushReq implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2866994296141139582L;

	@JSONField(name = "NotificationID")
	private String notificationID;
	
	@JSONField(name = "SubscribeID")
	private String subscribeID;
	
	@JSONField(name = "Title")
	private String title;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JSONField(name = "TriggerTime",format="yyyy-MM-dd HH:mm:ss")
	private Date triggerTime;
	
	@JSONField(name = "MotorVehicleObjectList")
	private List<CarpassPushEntity> motorVehicleObjectList;

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

	public List<CarpassPushEntity> getMotorVehicleObjectList() {
		return motorVehicleObjectList;
	}

	public void setMotorVehicleObjectList(List<CarpassPushEntity> motorVehicleObjectList) {
		this.motorVehicleObjectList = motorVehicleObjectList;
	}
	
	
}
