package com.zxelec.cpug.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
/**
 * kafka 生产者
 * @author liu.yongquan
 *
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {
	@Autowired
	private KafkaProperties kafkaProperties;
	
	
	/**
	 * 生产者
	 * @return
	 */
	public Map<String, Object> producerConfigs() {
		Map<String,Object> props = kafkaProperties.buildProducerProperties();
        return props;
    }
	
	/**
	 * 生产者
	 * @return
	 */
    @Bean
    public ProducerFactory<Object, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    /**
     * 生产者
     * @return
     */
	@Bean
	public KafkaTemplate<Object,Object> kafkaTemplate() {
		KafkaTemplate<Object,Object> ka = new KafkaTemplate<>(producerFactory());
		return ka;
	}
}
