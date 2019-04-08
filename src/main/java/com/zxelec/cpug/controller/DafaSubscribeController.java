package com.zxelec.cpug.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zxelec.cpug.cache.SubscribeCache;
import com.zxelec.cpug.entity.rest.DahuaSubscribeReq;
import com.zxelec.cpug.entity.rest.DahuaSubscribeRsp;
import com.zxelec.cpug.entity.rest.Subscribe;
import com.zxelec.cpug.entity.rest.SubscribeList;
import com.zxelec.cpug.service.DafaCarpassPushService;
import com.zxelec.cpug.util.CustomServerProperties;
import com.zxelec.cpug.util.DateUtils;
/**
 * 大华订阅接口
 * @author liu.yongquan
 *
 */
@RestController
public class DafaSubscribeController {
	
	@Autowired
	private DafaCarpassPushService dafaCarpassPushService;
	
	@Autowired
	private CustomServerProperties customServerProperties;
	
	@Autowired
	private SubscribeCache subscribeCache;


	
	/**
	 * 批量新增订阅
	 * @param dahuaSubscribeReq
	 * @return
	 */
	@RequestMapping(value = "/VIID/Triggers/Subscribes",method = RequestMethod.POST)
	public DahuaSubscribeRsp batchTollgateSubscribe(@RequestBody DahuaSubscribeReq dahuaSubscribeReq) {
		return checkSubscribe(null,dahuaSubscribeReq,null,RequestMethod.POST);
	}
	/**
	 * 批量修改订阅接口
	 * @param dahuaSubscribeReq
	 * @return
	 */
	@RequestMapping(value = "/VIID/Triggers/Subscribes",method = RequestMethod.PUT)
	public DahuaSubscribeRsp batchUpdateTollgateSubscribe(@RequestBody DahuaSubscribeReq dahuaSubscribeReq) {
		return checkSubscribe(null,dahuaSubscribeReq,null,RequestMethod.PUT);
	}
	
	/**
	 * 批量删除订阅
	 * @return
	 */
	@RequestMapping(value = "/VIID/Triggers/Subscribes",method = RequestMethod.DELETE)
	public DahuaSubscribeRsp batchDelSubscribe() {
		return null;
	}
	
	/**
	 * 取消订阅或者当个修改
	 * @param id
	 * @param subscribe
	 * @return
	 */
	@RequestMapping(value = "/VIID/Triggers/Subscribes/{ID}",method = RequestMethod.PUT)
	public DahuaSubscribeRsp cancelSubscribe(@PathVariable("ID") String id,@RequestBody Subscribe subscribe) {
		if(subscribe.getStatus() == 1) {//1表示取消订阅
			return this.checkSubscribe(id, null,subscribe, RequestMethod.PUT);
		}else {
			DahuaSubscribeReq dahuaSubscribeReq = new DahuaSubscribeReq();
			SubscribeList subscribeList  = new SubscribeList();
			List<Subscribe> subList = new ArrayList<>();
			subList.add(subscribe);
			subscribeList.setSubscribe(subList);
			dahuaSubscribeReq.setSubscribeList(subscribeList);
			return this.checkSubscribe(null, dahuaSubscribeReq, null, RequestMethod.PUT);
		}
		
	}
	
	/**
	 * 检查订阅信息是否正确
	 * @param ids 取消订阅使用
	 * @param dahuaSubscribeReq 新增订阅和修改订阅使用 
	 * @param subscribe 取消订阅使用
	 * @param method 调用方式
	 * @return
	 */
	private DahuaSubscribeRsp checkSubscribe(String ids ,DahuaSubscribeReq dahuaSubscribeReq,
			Subscribe subscribe,RequestMethod method) {
		String id = customServerProperties.getCpugDomainId() + DateUtils.data2String();
		String statusString = "";
		if(RequestMethod.PUT.equals(method)) {
			if(ids == null) {
				statusString = "修改成功";
				dafaCarpassPushService.updateSubscribe(dahuaSubscribeReq);
			}else {
				if(subscribeCache.getSubscribeInfo(ids)==null) {
					statusString = "取消失败、ID找不到";
				}else {
					dafaCarpassPushService.cancelSubscribe(ids, subscribe);
					statusString = "取消成功";
				}
			}
		}else if(RequestMethod.POST.equals(method)) {
			List<Subscribe> list = dahuaSubscribeReq.getSubscribeList().getSubscribe();
			List<Subscribe> cancelList = list.stream().filter(c -> 0==c.getStatus()).collect(Collectors.toList());
			if(cancelList.size() == list.size()) {
				statusString = "订阅成功";
				dafaCarpassPushService.resultSubscribeInfo(dahuaSubscribeReq);
			}else {
				statusString = "订阅失败【status状态值有误】！";
			}
		}
		return new DahuaSubscribeRsp("0", "200", statusString, id, "");
	}
	
}
