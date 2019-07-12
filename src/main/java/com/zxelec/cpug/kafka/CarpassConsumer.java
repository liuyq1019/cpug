package com.zxelec.cpug.kafka;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.cache.SubscribeCache;
import com.zxelec.cpug.entity.MotionVehicleType;
import com.zxelec.cpug.entity.VehiclePictureType;
import com.zxelec.cpug.entity.VissMessage;
import com.zxelec.cpug.entity.rest.ImagePicLoad;
import com.zxelec.cpug.entity.rest.Subscribe;
import com.zxelec.cpug.service.DahuaCarpassPushService;
import com.zxelec.cpug.util.CustomServerProperties;
import com.zxelec.cpug.util.DateUtils;
import com.zxelec.cpug.util.VissMessageConvertUtils;

@Component
public class CarpassConsumer {

	private Logger logger = LogManager.getLogger(CarpassConsumer.class);

	@Autowired
	private CustomServerProperties customServerProperties;
	
	@Autowired
	private SubscribeCache subscribeCache;
	
	
	@Autowired
	private DahuaCarpassPushService dahuaCarpassPushService;

	/**
	 * 1. 订阅 MOTION_VEHICLE_SUBSCRIPTION 获取过车记录.
	 */
	@KafkaListener(id = "yhkkCarId",topics = { "motion-vehicle-notification" },groupId = "car_yh")
	public void carpassMessage(byte[] message) {
		// 获取到数据后并解析为对象后先想
		try {
			VissMessage<MotionVehicleType> vissMessage = VissMessageConvertUtils.resolveVissMessage(message,
					MotionVehicleType.class);
			convert2CarPassedAndSend(vissMessage);
		} catch (UnsupportedEncodingException e) {
			logger.error("转换 viss 二进制 错误 : " + e.getMessage());
		}
	}

	/**
	 * 解析过车数据
	 * 
	 * @param vissMessage
	 */
	private void convert2CarPassedAndSend(VissMessage<MotionVehicleType> vissMessage) {
		List<Subscribe> subscribeList = subscribeCache.getAllSubscribeList().stream()
					 .filter(c -> (0 == c.getCancelFlag()&& "3" .equals(c.getSubscribeCategory())))
					 .collect(Collectors.toList());
		if(subscribeList!=null && subscribeList.size()>0) {
			MotionVehicleType type = vissMessage.getBody();
			if (type.getPictures() == null || type.getPictures().length <= 0) {// 如果没有图片则不进行数据处理
				return;
			}
			this.getPicBase64(type, vissMessage.getBinaryData());
			/**
			 * 大华专用配置
			 */
			//如果近景照片为null，则获取远景照片放入近景中、已经和大华确认
			if(StringUtils.isEmpty(type.getStorageUrl1())) {
				type.setStorageUrl1(type.getStorageUrl3());
			}
			dahuaCarpassPushService.sendDahuaCar(type,subscribeList);
		}else {
			logger.error("过车记录未进行订阅：" + JSONObject.toJSONString(vissMessage.getBody()));
		}
	}


	// 优先顺序选择一张显示， 0：近景照片2：远景照片3：合成图 4：缩略图1：车牌照片；
	private void getPicBase64(MotionVehicleType motionVehicleType, List<byte[]> binarys) {
		VehiclePictureType[] pictures = motionVehicleType.getPictures();
		int[] order = new int[] { 0, 1, 2, 3, 4 };
		for (int type : order) {
			getPicBase64(motionVehicleType, type, pictures, binarys);
		}
	}

	// 优先获取解析Ref的实时图片，若无Ref再考虑FileRef的获取。
	private void getPicBase64(MotionVehicleType motionVehicleType, int type, VehiclePictureType[] pictures,
			List<byte[]> binarys) {
		for (VehiclePictureType picture : pictures) {
			if (picture.getType() == type) {
				Integer ref = picture.getRef();
				if (ref == null) {// 如果图片只有一张 可能ref 为null
					ref = 0;
				}
				String base64 = Base64.encodeBase64String(binarys.get(ref));
				String res = this.sendImg(picture.getFmt(), base64.length(), motionVehicleType, base64, type);
				if (type == 0) {
					motionVehicleType.setStorageUrl1(res);
				} else if (type == 1) {
					motionVehicleType.setStorageUrl2(res);
				} else if (type == 2) {
					motionVehicleType.setStorageUrl3(res);
				} else if (type == 3) {
					motionVehicleType.setStorageUrl4(res);
				} else if (type == 4) {
					motionVehicleType.setStorageUrl5(res);
				}
			}
		}
	}

	/**
	 * 
	 * @param pictureType
	 *            当前图片的格式[1：JPG、2：BMP、3：PNG]
	 * @param pictureLength
	 *            存储图片的长度
	 * @param picture
	 *            客户端向存储写入的图片数据（图片二进制数据转码成base46转码后的字符串）
	 * @param passTime
	 *            过车时间
	 * @param tollgateId
	 *            卡口编号
	 * @param tollgateName
	 *            卡口名称
	 * @param type
	 *            图片类型（）
	 * @return
	 */
	private String sendImg(String pictureTypes, int pictureLength, MotionVehicleType motionVehicleType, String picture,
			int type) {
		int pictureType;
		if (pictureTypes.equalsIgnoreCase("jpg")) {
			pictureType = 1;
		} else if (pictureTypes.equalsIgnoreCase("png")) {
			pictureType = 3;
		} else {
			pictureType = 2;
		}
		ImagePicLoad imgLoad = new ImagePicLoad();
		String serialID = customServerProperties.getCpugIp() + "_" + customServerProperties.getCpugDeviceId();
		String path = "/dahua/" + DateUtils.data2String(motionVehicleType.getPassTime(), DateUtils.dateYNDPattern) + "/"
				+ motionVehicleType.getTollgateID() + "/" + motionVehicleType.getTollgateID() + "-"
				+ System.currentTimeMillis() + "-" + type + "." + pictureTypes;
		motionVehicleType.setSerialID(serialID);
		imgLoad.setSerialID(serialID);
		// imgLoad.setPoolID(poolID);
		imgLoad.setTimeStamp(new Date());
		imgLoad.setPictureType(pictureType + "");
		imgLoad.setToken("Token");
		imgLoad.setPictureLength(pictureLength + "");
		imgLoad.setPicture(picture);
		imgLoad.setLunId(customServerProperties.getCpugLunId());
		imgLoad.setPath(path);
		return dahuaCarpassPushService.sendDahuaImg(imgLoad);
	}

}
