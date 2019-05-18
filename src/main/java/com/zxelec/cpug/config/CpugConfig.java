package com.zxelec.cpug.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zxelec.cpug.service.TestDbMsg;

@Configuration
@Profile("prod")
public class CpugConfig {
	@Bean
	public TestDbMsg getTestDbMsg() {
		System.out.println("============pord=========");
		return new TestDbMsg();
	}
}
