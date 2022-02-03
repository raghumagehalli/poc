package com.perficient;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@SpringBootApplication
public class TollDataBaseApplication {

	@Autowired
	KafkaProperties kafkaProperties;

	public static void main(String[] args) {
		SpringApplication.run(TollDataBaseApplication.class, args);
	}

	@Bean
	
	ProducerFactory<String, Object> jsonProducerfactory() {
		Map<String, Object> props = kafkaProperties.buildProducerProperties();
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	KafkaTemplate<String, Object> jsonKafkaTemplate() {
		return new KafkaTemplate<>(jsonProducerfactory());
	}

}
