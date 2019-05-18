package com.zxelec.cpug.vl.entity.rest;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

public class VissCpbsResponseStatus {
	@JSONField(name="RequestURL")
	private String requestURL;
	@JSONField(name="StatusCode")
	private String statusCode;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JSONField(name="LocalTime")
	private Date localTime;
	@JSONField(name="StatusString")
	private String statusString;
	@JSONField(name="ResourceId")
	private String resourceId;
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Date getLocalTime() {
		return localTime;
	}
	public void setLocalTime(Date localTime) {
		this.localTime = localTime;
	}
	public String getStatusString() {
		return statusString;
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	
}
