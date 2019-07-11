package com.zxelec.cpug.entity.rest;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DahuaSubscribeRsp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4509029476226938161L;
	
	@JSONField(name = "StatusCode")
	private String statusCode;
	
	@JSONField(name = "ConfirmatStatus")
	private String confirmatStatus;
	
	@JSONField(name = "StatusString")
	private String statusString;
	
	@JSONField(name = "Id")
	private String id;
	
	@JSONField(name = "RequestURL")
	private String requestURL;
	

	public DahuaSubscribeRsp() {
	}

	public DahuaSubscribeRsp(String statusCode, String confirmatStatus, String statusString, String id,
			String requestURL) {
		this.statusCode = statusCode;
		this.confirmatStatus = confirmatStatus;
		this.statusString = statusString;
		this.id = id;
		this.requestURL = requestURL;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getConfirmatStatus() {
		return confirmatStatus;
	}

	public void setConfirmatStatus(String confirmatStatus) {
		this.confirmatStatus = confirmatStatus;
	}

	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	

}
