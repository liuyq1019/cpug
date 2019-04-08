package com.zxelec.cpug.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Component;

import com.zxelec.cpug.entity.rest.DafaCamPushEntity;
import com.zxelec.cpug.entity.rest.DeviceList;
import com.zxelec.cpug.entity.rest.Subscribe;


@Component
public class SubscribeCache {
	
	
	private Map<String, Subscribe> subMap = new ConcurrentHashMap<String, Subscribe>();
	private Map<String, List<Subscribe>> allSubscribeData = new ConcurrentHashMap<String, List<Subscribe>>();
	
	private Map<String, DeviceList> camDeviceMap = new ConcurrentHashMap<String, DeviceList>();
	private Map<String, List<DeviceList>> camAllDeviceMap = new ConcurrentHashMap<String, List<DeviceList>>();
	
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	/**
	 * 将所有的订阅放入缓存
	 * @param subscribeReq
	 */
	public void putSubscribeInfo(List<Subscribe> subList) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			subMap.clear();
			allSubscribeData.clear();
			for (Subscribe subscribe : subList) {
				subMap.put(subscribe.getSubscribeID(), subscribe);
			}
			allSubscribeData.put("subscribeList", subList);
		}finally{
			writeLock.unlock();
		}
	}
	/**
	 * json device 数据
	 * @param subList
	 */
	public void putCamJson(List<DeviceList> devList) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			camDeviceMap.clear();
			for (DeviceList device : devList) {
				camDeviceMap.put(device.getDeviceID(), device);
			}
			camAllDeviceMap.put("camAllDevice", devList);
		}finally{
			writeLock.unlock();
		}
	}
	/**
	 * 获取设备全部的数据
	 * @return
	 */
	public Map<String,DeviceList> getAllCamDeviceMap(){
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			return camDeviceMap;
		}finally{
			readLock.unlock();
		}
	}
	
	/**
	 * 获取指定device信息
	 * @param subList
	 */
	public DeviceList getCamJson(String device) {
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			return camDeviceMap.get(device);
		}finally{
			readLock.unlock();
		}
	}
	/**
	 * 获取全部发送给大华Device信息
	 * @return
	 */
	public List<DeviceList> getAllCamJson(){
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			List<DeviceList> allList = camAllDeviceMap.get("camAllDevice");
			if(allList == null) {
				allList = new ArrayList<>();
			}
			return allList;
		}finally{
			readLock.unlock();
		}
	}
	
	
	/**
	 * 通过订阅标识符获取数据
	 * @param subscribeID
	 * @return
	 */
	public Subscribe getSubscribeInfo(String subscribeID) {
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			return subMap.get(subscribeID);
		}finally{
			readLock.unlock();
		}
	}
	/**
	 * 获取全部订阅信息
	 * @return Map对象 key SubscribeID
	 */
	public Map<String, Subscribe> getAllSubscribeInfo(){
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			return subMap;
		}finally{
			readLock.unlock();
		}
	}
	
	
	/**
	 * 获取所有的订阅信息
	 * @return
	 */
	public List<Subscribe> getAllSubscribeData(){
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			List<Subscribe> allList = allSubscribeData.get("subscribeList");
			if(allList == null) {
				allList = new ArrayList<>();
			}
			return allList;
		}finally{
			readLock.unlock();
		}
	}
	
	
	
}
