package com.zxelec.cpug.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Component;

import com.zxelec.cpug.entity.rest.CameraRows;

@Component
public class CameraCache {
	private Map<String, List<CameraRows>> allCamerData = new ConcurrentHashMap<String, List<CameraRows>>();
	
	private Map<String, CameraRows> camMap = new ConcurrentHashMap<String, CameraRows>();
	private Map<String, CameraRows> externalMap = new ConcurrentHashMap<String, CameraRows>();
	

	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	/**
	 * 存相机数据
	 * @param camList
	 */
	public void putCamera(List<CameraRows> camList) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			camMap.clear();
			allCamerData.clear();
			externalMap.clear();
			for (CameraRows cameraRows : camList) {
				camMap.put(cameraRows.getId(), cameraRows);
				externalMap.put(cameraRows.getExternalId(), cameraRows);
			}
			allCamerData.put("camList", camList);
		}finally{
			writeLock.unlock();
		}
	}
	/**
	 * 通过国标编号或者内部编号存入数据
	 * @param id 
	 * @return
	 */
//	public CameraRows getCamera(String id) {
//		Lock readLock = readWriteLock.readLock();
//		try {
//			readLock.lock();
//			CameraRows cam = camMap.get(id);//通过内部编号获取
//			if(cam == null) {//内部编号为null时使用国标获取
//				return externalMap.get(id);
//			}else {
//				return cam;
//			}
//		}finally{
//			readLock.unlock();
//		}
//	}
	/**
	 * 获取全量相机数据
	 * @return
	 */
	public List<CameraRows> getAllCameraList(){
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			return allCamerData.get("camList");
		}finally{
			readLock.unlock();
		}
	}
}
