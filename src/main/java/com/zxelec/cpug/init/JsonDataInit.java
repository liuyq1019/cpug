package com.zxelec.cpug.init;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.cache.SubscribeCache;
import com.zxelec.cpug.entity.rest.DeviceList;
import com.zxelec.cpug.entity.rest.Subscribe;
import com.zxelec.cpug.util.JsonUtils;
/**
 * 解析json文件数据
 * @author liu.yongquan
 *
 */
@Service
public class JsonDataInit implements Runnable{
	private Logger logger = LogManager.getLogger(JsonDataInit.class);
	@Value("classpath:dahua/data.json")
	private Resource areaRes;
	
	@Value("classpath:dahua/cam.json")
	private Resource camRes;
	@Autowired
	private SubscribeCache subscribeCache;
	
	@Override
	public void run() {
		this.subscribeJsonInit();
		this.camJsonInit();
	}
	
	private void camJsonInit() {
		try {
			File file = camRes.getFile();
			String jsonData = JsonUtils.jsonRead(file);
			if (!StringUtils.isEmpty(jsonData)) {
				List<DeviceList> devList = new ArrayList<>();
				JSONArray array = JSONArray.parseArray(jsonData);
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObject2 = array.getJSONObject(i);
					DeviceList m = JSONObject.toJavaObject(jsonObject2, DeviceList.class);
					logger.info(JSONObject.toJSONString(m));
					devList.add(m);
				}
				subscribeCache.putCamJson(devList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 订阅信息
	 */
	private void subscribeJsonInit() {
		try {
			File file = areaRes.getFile();
			String jsonData = JsonUtils.jsonRead(file);
			if (!StringUtils.isEmpty(jsonData)) {
				List<Subscribe> subList = new ArrayList<>();
				JSONArray array = JSONArray.parseArray(jsonData);
				for (int i = 0; i < array.size(); i++) {
					JSONObject jsonObject2 = array.getJSONObject(i);
					Subscribe m = JSONObject.toJavaObject(jsonObject2, Subscribe.class);
					subList.add(m);
				}
				subscribeCache.putSubscribeInfo(subList);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
