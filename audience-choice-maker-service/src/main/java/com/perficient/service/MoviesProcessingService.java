package com.perficient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.perficient.dto.Movie;
import com.perficient.dto.Movies;
import com.perficient.model.Genere;
import com.perficient.model.GenereMaping;
import com.perficient.repository.GenereMapingRepository;
import com.perficient.repository.GenereRepository;

@Service
public class MoviesProcessingService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	GenereRepository genereRepository;
	@Autowired
	GenereMapingRepository genereMapingRepository;

	@Scheduled(fixedDelay = 60000)
	public void fetchAndProcessMovie()

	{

		Movies movies = restTemplate.getForObject("http://FILM-HOUSE-SERVICE/filmhouse/movies", Movies.class);
		if(movies != null)
		{
			movies.getMovies().stream().forEach(movie -> {
				Genere genere = genereRepository.findByName(movie.getGenere());
				if (genere == null)
					genere = genereRepository.save(new Genere(movie.getGenere()));
				genereMapingRepository.save(new GenereMaping(genere.getGenereId(), movie.getMovieId()));
			});
			System.out.println("Response processed");
		}
		else
		{
			System.out.println("Response is emty");
		}
	
		
        
	}
}
