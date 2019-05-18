package com.zxelec.cpug.vl.entity.rest;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Subscribe implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9076699609606570020L;
	@JsonProperty(value = "SubscribeID")
	private String subscribeID;
	@JsonProperty(value = "Title")
	private String title;
	@JsonProperty(value = "SubscribeCategory")
	private String subscribeCategory;
	private String subscribeCategoryName;
	@JsonProperty(value = "SubscribeDetail")
	private String subscribeDetail;
	private String subscribeDetailName;
	@JsonProperty(value = "ResourceURI")
	private String resourceURI;
	private String resourceUriId;
	@JsonProperty(value = "AplicantName")
	private String aplicantName;
	@JsonProperty(value = "ApplicantOrg")
	private String applicantOrg;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonProperty(value = "StartTime")
	private Date startTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startTimeStr;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonProperty(value = "EndTime")
	private Date endTime;
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endTimeStr;
	@JsonProperty(value = "ReceiveAddr")
	private String receiveAddr;
	@JsonProperty(value = "SubscribeCancelPerson")
	private String subscribeCancelPerson;
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date cancelTimeStr;
	private String applicantOrgId;
	private String subscribeCancelOrgId;
	private String subscribeCancelPersonId;
	private String receiveAddrId;
	private String upSubscribeId;
	private int cancelFlag;//待定0 表示开启订阅 1表示取消订阅
	private String tokenURL;
	private String tUserName;
	private String tPassword;
	private int platType;
	private int model;
	private String callModel;
	private int status;
	@JsonProperty(value = "ReceiveAddrType")
	private String receiveAddrType;
	@JsonProperty(value = "SubscribeTopic")
	private String subscribeTopic;
	
	@JsonProperty(value = "Reason")
	private String reason; //理由
	@JsonProperty(value = "CancelReason")
	private String cancelReason; //取消原因
	
	
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
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
	public String getSubscribeCategory() {
		return subscribeCategory;
	}
	public void setSubscribeCategory(String subscribeCategory) {
		this.subscribeCategory = subscribeCategory;
	}
	public String getSubscribeCategoryName() {
		return subscribeCategoryName;
	}
	public void setSubscribeCategoryName(String subscribeCategoryName) {
		this.subscribeCategoryName = subscribeCategoryName;
	}
	public String getSubscribeDetail() {
		return subscribeDetail;
	}
	public void setSubscribeDetail(String subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}
	public String getSubscribeDetailName() {
		return subscribeDetailName;
	}
	public void setSubscribeDetailName(String subscribeDetailName) {
		this.subscribeDetailName = subscribeDetailName;
	}
	public String getResourceURI() {
		return resourceURI;
	}
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}
	public String getResourceUriId() {
		return resourceUriId;
	}
	public void setResourceUriId(String resourceUriId) {
		this.resourceUriId = resourceUriId;
	}
	public String getAplicantName() {
		return aplicantName;
	}
	public void setAplicantName(String aplicantName) {
		this.aplicantName = aplicantName;
	}
	public String getApplicantOrg() {
		return applicantOrg;
	}
	public void setApplicantOrg(String applicantOrg) {
		this.applicantOrg = applicantOrg;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(Date startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(Date endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public String getReceiveAddr() {
		return receiveAddr;
	}
	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}
	public String getSubscribeCancelPerson() {
		return subscribeCancelPerson;
	}
	public void setSubscribeCancelPerson(String subscribeCancelPerson) {
		this.subscribeCancelPerson = subscribeCancelPerson;
	}
	public Date getCancelTimeStr() {
		return cancelTimeStr;
	}
	public void setCancelTimeStr(Date cancelTimeStr) {
		this.cancelTimeStr = cancelTimeStr;
	}
	public String getApplicantOrgId() {
		return applicantOrgId;
	}
	public void setApplicantOrgId(String applicantOrgId) {
		this.applicantOrgId = applicantOrgId;
	}
	public String getSubscribeCancelOrgId() {
		return subscribeCancelOrgId;
	}
	public void setSubscribeCancelOrgId(String subscribeCancelOrgId) {
		this.subscribeCancelOrgId = subscribeCancelOrgId;
	}
	public String getSubscribeCancelPersonId() {
		return subscribeCancelPersonId;
	}
	public void setSubscribeCancelPersonId(String subscribeCancelPersonId) {
		this.subscribeCancelPersonId = subscribeCancelPersonId;
	}
	public String getReceiveAddrId() {
		return receiveAddrId;
	}
	public void setReceiveAddrId(String receiveAddrId) {
		this.receiveAddrId = receiveAddrId;
	}
	public String getUpSubscribeId() {
		return upSubscribeId;
	}
	public void setUpSubscribeId(String upSubscribeId) {
		this.upSubscribeId = upSubscribeId;
	}
	public int getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getTokenURL() {
		return tokenURL;
	}
	public void setTokenURL(String tokenURL) {
		this.tokenURL = tokenURL;
	}
	public String gettUserName() {
		return tUserName;
	}
	public void settUserName(String tUserName) {
		this.tUserName = tUserName;
	}
	public String gettPassword() {
		return tPassword;
	}
	public void settPassword(String tPassword) {
		this.tPassword = tPassword;
	}
	public int getPlatType() {
		return platType;
	}
	public void setPlatType(int platType) {
		this.platType = platType;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public String getCallModel() {
		return callModel;
	}
	public void setCallModel(String callModel) {
		this.callModel = callModel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReceiveAddrType() {
		return receiveAddrType;
	}
	public void setReceiveAddrType(String receiveAddrType) {
		this.receiveAddrType = receiveAddrType;
	}
	public String getSubscribeTopic() {
		return subscribeTopic;
	}
	public void setSubscribeTopic(String subscribeTopic) {
		this.subscribeTopic = subscribeTopic;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
