package com.zxelec.cpug.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Component;

import com.zxelec.cpug.entity.rest.DafaCamPushEntity;
import com.zxelec.cpug.entity.rest.Subscribe;

@Component
public class SubscribeCache {
	
	/**
	 * 订阅信息
	 */
	private Map<String, Subscribe> subMap = new ConcurrentHashMap<>();
	/**
	 * 发送给大华的设备信息
	 */
//	private Map<String, DafaCamPushEntity> camDeviceMap = new ConcurrentHashMap<>();
	
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	/**
	 * 将所有的订阅放入缓存
	 * 
	 * @param subscribeReq
	 */
	public void putSubscribeInfo(List<Subscribe> subList) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			subMap.clear();
			for (Subscribe subscribe : subList) {
				subMap.put(subscribe.getSubscribeID(), subscribe);
			}
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * 数据当个存入
	 * 
	 * @param key
	 *            getSubscribeID
	 * @param value
	 *            subscribe
	 */
	public void put(String key, Subscribe value) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			subMap.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * 通过订阅标识符获取数据
	 * 
	 * @param subscribeID
	 * @return
	 */
	public Subscribe getSubscribeInfo(String subscribeID) {
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			return subMap.get(subscribeID);
		} finally {
			readLock.unlock();
		}
	}
	
	/**
	 * 删除自定信息
	 * @param subscribeID
	 */
	public void removeSubscribe(String subscribeID) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			subMap.remove(subscribeID);
		} finally {
			writeLock.unlock();
		}
		
	}

	/**
	 * 获取全部订阅信息
	 * 
	 * @return Map对象 key SubscribeID
	 */
	public Map<String, Subscribe> getAllSubscribeInfo() {
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			return subMap;
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * 将map对象转换为list
	 * 
	 * @return
	 */
	public List<Subscribe> getAllSubscribeList() {
		Lock readLock = readWriteLock.readLock();
		List<Subscribe> list = new ArrayList<>();
		try {
			readLock.lock();
			for (Entry<String, Subscribe> entry : subMap.entrySet()) {
				list.add(entry.getValue());
			}
			return list;
		} finally {
			readLock.unlock();
		}
	}

	
	
//	/**
//	 * json device 数据
//	 * 
//	 * @param subList
//	 */
//	public void putCamJson(List<DafaCamPushEntity> devList) {
//		Lock writeLock = readWriteLock.writeLock();
//		try {
//			writeLock.lock();
//			camDeviceMap.clear();
//			for (DafaCamPushEntity dafaCamPushEntity : devList) {
//				camDeviceMap.put(dafaCamPushEntity.getSubscribeID(), dafaCamPushEntity);
//			}
//		} finally {
//			writeLock.unlock();
//		}
//	}
//
//	/**
//	 * 获取指定device信息
//	 * 
//	 * @param subList
//	 */
//	public DafaCamPushEntity getCamJson(String subscribeID) {
//		Lock readLock = readWriteLock.readLock();
//		try {
//			readLock.lock();
//			return camDeviceMap.get(subscribeID);
//		} finally {
//			readLock.unlock();
//		}
//	}
//
//	/**
//	 * 获取全部发送给大华Device信息
//	 * 
//	 * @return
//	 */
//	public Map<String, DafaCamPushEntity> getAllCamJson() {
//		Lock readLock = readWriteLock.readLock();
//		try {
//			readLock.lock();
//			return camDeviceMap;
//		} finally {
//			readLock.unlock();
//		}
//	}

}
