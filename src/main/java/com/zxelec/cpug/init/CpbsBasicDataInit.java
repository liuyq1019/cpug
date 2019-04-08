package com.zxelec.cpug.init;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.cache.CameraCache;
import com.zxelec.cpug.entity.rest.CameraRows;
import com.zxelec.cpug.entity.rest.VissCpbsBean;
import com.zxelec.cpug.entity.rest.VissCpbsResponseStatus;
import com.zxelec.cpug.util.CustomServerProperties;
import com.zxelec.cpug.util.Md5Encrypt;
import com.zxelec.cpug.util.RestDigestClient;

/**
 * 处理cpbs基础数据
 * 
 * @author liu.yongquan
 *
 */
@Service
public class CpbsBasicDataInit{
	private Logger logger = LogManager.getLogger(CpbsBasicDataInit.class);
	@Autowired
	private CustomServerProperties customServerProperties;
	
	@Autowired
	private CameraCache cameraCache;

	@Autowired
	private RestDigestClient restDigestClient;
	
	
	@PostConstruct
	public void init() {
		sendCpbs();
	}
	/**
	 * 每天定时从cpbs获取设备列表数据
	 */
	public void sendCpbs() {
		VissCpbsBean<CameraRows> vissCpbsBean = null;
		String url = customServerProperties.getCpugCpbsUrl() + "rest/Camera";
		String username = customServerProperties.getCpugVissUsername();
		String password = Md5Encrypt.encrypt(customServerProperties.getCpugVissPassword());
		String respBody = restDigestClient.sendGetVissDigest(url, username, password);
		try {
			if (!StringUtils.isEmpty(respBody)) {
				vissCpbsBean = JSONObject.parseObject(respBody, VissCpbsBean.class);
				VissCpbsResponseStatus statusBean = vissCpbsBean.getResponseStatus();
				if (statusBean != null && "0".equals(statusBean.getStatusCode())) {
					List<CameraRows> camerRows = vissCpbsBean.getRows();
					if(camerRows!=null) {
						List<CameraRows> jsonList = JSONArray.parseArray(JSONObject.toJSONString(camerRows),CameraRows.class);
						List<CameraRows> camList = jsonList.stream().filter(c -> "卡口".equals(c.getType())).collect(Collectors.toList());
						cameraCache.putCamera(camList);
					}
				} else {// 其他状态表示需要重新获取
					logger.error("获取失败:"+JSONObject.toJSONString(statusBean));
				}
			} else {// 如果出现null表示有异常，则休眠30分钟后重新获取
				logger.error("获取数据CAM失败："+respBody);
			}
		}catch (RuntimeException e) {
			logger.error("程序运行时异常:"+e.getMessage());
		}
	}
}
