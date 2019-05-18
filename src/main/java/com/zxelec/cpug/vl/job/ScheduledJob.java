package com.zxelec.cpug.vl.job;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zxelec.cpug.vl.cache.SubscribeCache;
import com.zxelec.cpug.vl.entity.rest.Subscribe;
import com.zxelec.cpug.vl.init.CpbsBasicDataInit;
import com.zxelec.cpug.vl.service.DahuaCarpassPushService;

@Component
public class ScheduledJob {
	
	@Autowired
	private CpbsBasicDataInit cpbsBasicDataService;
	
	
	@Autowired
	private SubscribeCache subscribeCache;
	
	@Autowired
	private DahuaCarpassPushService dahuaCarpassPushService;
	
	/**
	 * 每天凌晨获取设备数据并检查是否需要推送给大华
	 */
	@Scheduled(cron = "${cpug.cam.cron}")
	public void getCpbsCamerInfo() {
		cpbsBasicDataService.sendCpbsCam();
		cpbsBasicDataService.sendCpbsTollgate();
		List<Subscribe> subList = subscribeCache.getAllSubscribeList();
		//订阅信息需要是相机订阅，且为开启订阅状态
		List<Subscribe> camList = subList.stream()
										 .filter(c -> ("2".equals(c.getSubscribeCategory())&& 0 == c.getCancelFlag()))
										 .collect(Collectors.toList());
		dahuaCarpassPushService.sendCamDahua(camList);
		//订阅信息需要是卡口订阅，且为开启订阅状态
		List<Subscribe> tollgateList = subList.stream()
											  .filter(c -> ("5".equals(c.getSubscribeCategory())&& 0 == c.getCancelFlag()))
											  .collect(Collectors.toList());
		dahuaCarpassPushService.sendTollgateDahua(tollgateList);
		//订阅信息需要是卡口订阅，且为开启订阅状态
		List<Subscribe> laneList = subList.stream()
											  .filter(c -> ("6".equals(c.getSubscribeCategory())&& 0 == c.getCancelFlag()))
											  .collect(Collectors.toList());
		dahuaCarpassPushService.sendLaneDahua(laneList);
	}
}
