package com.zxelec.cpug.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@PropertySource(value= {"classpath:server.properties"},
encoding="UTF-8")
@Component
public class CustomServerProperties {
	
	@Value("${cpug.device.id}")
	private String cpugDeviceId;
	
	@Value("${cpug.device.passwrod}")
	private String cpugDevicePasswrod;
	
	@Value("${cpug.domain.id}")
	private String cpugDomainId;
	
	@Value("${cpug.dafa.prefix.address}")
	private String cpugDafaPrefixAddress;
	
	@Value("${cpug.dafa.suffix.address}")
	private String  cpugDafaSuffixAddress;
	
	@Value("${cpug.dafa.imgUrl}")
	private String cpugDafaImgUrl;
	
	@Value("${cpug.ip}")
	private String cpugIp;
	@Value("${cpug.lunId}")
	private String cpugLunId;
	
	@Value("${cpug.dafa.username}")
	private String cpugDafaUsername;
	
	@Value("${cpug.dafa.password}")
	private String cpugDafaPassword;
	
	
	@Value("${cpug.cpbs.url}")
	private String cpugCpbsUrl;
	
	@Value("${cpug.viss.username}")
	private String cpugVissUsername;
	
	@Value("${cpug.viss.password}")
	private String cpugVissPassword;
	
	public String getCpugLunId() {
		return cpugLunId;
	}


	public void setCpugLunId(String cpugLunId) {
		this.cpugLunId = cpugLunId;
	}


	public String getCpugCpbsUrl() {
		return cpugCpbsUrl;
	}


	public void setCpugCpbsUrl(String cpugCpbsUrl) {
		this.cpugCpbsUrl = cpugCpbsUrl;
	}


	public String getCpugVissUsername() {
		return cpugVissUsername;
	}


	public void setCpugVissUsername(String cpugVissUsername) {
		this.cpugVissUsername = cpugVissUsername;
	}


	public String getCpugVissPassword() {
		return cpugVissPassword;
	}


	public void setCpugVissPassword(String cpugVissPassword) {
		this.cpugVissPassword = cpugVissPassword;
	}


	@Override
	public String toString() {
		return "CustomServerProperties [cpugDeviceId=" + cpugDeviceId + ", cpugDevicePasswrod=" + cpugDevicePasswrod
				+ ", cpugDomainId=" + cpugDomainId + ", cpugDafaPrefixAddress=" + cpugDafaPrefixAddress
				+ ", cpugDafaSuffixAddress=" + cpugDafaSuffixAddress + ", cpugDafaUsername=" + cpugDafaUsername
				+ ", cpugDafaPassword=" + cpugDafaPassword + "]";
	}
	
	
	public String getCpugIp() {
		return cpugIp;
	}


	public void setCpugIp(String cpugIp) {
		this.cpugIp = cpugIp;
	}


	public String getCpugDafaImgUrl() {
		return cpugDafaImgUrl;
	}


	public void setCpugDafaImgUrl(String cpugDafaImgUrl) {
		this.cpugDafaImgUrl = cpugDafaImgUrl;
	}


	public String getCpugDeviceId() {
		return cpugDeviceId;
	}
	public void setCpugDeviceId(String cpugDeviceId) {
		this.cpugDeviceId = cpugDeviceId;
	}
	public String getCpugDevicePasswrod() {
		return cpugDevicePasswrod;
	}
	public void setCpugDevicePasswrod(String cpugDevicePasswrod) {
		this.cpugDevicePasswrod = cpugDevicePasswrod;
	}
	public String getCpugDomainId() {
		return cpugDomainId;
	}
	public void setCpugDomainId(String cpugDomainId) {
		this.cpugDomainId = cpugDomainId;
	}
	public String getCpugDafaUsername() {
		return cpugDafaUsername;
	}
	public void setCpugDafaUsername(String cpugDafaUsername) {
		this.cpugDafaUsername = cpugDafaUsername;
	}
	public String getCpugDafaPassword() {
		return cpugDafaPassword;
	}
	public void setCpugDafaPassword(String cpugDafaPassword) {
		this.cpugDafaPassword = cpugDafaPassword;
	}
	public String getCpugDafaPrefixAddress() {
		return cpugDafaPrefixAddress;
	}
	public void setCpugDafaPrefixAddress(String cpugDafaPrefixAddress) {
		this.cpugDafaPrefixAddress = cpugDafaPrefixAddress;
	}
	public String getCpugDafaSuffixAddress() {
		return cpugDafaSuffixAddress;
	}
	public void setCpugDafaSuffixAddress(String cpugDafaSuffixAddress) {
		this.cpugDafaSuffixAddress = cpugDafaSuffixAddress;
	}
	
}
