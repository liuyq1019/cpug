package com.zxelec.cpug.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConstructInit {
	@Autowired
	private JsonDataInit jsonDataInit;
	@PostConstruct
	public void init() {
		Thread j = new Thread(jsonDataInit);
		j.start();
	}
}
