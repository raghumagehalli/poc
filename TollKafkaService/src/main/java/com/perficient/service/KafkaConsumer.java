package com.perficient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.perficient.model.TollData;
import com.perficient.repository.TollDataRepository;

@Service
public class KafkaConsumer {

	@Autowired
	TollDataRepository dataRepository;

	@KafkaListener(topics = "toll_data_topic", groupId = "${spring.kafka.consumer.group-id}")
	private void consumeFromKafka(TollData tollData) {

		System.out.println("Response:" + tollData.getTollId());

		dataRepository.save(tollData);
		dataRepository.flush();

	}

}
