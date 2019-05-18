package com.zxelec.cpug.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;

import com.zxelec.cpug.vl.service.TestDbMsg;


@Profile("prod")
@Configuration
@ComponentScan("com.zxelec.cpug.vl")
public class CpugConfig {
	@Bean
	public TestDbMsg getTestDbMsg() {
		System.out.println("============pord=========");
		return new TestDbMsg();
	}
}
