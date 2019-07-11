package com.zxelec.cpug.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
/**
 * 程序启动完成后进行kafka是否启动监听程序
 * @author liu.yongquan
 *
 */
@Component
public class AfterServiceStarted implements ApplicationRunner {
	
	private Logger logger = LogManager.getLogger(AfterServiceStarted.class);
	
	@Autowired
	private JsonDataInit jsonDataInit;
	/**
	 * 启动指定的topics
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("初始化订阅信息");
		jsonDataInit.init();
		logger.info("启动CPUG 成功。。。。。");
	}
}
