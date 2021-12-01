package com.perficient.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.perficient.dao.RatingDAO;
import com.perficient.model.Movie;
import com.perficient.model.Ratings;
import com.perficient.model.RatingsIdentity;

@Service
public class KafkaConsumerService {
	@Autowired
	RatingDAO dao;

	@KafkaListener(topics = "my_demo_topic", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "jsonListenerContainerFactory")
	private void consumeFromKafka(ConsumerRecord<String, Movie> rec, @Payload Movie movie) {

		System.out.println("Response: Key" + rec.key() + "," + movie.getName());

		Ratings rating = new Ratings();
		RatingsIdentity identity = new RatingsIdentity(movie.getMovieId(),movie.getMovieId());
		identity.setMovieId(movie.getMovieId());
		rating.setRatingsIdentity(identity);

		rating.setMovieName(movie.getName());
		rating.setRating("5");
		dao.save(rating);
		
		System.out.println("saved to db");

	}

}
