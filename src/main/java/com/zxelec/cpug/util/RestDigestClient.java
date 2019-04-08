package com.zxelec.cpug.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.cache.SubscribeCache;
import com.zxelec.cpug.entity.rest.CarpassPushEntity;
import com.zxelec.cpug.entity.rest.DafaCamPushEntity;
import com.zxelec.cpug.entity.rest.DafaCarPushReq;
import com.zxelec.cpug.entity.rest.DahuaSubscribeRsp;
import com.zxelec.cpug.entity.rest.DeviceList;
import com.zxelec.cpug.entity.rest.ImagePicLoad;
import com.zxelec.cpug.entity.rest.Subscribe;
/**
 * HTTPClient 实现
 * @author liu.yongquan
 *
 */
@Component
public class RestDigestClient {
	private Logger logger = LogManager.getLogger(RestDigestClient.class);

	@Autowired
	private CustomServerProperties customServerProperties;

	@Autowired
	private SubscribeCache subscribeCache;

	/**
	 * 发送digets认证
	 * 
	 * @param url
	 *            请求url
	 * @param car
	 *            过车记录
	 */
	public void sendDafaCar(List<DafaCarPushReq> car) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		List<Subscribe> listSub = subscribeCache.getAllSubscribeData();
		for (Subscribe subscribe : listSub) {
			String url = subscribe.getResourceURI() + customServerProperties.getCpugDafaPrefixAddress()
					+ subscribe.getSubscribeID() + customServerProperties.getCpugDafaSuffixAddress();
			HttpPost httpPost = new HttpPost(url);
			HttpContext context = getLocalHttpContext("", "");
			String booy = JSONObject.toJSONString(car);
			HttpEntity entity = new StringEntity(booy, "utf-8");
			httpPost.setEntity(entity);
			try {
				HttpResponse response = httpclient.execute(httpPost, context);
				if (response.getStatusLine().getStatusCode() == 200) {
					logger.info(EntityUtils.toString(response.getEntity(), "UTF-8"));
				} else {
					logger.error("digest认证失败或者其他未知异常：" + response.getStatusLine().getStatusCode());
				}
			} catch (IOException e) {
				logger.error("未知异常sendCmsHttpDigestClient:" + e.getMessage(), e);
			} finally {
				try {
					if (null != httpclient) {
						httpclient.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 大华无认证推送过车记录 String url =
	 * "http://10.104.189.104:8300/VIID/Triggers/Subscribes/22020000000020190404103054037876"+
	 * customServerProperties.getCpugDafaAddress();
	 * 
	 * @param carList
	 */
	public void sendNoDigestDafaCar(List<CarpassPushEntity> motorVehicleObjectList) {
		List<Subscribe> listSub = subscribeCache.getAllSubscribeData();
		List<Subscribe> sList = listSub.stream().filter(c -> 0 == c.getStatus()).collect(Collectors.toList());
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			for (Subscribe subscribe : sList) {
				List<DafaCarPushReq> list = new ArrayList<>();
				DafaCarPushReq req = new DafaCarPushReq();
				String url = subscribe.getReceiveAddr() + customServerProperties.getCpugDafaPrefixAddress()
						+ subscribe.getSubscribeID() + customServerProperties.getCpugDafaSuffixAddress();
				HttpPost httpPost = new HttpPost(url);
				String notificationID = customServerProperties.getCpugDomainId() + DateUtils.data2String();
				req.setNotificationID(notificationID);
				req.setTriggerTime(new Date());
				req.setSubscribeID(subscribe.getSubscribeID());
				req.setTitle(subscribe.getTitle());
				req.setMotorVehicleObjectList(motorVehicleObjectList);
				list.add(req);
				String booy = JSONObject.toJSONString(list);
				HttpEntity entity = new StringEntity(booy, "utf-8");
				httpPost.setEntity(entity);
				try {
					HttpResponse response = httpclient.execute(httpPost);
					if (response.getStatusLine().getStatusCode() == 200) {
						String eng = EntityUtils.toString(response.getEntity(), "UTF-8");
						DahuaSubscribeRsp subRsp = JSONObject.parseObject(eng, DahuaSubscribeRsp.class);
						if (!"0".equals(subRsp.getStatusCode())) {
							logger.error("发送数据：【" + JSONObject.toJSONString(list) + "】 \n" + "通知失败："
									+ JSONObject.toJSONString(subRsp));
						}
					} else {
						logger.error("发送数据：【" + JSONObject.toJSONString(list) + "】\n " + "响应失败："
								+ response.getStatusLine().getStatusCode());
					}
				} catch (IOException e) {
					logger.error("发送数据：【" + JSONObject.toJSONString(list) + "】\n " + "未知异常sendNoDigestDafaCar:"
							+ e.getMessage());
				}
			}
		} finally {
			try {
				if (null != httpclient) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送device信息
	 * @param listCamDevice
	 * @param subList
	 */
	public boolean sendNoDigestDafaCam(List<DeviceList> listCamDevice, List<Subscribe> subList) {
		boolean fl = false;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			for (Subscribe subscribe : subList) {
				List<DafaCamPushEntity> sendList = new ArrayList<>();
				DafaCamPushEntity entity = new DafaCamPushEntity();
				String notificationID = customServerProperties.getCpugDomainId() + DateUtils.data2String();
				entity.setNotificationID(notificationID);
				entity.setSubscribeID(subscribe.getSubscribeID());
				entity.setTitle(subscribe.getTitle());
				entity.setTriggerTime(new Date());
				entity.setDeviceList(listCamDevice);
				sendList.add(entity);

				String url = subscribe.getReceiveAddr() + customServerProperties.getCpugDafaPrefixAddress()
						+ subscribe.getSubscribeID() + customServerProperties.getCpugDafaSuffixAddress();
				HttpPost httpPost = new HttpPost(url);
				String body = JSONObject.toJSONString(sendList);
				StringEntity strentity = new StringEntity(body, "utf-8");
				strentity.setContentType("application/json;charset=UTF-8");
				httpPost.setEntity(strentity);
				httpPost.setHeader("Content-type", "application/json;charset=UTF-8");
				try {
					HttpResponse response = httpclient.execute(httpPost);
					if (response.getStatusLine().getStatusCode() == 200) {
						String eng = EntityUtils.toString(response.getEntity(), "UTF-8");
						DahuaSubscribeRsp subRsp = JSONObject.parseObject(eng, DahuaSubscribeRsp.class);
						if (!"0".equals(subRsp.getStatusCode())) {
							logger.error("发送数据：【" + JSONObject.toJSONString(sendList) + "】 \n" + "通知失败："
									+ JSONObject.toJSONString(subRsp));
						}else {
							fl = true;
						}
					} else {
						logger.error("发送数据：【" + JSONObject.toJSONString(sendList) + "】\n " + "响应失败："
								+ response.getStatusLine().getStatusCode());
					}
				} catch (IOException e) {
					logger.error("发送数据：【" + JSONObject.toJSONString(sendList) + "】\n " + "未知异常sendNoDigestDafaCar:"
							+ e.getMessage());
				}
			}
		} finally {
			try {
				if (null != httpclient) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fl;
	}

	/**
	 * 发送图片给大华服务器
	 * 
	 * @param picLoad
	 */
	public String sendNoDigestImg(ImagePicLoad picLoad) {
		String imgUrl = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = customServerProperties.getCpugDafaImgUrl();
		HttpPost httpPost = new HttpPost(url);
		String body = JSONObject.toJSONString(picLoad);
		StringEntity entity = new StringEntity(body, "utf-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
		try {
			HttpResponse response = httpclient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				String eng = EntityUtils.toString(response.getEntity(), "UTF-8");
				JSONObject jsonObj = JSONObject.parseObject(eng);
				imgUrl = jsonObj.getString("pictureUrl");
			} else {
				logger.error("图片响应失败：" + response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			logger.error("图片未知异常sendNoDigestImg:" + e.getMessage());
		} finally {
			try {
				if (null != httpclient) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imgUrl;
	}

	/**
	 * 查询cpbs基础信息
	 * 
	 * @param url
	 *            访问url
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param body
	 *            请求数据
	 */
	public String sendPostVissDigest(String url, String username, String password, String body) {
		String respBody = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		HttpEntity entity = new StringEntity(body, "utf-8");
		httpPost.setEntity(entity);
		HttpContext context = this.getLocalHttpContext(username, password);
		try {
			HttpResponse response = httpclient.execute(httpPost, context);
			if (response.getStatusLine().getStatusCode() == 200) {
				respBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				logger.error("图片响应失败：" + response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			logger.error("sendVissDigest：" + e.getMessage());
		} finally {
			try {
				if (null != httpclient) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return respBody;
	}

	/**
	 * get 查询cpbs基础信息
	 * 
	 * @param url
	 *            访问url
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 */
	public String sendGetVissDigest(String url, String username, String password) {
		String respBody = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		HttpContext context = this.getLocalHttpContext(username, password);
		try {
			HttpResponse response = httpclient.execute(httpGet, context);
			if (response.getStatusLine().getStatusCode() == 200) {
				respBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			} else {
				logger.error("访问VISS_CPBS失败：" + response.getStatusLine().getStatusCode());
			}
		} catch (IOException e) {
			logger.error(" 访问VISS_CPBS失败 sendVissDigest：" + e.getMessage());
		} finally {
			try {
				if (null != httpclient) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return respBody;
	}

	/**
	 * 获得认证头（摘要认证）
	 *
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return HttpContext 认证头对象
	 */
	protected HttpContext getLocalHttpContext(String username, String password) {
		HttpClientContext httpContext = null;
		try {
			httpContext = HttpClientContext.create();
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
			httpContext.setCredentialsProvider(credentialsProvider);
		} catch (Exception e) {
			logger.error("未知异常getLocalHttpContext:" + e.getMessage(), e);
		}
		return httpContext;
	}
	

	/**
	 * 测试程序 大华无认证推送过车记录 String url =
	 * "http://10.104.189.104:8300/VIID/Triggers/Subscribes/22020000000020190404103054037876"+
	 * customServerProperties.getCpugDafaAddress();
	 * 
	 * @param carList
	 */
	public void testSendNoDigestDafaCar(List<DafaCarPushReq> list) {
		List<Subscribe> listSub = subscribeCache.getAllSubscribeData();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			for (Subscribe subscribe : listSub) {
				String url = subscribe.getReceiveAddr() + customServerProperties.getCpugDafaPrefixAddress()
						+ subscribe.getSubscribeID() + customServerProperties.getCpugDafaSuffixAddress();
				HttpPost httpPost = new HttpPost(url);
				String booy = JSONObject.toJSONString(list);
				HttpEntity entity = new StringEntity(booy, "utf-8");
				httpPost.setEntity(entity);
				try {
					HttpResponse response = httpclient.execute(httpPost);
					if (response.getStatusLine().getStatusCode() == 200) {
						String eng = EntityUtils.toString(response.getEntity(), "UTF-8");
						DahuaSubscribeRsp subRsp = JSONObject.parseObject(eng, DahuaSubscribeRsp.class);
						if (!"0".equals(subRsp.getStatusCode())) {
							logger.error("发送数据：【" + JSONObject.toJSONString(list) + "】 \n" + "通知失败："
									+ JSONObject.toJSONString(subRsp));
						}
					} else {
						logger.error("发送数据：【" + JSONObject.toJSONString(list) + "】\n " + "响应失败："
								+ response.getStatusLine().getStatusCode());
					}
				} catch (IOException e) {
					logger.error("发送数据：【" + JSONObject.toJSONString(list) + "】\n " + "未知异常sendNoDigestDafaCar:"
							+ e.getMessage());
				}
			}
		} finally {
			try {
				if (null != httpclient) {
					httpclient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
