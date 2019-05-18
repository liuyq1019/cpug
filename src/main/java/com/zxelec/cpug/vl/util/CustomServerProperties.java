package com.zxelec.cpug.vl.util;

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
	
	//线程池维护线程的最少数量
	@Value("${cpug.core.poolSize}")
	private int cpugCorePoolSize;
	//#线程池维护线程的最大数量
	@Value("${cpug.core.poolSize}")
	private int cpugMaxPoolSize;
	//#缓存队列
	@Value("${cpug.queue.capacity}")
	private int cpugQueueCapacity;
	//#允许的空闲时间
	@Value("${cpug.keepAlive}")
	private int cpugKeepAlive;
	

	public int getCpugCorePoolSize() {
		return cpugCorePoolSize;
	}


	public void setCpugCorePoolSize(int cpugCorePoolSize) {
		this.cpugCorePoolSize = cpugCorePoolSize;
	}


	public int getCpugMaxPoolSize() {
		return cpugMaxPoolSize;
	}


	public void setCpugMaxPoolSize(int cpugMaxPoolSize) {
		this.cpugMaxPoolSize = cpugMaxPoolSize;
	}


	public int getCpugQueueCapacity() {
		return cpugQueueCapacity;
	}


	public void setCpugQueueCapacity(int cpugQueueCapacity) {
		this.cpugQueueCapacity = cpugQueueCapacity;
	}


	public int getCpugKeepAlive() {
		return cpugKeepAlive;
	}


	public void setCpugKeepAlive(int cpugKeepAlive) {
		this.cpugKeepAlive = cpugKeepAlive;
	}


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
