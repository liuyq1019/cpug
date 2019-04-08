package com.zxelec.cpug.kafka;

import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

//	@KafkaListener(topics = "topic1")
//	public void listenT1(ConsumerRecord<?, ?> cr) throws Exception {
//		System.out.println("listenT1收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  "
//				+ cr.value());
//	}
//
//	@KafkaListener(topics = "topic2",groupId = "top2")
//	public void listenT2(ConsumerRecord<?, ?> cr) throws Exception {
//		System.out.println("listenT2收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  "
//				+ cr.value());
//	}
//	
//	@KafkaListener(topics = "topic2" , groupId = "top3")
//	public void listenT3(ConsumerRecord<?, ?> cr) throws Exception {
//		System.out.println("listenT3收到消息！！   topic:>>>  " + cr.topic() + "    key:>>  " + cr.key() + "    value:>>  "
//				+ cr.value()+"\t "+cr.partition());
//	}
}
