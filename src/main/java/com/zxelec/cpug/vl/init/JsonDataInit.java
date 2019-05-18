package com.zxelec.cpug.vl.init;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.vl.cache.SubscribeCache;
import com.zxelec.cpug.vl.entity.rest.Subscribe;
import com.zxelec.cpug.vl.util.JsonUtils;
/**
 * 解析json文件数据
 * @author liu.yongquan
 *
 */
@Service
public class JsonDataInit{
	@Value("classpath:dahua/data.json")
	private Resource areaRes;
	
	@Autowired
	private SubscribeCache subscribeCache;
	
	@PostConstruct
	public void init() {
		this.subscribeJsonInit();
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