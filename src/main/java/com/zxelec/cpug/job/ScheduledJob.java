package com.zxelec.cpug.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zxelec.cpug.cache.SubscribeCache;
import com.zxelec.cpug.entity.rest.Subscribe;
import com.zxelec.cpug.init.CpbsBasicDataInit;
import com.zxelec.cpug.service.DafaCarpassPushService;

@Component
public class ScheduledJob {
	
	@Autowired
	private CpbsBasicDataInit cpbsBasicDataService;
	
	
	@Autowired
	private SubscribeCache subscribeCache;
	
	@Autowired
	private DafaCarpassPushService dafaCarpassPushService;
	
	/**
	 * 每天凌晨获取设备数据并检查是否需要推送给大华
	 */
	@Scheduled(cron = "${cpug.cam.cron}")
	public void getCpbsCamerInfo() {
		cpbsBasicDataService.sendCpbs();
		List<Subscribe> subList = subscribeCache.getAllSubscribeData();
		dafaCarpassPushService.sendCamDahu(subList);
	}
}
