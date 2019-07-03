package com.zxelec.cpug.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.cache.CameraCache;
import com.zxelec.cpug.cache.SubscribeCache;
import com.zxelec.cpug.cache.TollgateCache;
import com.zxelec.cpug.entity.rest.DafaCarPushReq;
import com.zxelec.cpug.entity.rest.DahuaSubscribeRsp;
import com.zxelec.cpug.init.CpbsBasicDataInit;
import com.zxelec.cpug.service.DahuaCarpassPushService;
import com.zxelec.cpug.util.RestDigestClient;

/**
 * 缓存测试查看接口
 * @author liu.yongquan
 *
 */
@RestController
public class CacheController {
	@Autowired
	private KafkaTemplate<Object,Object> kafkaTemplate;
	
	private Logger logger = LogManager.getLogger(CacheController.class);
	
	@Autowired
	private CameraCache cameraCache;
	
	@Autowired
	private SubscribeCache subscribeCache;
	
	@Autowired
	private TollgateCache tollgateCache;
	
	@Autowired
	private CpbsBasicDataInit cpbsBasicDataService;
	/**
	 * 查看缓存信息
	 */
	@RequestMapping("/cache/{type}")
	public String queryCache(@PathVariable("type") String type) {
		if("cam".equals(type)) {
			return JSONObject.toJSONString(cameraCache.getAllCameraList());
		}else if("subscribe".equals(type)) {
			return JSONObject.toJSONString(subscribeCache.getAllSubscribeInfo());
		}else if("tollgate".equals(type)) {
			return JSONObject.toJSONString(tollgateCache.getAll());
		}
		return "type信息不正确!【cam,subscribe,tollgate】";
	}
	/**
	 * 刷新相机和卡口列表缓存
	 * @return
	 */
	@RequestMapping("/refresh")
	public String refreshCache() {
		cpbsBasicDataService.sendCpbsCam();
		cpbsBasicDataService.sendCpbsTollgate();
		return "刷新成功";
	}
	
	/**
	 * 返回版本信息
	 * @return
	 */
	@RequestMapping("/")
	public String cpug() {
		return "应用启动成功";
	}
	
	
	
	
	
	
	
	
	
	@Autowired
	private DahuaCarpassPushService dahuaCarpassPushService;
	
	@Autowired
	private RestDigestClient restDigestClient;
	/**
	 * 推送测试发送接口
	 * @param dafaCarPushReqList
	 * @return
	 */
	@RequestMapping(value = "/VIID/Triggers/onTest",method = RequestMethod.POST)
	public DahuaSubscribeRsp testPush(@RequestBody List<DafaCarPushReq> dafaCarPushReqList) {
		logger.info(JSONObject.toJSONString(dafaCarPushReqList));
		restDigestClient.testSendNoDigestDafaCar(dafaCarPushReqList);
		return new DahuaSubscribeRsp("0","200","发送成功","11111","");
	}
	
	@RequestMapping("/test")
	public String exectorTest() {
		logger.info("多线程测试start");
		dahuaCarpassPushService.executeAsync();
		logger.info("多线程测试 end ");
		return "测试";
	}
	@GetMapping("/send")
	public String send(String topic,String key,String data) {
		if(!StringUtils.isEmpty(data)) {
			byte[] by = data.getBytes(Charset.defaultCharset());
			kafkaTemplate.send(topic, key, by);
		}
		return "kafka发送成功";
	}
}
