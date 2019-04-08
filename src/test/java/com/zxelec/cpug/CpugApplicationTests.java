package com.zxelec.cpug;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zxelec.cpug.entity.rest.Subscribe;
import com.zxelec.cpug.util.CustomServerProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CpugApplicationTests {
	@Autowired
	CustomServerProperties customServerProperties;

	@Value("dahua/data.json")
	private Resource areaRes;
	
	@Value("${cpug.cam.cron}")
	private String crom;
	@Test
	public void contextLoads() {
		System.out.println(crom);
//		 String jsonData = this.jsonRead(file);
	}
	
}
