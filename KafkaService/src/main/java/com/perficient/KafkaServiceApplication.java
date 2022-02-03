package com.perficient;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableEurekaClient
public class KafkaServiceApplication {

	@Autowired
	KafkaProperties kafkaProperties;
	

	public static void main(String[] args) {
		SpringApplication.run(KafkaServiceApplication.class, args);
		
	}

	@Bean
	ProducerFactory<String, String> genericProducerFactory() {

		return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());

	}

	@Bean
	KafkaTemplate<String, String> generickKafkaTemplate() {
		return new KafkaTemplate<>(genericProducerFactory());

	}

	@Bean
	ProducerFactory<String, Object> jsonProducerFactory()

	{
		Map<String, Object> props = kafkaProperties.buildProducerProperties();
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");

		return new DefaultKafkaProducerFactory<>(props);

	}

	@Bean
	KafkaTemplate<String, Object> jsonKafkaTemplate() {
		return new KafkaTemplate<>(jsonProducerFactory());

	}

	@Bean
	ConsumerFactory<String, String> genericConsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());

	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> genericListenerContainerFactory()

	{
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();

		factory.setConsumerFactory(genericConsumerFactory());

		return factory;

	}

	@Bean
	ConsumerFactory<String, Object> jsonConsumerFactory() {
		
		 final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
	        jsonDeserializer.addTrustedPackages("*");

		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), new StringDeserializer(),
				jsonDeserializer);

	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, Object> jsonListenerContainerFactory()

	{
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<String, Object>();

		factory.setConsumerFactory(jsonConsumerFactory());

		return factory;

	}

}
