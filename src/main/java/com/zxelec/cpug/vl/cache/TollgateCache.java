package com.zxelec.cpug.vl.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Component;

import com.zxelec.cpug.vl.entity.rest.TollgateRows;

@Component
public class TollgateCache {
	private Map<String, List<TollgateRows>> allTollgateData = new ConcurrentHashMap<>();
	private Map<String, TollgateRows> tollgateMap = new ConcurrentHashMap<>();
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	/**
	 * 存数据
	 * @param tollgateList
	 */
	public void put(List<TollgateRows> tollgateList) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			allTollgateData.clear();
			tollgateMap.clear();
			for (TollgateRows tollgate : tollgateList) {
				tollgateMap.put(tollgate.getId(), tollgate);
			}
			allTollgateData.put("allTollgateData", tollgateList);
		}finally{
			writeLock.unlock();
		}
	}
	
	/**
	 * 通过卡口ID获取对象
	 * @param tollgateId
	 * @return
	 */
	public TollgateRows get(String tollgateId) {
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			return tollgateMap.get(tollgateId);
		}finally{
			readLock.unlock();
		}
	}
	
	public List<TollgateRows> getAll() {
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			List<TollgateRows> list = allTollgateData.get("allTollgateData");
			if(list == null) {
				list = new ArrayList<>();
			}
			return list;
		}finally{
			readLock.unlock();
		}
	}
}
