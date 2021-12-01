package com.perficient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.perficient.dao.MovieDAO;
import com.perficient.model.Movie;

@Service
public class KafkaProducerService {

	@Autowired
	private MovieDAO dao;
	private String topicName = "my_demo_topic";
	@Autowired
	private KafkaTemplate<String, Object> template;

	@Scheduled(fixedDelay = 60000)
	private void produceToKafka() {
		List<Movie> movies = getMovies();

		for (Movie movie : movies) {
			ListenableFuture<SendResult<String, Object>> future = template.send(topicName,
					String.valueOf(movie.getMovieId()), movie);
			future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

				@Override
				public void onFailure(Throwable ex) {
					System.out.println("Unable to send message");

				}

				@Override
				public void onSuccess(SendResult<String, Object> result) {
					System.out.println("Success" + movie.getMovieId() + "," + result.getRecordMetadata().offset() + ","
							+ result.getRecordMetadata().partition());

				}
			});
		}

	}

	private List<Movie> getMovies() {

		return dao.findAll();
	}

}
