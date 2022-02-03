package com.perficient.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.perficient.model.TollData;
import com.perficient.repository.TollDataRepository;

@Service
public class TollDataService {

	@Autowired
	KafkaTemplate<String, Object> template;

	@Autowired
	private TollDataRepository dataRepository;

	public List<TollData> getTollData() {
		return dataRepository.findAll();

	}

	public List<TollData> addTollData(List<TollData> tollDetails) {
		// return dataRepository.saveAll(tollDetails);
		IntStream.range(0, tollDetails.size()).forEach(index -> ProduceToKafka(tollDetails.get(index)));
		return tollDetails;

	}

	private void ProduceToKafka(TollData tollData) {

		ListenableFuture<SendResult<String, Object>> future = template.send("toll_data_topic", tollData.getTollId(),
				tollData);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				System.out.println("Success" + tollData.getTollId());
			}

			@Override
			public void onFailure(Throwable ex) {

				System.out.println("Unable to send message");
			}

		});

	}

}
