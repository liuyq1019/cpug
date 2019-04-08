package com.zxelec.cpug.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
import com.zxelec.cpug.entity.MotionVehicleType;
import com.zxelec.cpug.entity.rest.CameraRows;
import com.zxelec.cpug.entity.rest.CarpassPushEntity;
import com.zxelec.cpug.entity.rest.DahuaSubscribeReq;
import com.zxelec.cpug.entity.rest.DeviceList;
import com.zxelec.cpug.entity.rest.ImagePicLoad;
import com.zxelec.cpug.entity.rest.Subscribe;
import com.zxelec.cpug.util.RestDigestClient;

@Service
public class DafaCarpassPushService {

	@Autowired
	private SubscribeCache subscribeCache;

	@Autowired
	private CameraCache cameraCache;

	@Autowired
	private RestDigestClient restDigestClient;

	private Logger logger = LogManager.getLogger(DafaCarpassPushService.class);

	/**
	 * 大华发起订阅后进行后续逻辑处理
	 * 
	 * @param dahuaSubscribeReq
	 * @return
	 */
	@Async("asyncServiceExecutor")
	public void resultSubscribeInfo(DahuaSubscribeReq dahuaSubscribeReq) {
		logger.info("异步处理设备信息开始。。。。。");
		/**
		 * 将订阅信息写入文件
		 */
		List<Subscribe> sendCamSub = new ArrayList<>();// 存储需要发送订阅的点位信息
		// 获取所有的信息
		List<Subscribe> allSubList = subscribeCache.getAllSubscribeData();
		if (allSubList == null) {
			allSubList = new ArrayList<Subscribe>();
		}
		
		List<Subscribe> listSb = dahuaSubscribeReq.getSubscribeList().getSubscribe();
		for (Subscribe subscribe : listSb) {
			if (null == subscribeCache.getSubscribeInfo(subscribe.getSubscribeID())) {
				allSubList.add(subscribe);
				// 没有添加过的点位信息
				sendCamSub.add(subscribe);
			}
		}
		this.writeSubscribeJson(allSubList);
		subscribeCache.putSubscribeInfo(allSubList);
		this.sendCamDahu(sendCamSub);
		logger.info("异步推送设备信息结束。。。。。");
	}
	
	/**
	 * 
	 * @param id 需要取消ID
	 * @param subscribe 需要取消的对象 
	 */
	public void cancelSubscribe(String id,Subscribe subscribe) {
		List<Subscribe> writeList = new ArrayList<>();
		// 获取订阅所有的信息
		List<Subscribe> allList = subscribeCache.getAllSubscribeData();
		//排除不需要取消的订阅信息
		List<Subscribe> noCancelList = allList.stream().
				filter(c -> !id.equals(c.getSubscribeID())).collect(Collectors.toList());
		writeList.addAll(noCancelList);
		writeList.add(subscribe);
		this.writeSubscribeJson(writeList);
		subscribeCache.putSubscribeInfo(writeList);
	}
	
	/**
	 * 修改订阅
	 * @param dahuaSubscribeReq 待修改的集合对象
	 */
	@Async("asyncServiceExecutor")
	public void updateSubscribe(DahuaSubscribeReq dahuaSubscribeReq) {
		logger.info("取消订阅开始。。。。。");
		/**
		 * 将订阅信息写入文件
		 */
		List<Subscribe> writeList = new ArrayList<>();
		// 获取订阅所有的信息
		List<Subscribe> allList = subscribeCache.getAllSubscribeData();
		// 获取请求对象的订阅信息
		List<Subscribe> cancelList = dahuaSubscribeReq.getSubscribeList().getSubscribe();
		if(cancelList !=null && cancelList.size()>0) {//需要修改的内容
			//需要修改订阅的对象数据
			Map<String,Subscribe> cancelMap = cancelList.stream().
					collect(Collectors.toMap(Subscribe::getSubscribeID, Function.identity()));
			//没有修改订阅的对象
			List<Subscribe> noCancelList = allList.stream().
					filter(c -> !cancelMap.containsKey(c.getSubscribeID())).collect(Collectors.toList());
			writeList.addAll(cancelList);
			writeList.addAll(noCancelList);
			this.writeSubscribeJson(writeList);
			subscribeCache.putSubscribeInfo(writeList);
		}
		logger.info("取消订阅结束。。。。。");
	}

	/**
	 * 发送设备信息到大华服务器
	 */
	public void sendCamDahu(List<Subscribe> subList) {
		// 判断此订阅ID是否已经发送给过点位信息，
		// 循环发送点位信息
		if (subList.size() > 0) {
			List<DeviceList> listCamDevice = this.encapsulatioDafaCamPushEntity();
			if (!listCamDevice.isEmpty()) {
				List<DeviceList> jsonCamList = subscribeCache.getAllCamJson();
				if (jsonCamList == null || jsonCamList.size() == 0) {// json缓存中没有数据则直接发送
					boolean fl = true;//restDigestClient.sendNoDigestDafaCam(listCamDevice, subList);
					if(fl) {
						this.writeCamJson(listCamDevice);
						subscribeCache.putCamJson(listCamDevice);
					}
				} else {// 如果有数据则比较两个数据
						// 或者为null的数据
					Map<String, DeviceList> map = subscribeCache.getAllCamDeviceMap();
					List<DeviceList> sendDevList = listCamDevice.stream().filter(c -> !map.containsKey(c.getDeviceID()))
							.collect(Collectors.toList());
					if (sendDevList.size() > 0) {
						boolean fl = true;// restDigestClient.sendNoDigestDafaCam(sendDevList, subList);
						if(fl) {
							jsonCamList.addAll(sendDevList);// 将新增加的设备发送
							this.writeCamJson(jsonCamList);
							subscribeCache.putCamJson(jsonCamList);
						}
					}
				}
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
		File paths;
		try {
			String jsonStr = JSONObject.toJSONString(writeList);
			paths = new File(ResourceUtils.getURL("classpath:").getPath());
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
		}
		
	}
	

	/**
	 * 写数据
	 * cam 信息
	 * @param devList
	 */
	private void writeCamJson(List<DeviceList> devList) {
		try {
			File paths = new File(ResourceUtils.getURL("classpath:").getPath());
			Path rootLocation = Paths.get(paths.getPath() + "/dahua");
			if (Files.notExists(rootLocation)) {
				Files.createDirectories(rootLocation);
			}
			String jsonStr = JSONObject.toJSONString(devList);
			Path path = rootLocation.resolve("cam.json");
			if (!StringUtils.isEmpty(jsonStr)) {
				byte[] strToBytes = jsonStr.getBytes(Charset.defaultCharset());
				Files.write(path, strToBytes);
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 发送大华图片
	 */
	public String sendDafaImg(ImagePicLoad imgLoad) {
		return restDigestClient.sendNoDigestImg(imgLoad);
	}

	/**
	 * 发送过车记录
	 */
	@Async("asyncServiceExecutor")
	public void sendDafaCar(MotionVehicleType motionVehicle) {
		logger.info("异步发送start");
		List<CarpassPushEntity> datfCar = this.encapsulationDafaCarPushReq(motionVehicle);
		logger.info(JSONObject.toJSONString(datfCar));
		restDigestClient.sendNoDigestDafaCar(datfCar);
		logger.info("异步发送 end ！");
	}

	/**
	 * 封装发送设备信息
	 * 
	 * @param list
	 */
	public List<DeviceList> encapsulatioDafaCamPushEntity() {
		List<CameraRows> camRows = cameraCache.getAllCameraList();
		List<DeviceList> deviceList = new ArrayList<>();
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
		return deviceList;
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
		pushEntity.setMotorVehicleID(motionVehicleType.getId() + motionVehicleType.getMotorVehicleID());
		pushEntity.setSourceID(motionVehicleType.getSerialID());
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
		pushEntity.setPlateClass(motionVehicleType.getRearPlateClass());// 号牌种类
		pushEntity.setPlateColor(motionVehicleType.getPlateColor());// 车牌颜色
		pushEntity.setPlateNo(motionVehicleType.getPlateNo());
		pushEntity.setSpeed(motionVehicleType.getSpeed());// 行驶速度
		pushEntity.setDirection(motionVehicleType.getDirection());// 方向
		pushEntity.setVehicleClass(motionVehicleType.getVehicleClass());// 车辆类型
		pushEntity.setVehicleBrand(motionVehicleType.getVehicleBrand());
		if (!StringUtils.isEmpty(motionVehicleType.getVehicleLength())) {
			try {
				pushEntity.setVehicleLength(Integer.parseInt(motionVehicleType.getVehicleLength()));
			} catch (RuntimeException e) {
				logger.error("数据类型转换失败:" + motionVehicleType.getVehicleLength());
			}
		}
		pushEntity.setVehicleWidth(motionVehicleType.getVehicleWidth());
		pushEntity.setVehicleHeight(motionVehicleType.getVehicleHeight());
		pushEntity.setVehicleColor(motionVehicleType.getVehicleColor() + "");
		pushEntity.setVehicleColorDepth(motionVehicleType.getVehicleColorDepth() + "");
		pushEntity.setPassTime(motionVehicleType.getPassTime());// 过车时间
		pushEntity.setLaneID(motionVehicleType.getLaneNo());// 车道号
		pushEntity.setLaneNo(motionVehicleType.getLaneNo());// 车道号
		pushEntity.setPlaceCode(motionVehicleType.getNameOfPassedRoad());// 安装地点行政区划代码
		motorVehicleObjectList.add(pushEntity);
		return motorVehicleObjectList;
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

}
