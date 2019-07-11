package com.zxelec.cpug.entity.rest;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscribeList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2890088893461045540L;
	@JsonProperty(value = "Subscribe")
	private List<Subscribe> subscribe;

	public List<Subscribe> getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(List<Subscribe> subscribe) {
		this.subscribe = subscribe;
	}
}
