package com.zxelec.cpug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

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
