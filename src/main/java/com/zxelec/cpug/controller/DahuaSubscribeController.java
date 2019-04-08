package com.zxelec.cpug.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxelec.cpug.cache.SubscribeCache;
import com.zxelec.cpug.entity.rest.DahuaSubscribeReq;
import com.zxelec.cpug.entity.rest.DahuaSubscribeRsp;
import com.zxelec.cpug.entity.rest.Subscribe;
import com.zxelec.cpug.service.DahuaCarpassPushService;
import com.zxelec.cpug.util.CustomServerProperties;
import com.zxelec.cpug.util.DateUtils;
/**
 * 大华订阅接口
 * @author liu.yongquan
 *
 */
@RestController
public class DahuaSubscribeController {
	@Autowired
	private DahuaCarpassPushService dahuaCarpassPushService;
	
	@Autowired
	private CustomServerProperties customServerProperties;
	

	
	/**
	 * 批量新增订阅
	 * @param dahuaSubscribeReq
	 * @return
	 */
	@RequestMapping(value = "/VIID/Triggers/Subscribes",method = RequestMethod.POST)
	public DahuaSubscribeRsp batchSubscribe(@RequestBody DahuaSubscribeReq dahuaSubscribeReq) {
		String id = customServerProperties.getCpugDomainId() + DateUtils.data2String();
		List<Subscribe> subscribe = dahuaSubscribeReq.getSubscribeList().getSubscribe();
		//卡口信息采集订阅
		List<Subscribe> tollgetSubscribe = subscribe.stream().filter(c -> "5".equals(c.getSubscribeCategory())).collect(Collectors.toList());
		//设备信息采集订阅
		List<Subscribe> deviceSubscribe = subscribe.stream().filter(c -> "2".equals(c.getSubscribeCategory())).collect(Collectors.toList());
		//过车记录采集订阅
		List<Subscribe> carSubscribe = subscribe.stream().filter(c -> "3".equals(c.getSubscribeCategory())).collect(Collectors.toList());
		//车道订阅采集订阅
		List<Subscribe> laneSubscribe = subscribe.stream().filter(c -> "6".equals(c.getSubscribeCategory())).collect(Collectors.toList());
				
		if(deviceSubscribe!=null && deviceSubscribe.size()>0) {
			dahuaCarpassPushService.deviceSubscribe(deviceSubscribe);
		}
		if(tollgetSubscribe!=null && tollgetSubscribe.size()>0) {
			dahuaCarpassPushService.tollgateSubscribe(tollgetSubscribe);
		}
		if(carSubscribe!=null && carSubscribe.size()>0) {
			dahuaCarpassPushService.carSubscribe(carSubscribe);
		}
		if(laneSubscribe!=null && laneSubscribe.size()>0) {
			dahuaCarpassPushService.laneSubscribe(laneSubscribe);
		}
		return new DahuaSubscribeRsp("0", "200", "订阅成功", id, "");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	/**
//	 * 批量修改订阅接口
//	 * @param dahuaSubscribeReq
//	 * @return
//	 */
//	@RequestMapping(value = "/VIID/Triggers/Subscribes",method = RequestMethod.PUT)
//	public DahuaSubscribeRsp batchUpdateTollgateSubscribe(@RequestBody DahuaSubscribeReq dahuaSubscribeReq) {
//		return checkSubscribe(null,dahuaSubscribeReq,null,RequestMethod.PUT);
//	}
//	
//	/**
//	 * 批量删除订阅
//	 * @return
//	 */
//	@RequestMapping(value = "/VIID/Triggers/Subscribes",method = RequestMethod.DELETE)
//	public DahuaSubscribeRsp batchDelSubscribe() {
//		return null;
//	}
//	
//	/**
//	 * 取消订阅{大华没做、取消订阅还是走的订阅接口，无法使用}
//	 * 或者修改订阅信息
//	 * @param id
//	 * @param subscribe
//	 * @return
//	 */
//	@RequestMapping(value = "/VIID/Triggers/Subscribes/{ID}",method = RequestMethod.PUT)
//	public DahuaSubscribeRsp cancelSubscribe(@PathVariable("ID") String id,@RequestBody Subscribe subscribe) {
//		if(subscribe.getStatus() == 1) {//1表示取消订阅
//			return this.checkSubscribe(id, null,subscribe, RequestMethod.PUT);
//		}else {
//			DahuaSubscribeReq dahuaSubscribeReq = new DahuaSubscribeReq();
//			SubscribeList subscribeList  = new SubscribeList();
//			List<Subscribe> subList = new ArrayList<>();
//			subList.add(subscribe);
//			subscribeList.setSubscribe(subList);
//			dahuaSubscribeReq.setSubscribeList(subscribeList);
//			return this.checkSubscribe(null, dahuaSubscribeReq, null, RequestMethod.PUT);
//		}
//		
//	}
	
	
}
