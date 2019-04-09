package com.zxelec.cpug.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.cache.CameraCache;
import com.zxelec.cpug.cache.SubscribeCache;
import com.zxelec.cpug.cache.TollgateCache;
import com.zxelec.cpug.entity.MotionVehicleType;
import com.zxelec.cpug.entity.rest.CameraRows;
import com.zxelec.cpug.entity.rest.CarpassPushEntity;
import com.zxelec.cpug.entity.rest.DafaCamPushEntity;
import com.zxelec.cpug.entity.rest.DafaLanePushEntity;
import com.zxelec.cpug.entity.rest.DafaTollgatePushEntity;
import com.zxelec.cpug.entity.rest.DeviceList;
import com.zxelec.cpug.entity.rest.ImagePicLoad;
import com.zxelec.cpug.entity.rest.LaneList;
import com.zxelec.cpug.entity.rest.Lanes;
import com.zxelec.cpug.entity.rest.Subscribe;
import com.zxelec.cpug.entity.rest.TollgateList;
import com.zxelec.cpug.entity.rest.TollgateRows;
import com.zxelec.cpug.util.CustomServerProperties;
import com.zxelec.cpug.util.DateUtils;
import com.zxelec.cpug.util.RestDigestClient;

@Service
public class DahuaCarpassPushService {
	
	private Logger logger = LogManager.getLogger(DahuaCarpassPushService.class);

	@Autowired
	private SubscribeCache subscribeCache;

	@Autowired
	private CameraCache cameraCache;
	
	@Autowired
	private TollgateCache tollgateCache;

	@Autowired
	private RestDigestClient restDigestClient;

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	@Autowired
	private CustomServerProperties customServerProperties;
	
	/**
	 * 设备CAM订阅
	 * @param deviceSubscribe
	 */
	@Async("asyncServiceExecutor")
	public void deviceSubscribe(List<Subscribe> deviceSubscribe) {
		logger.info("设备【cam】订阅start");
		for (Subscribe subscribe : deviceSubscribe) {
			subscribeCache.put(subscribe.getSubscribeID(), subscribe);
		}
		List<Subscribe> writeSub = subscribeCache.getAllSubscribeList();
		this.writeSubscribeJson(writeSub);
		//0表示开启订阅
		List<Subscribe> cancelList = deviceSubscribe.stream()
													.filter(c -> 0 == c.getCancelFlag())
													.collect(Collectors.toList());
		if(null != cancelList && cancelList.size()>0) {
			this.sendCamDahua(deviceSubscribe);//不管新增和修改都需要发送
		}
		logger.info("设备【cam】订阅   end。。。");
	}
	/**
	 * 过车记录订阅
	 */
	@Async("asyncServiceExecutor")
	public void carSubscribe(List<Subscribe> carSubscribe) {
		logger.info("过车记录【car】订阅start");
		// 获取所有的信息
		for (Subscribe subscribe : carSubscribe) {
			subscribeCache.put(subscribe.getSubscribeID(), subscribe);
		}
		List<Subscribe> writeSub = subscribeCache.getAllSubscribeList();
		this.writeSubscribeJson(writeSub);
		logger.info("过车记录【car】订阅   end。。。");
	}
	
	
	/**
	 * 卡口订阅推送接口
	 * @param tollgetSubscribe
	 */
	public void tollgateSubscribe(List<Subscribe> tollgetSubscribe) {
		logger.info("卡口信息【Tollgate】订阅start");
		// 获取所有的信息
		for (Subscribe subscribe : tollgetSubscribe) {
			subscribeCache.put(subscribe.getSubscribeID(), subscribe);
		}
		List<Subscribe> writeSub = subscribeCache.getAllSubscribeList();
		this.writeSubscribeJson(writeSub);
		this.sendTollgateDahua(tollgetSubscribe);//不管新增和修改都需要发送		
		logger.info("卡口信息【Tollgate】订阅   end。。。");
	}
	
	/**
	 * 车道订阅推送接口
	 * @param 车道推送对象
	 */
	public void laneSubscribe(List<Subscribe> laneSubscribe) {
		logger.info("车道信息【Lane】订阅start");
		// 获取所有的信息
		for (Subscribe subscribe : laneSubscribe) {
			subscribeCache.put(subscribe.getSubscribeID(), subscribe);
		}
		List<Subscribe> writeSub = subscribeCache.getAllSubscribeList();
		this.writeSubscribeJson(writeSub);
		this.sendLaneDahua(laneSubscribe);//不管新增和修改都需要发送		
		logger.info("车道信息【Lane】订阅   end。。。");
	}
	/**
	 * 删除订阅
	 * @param subscribeID 订阅ID
	 */
	public void delSubscribe(String subscribeID) {
		subscribeCache.removeSubscribe(subscribeID);//删除缓存后更新文件
		List<Subscribe> writeSub = subscribeCache.getAllSubscribeList();
		this.writeSubscribeJson(writeSub);
	}
	
	
	/**
	 * 发送卡口数据
	 * @param subList
	 */
	public void sendLaneDahua(List<Subscribe> subList) {
		// 判断此订阅ID是否已经发送给过点位信息，
		// 循环发送点位信息
		if (subList.size() > 0) {
			//需要发送的数据源
			List<TollgateRows> tollgateRows = tollgateCache.getAll();
			List<DafaLanePushEntity> laneList = this.encapsulatioDafaLanePushEntity(subList,tollgateRows);
			if (!laneList.isEmpty()) {
				//只要是新订阅则就需要发送
				restDigestClient.sendNoDigestDafaLane(laneList);
			}
		}else {
			logger.info("没有订阅。。。。。");
		}
	}
	
	/**
	 * 发送卡口数据
	 * @param subList
	 */
	public void sendTollgateDahua(List<Subscribe> subList) {
		// 判断此订阅ID是否已经发送给过点位信息，
		// 循环发送点位信息
		if (subList.size() > 0) {
			//需要发送的数据源
			List<TollgateRows> tollgateRows = tollgateCache.getAll();
			List<DafaTollgatePushEntity> tollgateList = this.encapsulatioDafaTollgatePushEntity(subList,tollgateRows);
			if (!tollgateList.isEmpty()) {
				//只要是新订阅则就需要发送
				restDigestClient.sendNoDigestDafaTollgate(tollgateList);
			}
		}else {
			logger.info("没有订阅。。。。。");
		}
	}

	/**
	 * 发送设备信息到大华服务器
	 * @param subList 发送给cam 订阅的对象信息
	 */
	public void sendCamDahua(List<Subscribe> subList) {
		// 判断此订阅ID是否已经发送给过点位信息，
		// 循环发送点位信息
		if (subList.size() > 0) {
			//需要发送的数据源
			List<CameraRows> camRows = cameraCache.getAllCameraList();
			List<DafaCamPushEntity> listCamDevice = this.encapsulatioDafaCamPushEntity(subList,camRows);
			if (!listCamDevice.isEmpty()) {
				//只要是新订阅则就需要发送
				restDigestClient.sendNoDigestDafaCam(listCamDevice);
			}
		}else {
			logger.info("没有订阅。。。。。");
		}

	}
	
	/**
	 * 写入订阅信息
	 * @param writeList
	 */
	private void writeSubscribeJson(List<Subscribe> writeList) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			String jsonStr = JSONObject.toJSONString(writeList);
			File paths = new File(ResourceUtils.getURL("classpath:").getPath());
			Path rootLocation = Paths.get(paths.getPath() + "/dahua");
			if (Files.notExists(rootLocation)) {
				Files.createDirectories(rootLocation);
			}
			Path path = rootLocation.resolve("data.json");
			if (!StringUtils.isEmpty(jsonStr)) {
				byte[] strToBytes = jsonStr.getBytes(Charset.defaultCharset());
				Files.write(path, strToBytes);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			writeLock.unlock();
		}
	}
	
	/**
	 * 发送大华图片
	 */
	public String sendDahuaImg(ImagePicLoad imgLoad) {
		return restDigestClient.sendNoDigestImg(imgLoad);
	}

	/**
	 * 发送过车记录
	 */
	@Async("asyncServiceExecutor")
	public void sendDahuaCar(MotionVehicleType motionVehicle,List<Subscribe> subscribeList) {
		List<CarpassPushEntity> datfCar = this.encapsulationDafaCarPushReq(motionVehicle);
		restDigestClient.sendNoDigestDafaCar(datfCar,subscribeList);
	}

	
	/**
	 * 封装发送车道信息
	 * 
	 * @param list
	 */
	private List<DafaLanePushEntity> encapsulatioDafaLanePushEntity(List<Subscribe> subList,List<TollgateRows> tollgateRows) {
		List<LaneList> sendLaneList = new ArrayList<>();
		/**
		 * 封装需要发送的设备
		 */
		if (tollgateRows != null && tollgateRows.size() > 0) {// 必须要有相机数据才能发送
			for (TollgateRows t : tollgateRows) {
				List<Lanes> lanes = t.getLanes();
				for (Lanes lane : lanes) {
					LaneList laneList = new LaneList();
					laneList.setTollgateID(t.getId());
					laneList.setLaneId(lane.getLaneId());
					laneList.setLaneNo(lane.getLaneNo());
					laneList.setName(lane.getName());
					laneList.setDirection(lane.getDirection());
					laneList.setCityPass(lane.getCityPass());
					laneList.setDeviceID(lane.getApeID());
					sendLaneList.add(laneList);
				}
				
			}
		}
		List<DafaLanePushEntity> sendList = new ArrayList<>();
		//需要发送多少个地址
		for (Subscribe subscribe : subList) {
			DafaLanePushEntity entity = new DafaLanePushEntity();
			String notificationID = customServerProperties.getCpugDomainId() + DateUtils.data2String();
			entity.setNotificationID(notificationID);
			entity.setSubscribeID(subscribe.getSubscribeID());
			entity.setTitle(subscribe.getTitle());
			entity.setTriggerTime(new Date());
			entity.setLaneList(sendLaneList);
			sendList.add(entity);
		}
		return sendList;
	}
	
	/**
	 * 封装发送卡口信息
	 * 
	 * @param list
	 */
	private List<DafaTollgatePushEntity> encapsulatioDafaTollgatePushEntity(List<Subscribe> subList,List<TollgateRows> tollgateRows) {
		List<TollgateList> tollgateList = new ArrayList<>();
		/**
		 * 封装需要发送的设备
		 */
		if (tollgateRows != null && tollgateRows.size() > 0) {// 必须要有相机数据才能发送
			for (TollgateRows t : tollgateRows) {
				TollgateList tollgate = new TollgateList();
				tollgate.setTollgateID(t.getId());
				tollgate.setName(t.getName());
				tollgate.setLongtidude(t.getLongitude());
				tollgate.setLatitude(t.getLatitude());
				tollgate.setPlaceCode(t.getPlaceCode());
				tollgate.setPlace(t.getPlace());
				tollgate.setStatus(t.getStatus());
				try {
					if(StringUtils.isEmpty(t.getTollgateType())) {
						tollgate.setTollgateType(80);
					}else {
						tollgate.setTollgateType(Integer.parseInt(t.getTollgateType()));
					}
				}catch (RuntimeException e) {
					tollgate.setTollgateType(80);
				}
				tollgate.setLaneNum(t.getLaneNum());
				tollgate.setOrgCode(t.getOrgCode());
				tollgate.setActiveTime(t.getActiveTime());
				tollgateList.add(tollgate);
			}
		}
		List<DafaTollgatePushEntity> sendList = new ArrayList<>();
		//需要发送多少个地址
		for (Subscribe subscribe : subList) {
			DafaTollgatePushEntity entity = new DafaTollgatePushEntity();
			String notificationID = customServerProperties.getCpugDomainId() + DateUtils.data2String();
			entity.setNotificationID(notificationID);
			entity.setSubscribeID(subscribe.getSubscribeID());
			entity.setTitle(subscribe.getTitle());
			entity.setTriggerTime(new Date());
			entity.setTollgateList(tollgateList);
			sendList.add(entity);
		}
		return sendList;
	}
	
	
	/**
	 * 封装发送设备信息
	 * 
	 * @param list
	 */
	private List<DafaCamPushEntity> encapsulatioDafaCamPushEntity(List<Subscribe> subList,List<CameraRows> camRows) {
		List<DeviceList> deviceList = new ArrayList<>();
		/**
		 * 封装需要发送的设备
		 */
		if (camRows != null && camRows.size() > 0) {// 必须要有相机数据才能发送
			for (CameraRows cameraRows : camRows) {
				DeviceList device = new DeviceList();
				device.setDeviceID(cameraRows.getExternalId());
				device.setName(cameraRows.getName());
				device.setModel(StringUtils.isEmpty(cameraRows.getModel())?"未知":cameraRows.getModel());
				device.setiPAddr("1.1.1.1");// ip地址
				device.setLongtidude(cameraRows.getLongitude());
				device.setLatitude(cameraRows.getLatitude());
				device.setPlaceCode("4320");// 行政区代码
				device.setPlace(cameraRows.getName());
				deviceList.add(device);
			}
		}
		List<DafaCamPushEntity> sendList = new ArrayList<>();
		//需要发送多少个地址
		for (Subscribe subscribe : subList) {
			DafaCamPushEntity entity = new DafaCamPushEntity();
			String notificationID = customServerProperties.getCpugDomainId() + DateUtils.data2String();
			entity.setNotificationID(notificationID);
			entity.setSubscribeID(subscribe.getSubscribeID());
			entity.setTitle(subscribe.getTitle());
			entity.setTriggerTime(new Date());
			entity.setDeviceList(deviceList);
			sendList.add(entity);
		}
		return sendList;
	}

	/**
	 * 大华记录对象封装
	 * 
	 * @param motionVehicle
	 * @return
	 */
	private List<CarpassPushEntity> encapsulationDafaCarPushReq(MotionVehicleType motionVehicleType) {
		List<CarpassPushEntity> motorVehicleObjectList = new ArrayList<>();
		// for (MotionVehicleType motionVehicleType : motionVehicle) {
		CarpassPushEntity pushEntity = new CarpassPushEntity();
		pushEntity.setMotorVehicleID(motionVehicleType.getMotorVehicleID());
		pushEntity.setSourceID(motionVehicleType.getTollgateID());
		pushEntity.setTransportID(motionVehicleType.getTollgateID());
		pushEntity.setDeviceID(motionVehicleType.getDeviceID());
		pushEntity.setStorageUrl1(motionVehicleType.getStorageUrl1());// 发送图片后获取到
		pushEntity.setStorageUrl2(motionVehicleType.getStorageUrl2());//// 发送图片后获取到
		pushEntity.setStorageUrl3(motionVehicleType.getStorageUrl3());//// 发送图片后获取到
		pushEntity.setStorageUrl4(motionVehicleType.getStorageUrl4());//// 发送图片后获取到
		pushEntity.setStorageUrl5(motionVehicleType.getStorageUrl5());//// 发送图片后获取到
		pushEntity.setLeftTopX(0);// 左上角X坐标
		pushEntity.setLeftTopY(0);// 左上角Y坐标
		pushEntity.setRightBtmX(0);// 右下角X坐标
		pushEntity.setRightBtmY(0);// 右下角Y坐标
		if(StringUtils.isEmpty(motionVehicleType.getPlateClass())) {//如果为null 复制为99
			pushEntity.setPlateClass("99");// 号牌种类
		}else {
			pushEntity.setPlateClass(motionVehicleType.getPlateClass());// 号牌种类
		}
		pushEntity.setPlateColor(motionVehicleType.getPlateColor());// 车牌颜色
		pushEntity.setPlateNo(motionVehicleType.getPlateNo());
		pushEntity.setSpeed(motionVehicleType.getSpeed());// 行驶速度
		pushEntity.setDirection(motionVehicleType.getDirection());// 方向
		pushEntity.setVehicleClass(vehicleClassTransformation(motionVehicleType.getVehicleClass()));// 车辆类型
		pushEntity.setVehicleBrand(motionVehicleType.getVehicleBrand());
		if (!StringUtils.isEmpty(motionVehicleType.getVehicleLength())) {
			try {
				int vehicleLength = Integer.parseInt(motionVehicleType.getVehicleLength());
				if(vehicleLength>=0) {
					pushEntity.setVehicleLength(vehicleLength);
				}
			} catch (RuntimeException e) {
				logger.error("数据类型转换失败:" + motionVehicleType.getVehicleLength());
			}
		}
		if(motionVehicleType.getVehicleWidth()>=0) {
			pushEntity.setVehicleWidth(motionVehicleType.getVehicleWidth());
		}
		if(motionVehicleType.getVehicleHeight()>=0) {
			pushEntity.setVehicleHeight(motionVehicleType.getVehicleHeight());
		}
		try {
			Integer vColor = Integer.parseInt(motionVehicleType.getVehicleColor());
			pushEntity.setVehicleColor(vColor+"");
		}catch (RuntimeException e) {
			logger.error("车辆颜色类型转换失败:"+motionVehicleType.getVehicleColor());
		}
		if(motionVehicleType.getVehicleColorDepth()>=0) {
			pushEntity.setVehicleColorDepth(motionVehicleType.getVehicleColorDepth() + "");
		}else {
			pushEntity.setVehicleColorDepth("0");
		}
		pushEntity.setPassTime(motionVehicleType.getPassTime());// 过车时间
		pushEntity.setLaneId(motionVehicleType.getLaneNo());// 车道号
		pushEntity.setPlaceCode(motionVehicleType.getNameOfPassedRoad());// 安装地点行政区划代码
		motorVehicleObjectList.add(pushEntity);
		return motorVehicleObjectList;
	}
	
	
	private List<String> daList = Arrays.asList("K10","K11","K12","K13","K14","K15",
			"K16","K17","H10","H11","H12","H13","H14","H15","H16","H17","H18","H19","Q10","Q11","Q12","Z11","Z51"
			,"T11","J11","J12","J13","G10","G11","G12","G13","G14","G15","G16","G17","G18","G19","B10","B11","B12",
			"B13","B14","B15","B16","B17","B18","B19","B1A","B1B");
	private	List<String> zxList = Arrays.asList("K20","K21","K22","K23","K24","K25","K27","H20",
			"H21","H22","H23","H24","H25","H26","H27","H28","H29","Q20","Q21","Q22","Z21","G20","G21","G22",
			"G23","G24","G25","G26","G27","G28","G29","B20","B21","B22","B23","B24","B25","B26","B27","B28","B29");
	private	List<String> xxList = Arrays.asList("K30","K31","K32","K33","K34","K40","K41","K42","K43","H30","H31","H32","H33","H34","H35","H37","H38","H39","H40","H41","H42","H43","H44","H45","H46","H47","H50","Q30","Q31","Q32","Z31","Z41","Z71","T20","T21","T22","T23","G30","G31","G32","G33","G34","G35","G36","G37","G38","B2A","B2B","B30","B31","B32","B33","B34","B35","B36","B37","B38","B39");
	private	List<String> qtList = Arrays.asList("X99","D12","D11");
	private	List<String> eList = Arrays.asList("M11","M12","M20","M21","M22");
	private	List<String> sList = Arrays.asList("M10","M13","M14","M15","N11");
	private	List<String> xhcList = Arrays.asList("H51","H52","H53","H54","H55");
	/**
	 * 车辆类型转换
	 * @param vehicleClass
	 * @return
	 */
	private String vehicleClassTransformation(String vehicleClass) {
		if(daList.contains(vehicleClass)) {
			return "3";
		}
		if(zxList.contains(vehicleClass)) {
			return "2";
		}
		if(xxList.contains(vehicleClass)) {
			return "1";
		}
		if(qtList.contains(vehicleClass)) {
			return "4";
		}
		if(eList.contains(vehicleClass)) {
			return "6";
		}
		if(sList.contains(vehicleClass)) {
			return "7";
		}
		if(xhcList.contains(vehicleClass)) {
			return "14";
		}
		return "0";
	}

	@Async("asyncServiceExecutor")
	public void executeAsync() {
		logger.info("start executeAsync");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("end executeAsync");
	}
	
	
//	/**
//	 * 大华发起订阅后进行后续逻辑处理
//	 * 
//	 * @param dahuaSubscribeReq
//	 * @return
//	 */
//	@Async("asyncServiceExecutor")
//	@Deprecated
//	public void resultSubscribeInfo(DahuaSubscribeReq dahuaSubscribeReq) {
//		logger.info("异步处理设备信息开始。。。。。");
//		/**
//		 * 将订阅信息写入文件
//		 */
//		List<Subscribe> sendCamSub = new ArrayList<>();// 存储需要发送订阅的点位信息
//		// 获取所有的信息
//		List<Subscribe> allSubList = subscribeCache.getAllSubscribeData();
//		if (allSubList == null) {
//			allSubList = new ArrayList<Subscribe>();
//		}
//		
//		List<Subscribe> listSb = dahuaSubscribeReq.getSubscribeList().getSubscribe();
//		for (Subscribe subscribe : listSb) {
//			if (null == subscribeCache.getSubscribeInfo(subscribe.getSubscribeID())) {
//				allSubList.add(subscribe);
//				// 没有添加过的点位信息
//				sendCamSub.add(subscribe);
//			}
//		}
//		this.writeSubscribeJson(allSubList);
//		subscribeCache.putSubscribeInfo(allSubList);
//		this.sendCamDahu(sendCamSub);
//		logger.info("异步推送设备信息结束。。。。。");
//	}
//	/**
//	 * 
//	 * @param id 需要取消ID
//	 * @param subscribe 需要取消的对象 
//	 */
//	@Deprecated
//	public void cancelSubscribe(String id,Subscribe subscribe) {
//		List<Subscribe> writeList = new ArrayList<>();
//		// 获取订阅所有的信息
//		List<Subscribe> allList = subscribeCache.getAllSubscribeData();
//		//排除不需要取消的订阅信息
//		List<Subscribe> noCancelList = allList.stream().
//				filter(c -> !id.equals(c.getSubscribeID())).collect(Collectors.toList());
//		writeList.addAll(noCancelList);
//		writeList.add(subscribe);
//		this.writeSubscribeJson(writeList);
//		subscribeCache.putSubscribeInfo(writeList);
//	}
//	
//	/**
//	 * 修改订阅
//	 * @param dahuaSubscribeReq 待修改的集合对象
//	 */
//	@Deprecated
//	@Async("asyncServiceExecutor")
//	public void updateSubscribe(DahuaSubscribeReq dahuaSubscribeReq) {
//		logger.info("取消订阅开始。。。。。");
//		/**
//		 * 将订阅信息写入文件
//		 */
//		List<Subscribe> writeList = new ArrayList<>();
//		// 获取订阅所有的信息
//		List<Subscribe> allList = subscribeCache.getAllSubscribeData();
//		// 获取请求对象的订阅信息
//		List<Subscribe> cancelList = dahuaSubscribeReq.getSubscribeList().getSubscribe();
//		if(cancelList !=null && cancelList.size()>0) {//需要修改的内容
//			//需要修改订阅的对象数据
//			Map<String,Subscribe> cancelMap = cancelList.stream().
//					collect(Collectors.toMap(Subscribe::getSubscribeID, Function.identity()));
//			//没有修改订阅的对象
//			List<Subscribe> noCancelList = allList.stream().
//					filter(c -> !cancelMap.containsKey(c.getSubscribeID())).collect(Collectors.toList());
//			writeList.addAll(cancelList);
//			writeList.addAll(noCancelList);
//			this.writeSubscribeJson(writeList);
//			subscribeCache.putSubscribeInfo(writeList);
//		}
//		logger.info("取消订阅结束。。。。。");
//	}
}
