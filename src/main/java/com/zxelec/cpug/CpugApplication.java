package com.zxelec.cpug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * 排除数据源加载autoConfig 
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableScheduling
public class CpugApplication {
	public static void main(String[] args) {
		SpringApplication.run(CpugApplication.class, args);
	}

}
