package com.perficient.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.util.QueryExecutionConverters.ExecutionAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.perficient.dto.Movie;
import com.perficient.dto.Movies;
import com.perficient.model.AudienceChoice;
import com.perficient.model.Genere;
import com.perficient.model.GenereMaping;
import com.perficient.repository.GenereMapingRepository;
import com.perficient.repository.GenereRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/genere")
@Tag(name = "Genere Creator")
public class GenereController {

	@Autowired
	GenereRepository repository;
	@Autowired
	GenereMapingRepository genereMapingRepository;
	@Autowired
	RestTemplate restTemplate;

	@PostMapping(consumes = { "application/json" }, produces = { "application/json" })
	@Operation(description = "Add Genere", summary = "Create New Genere")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	public ResponseEntity<String> addGenere(@Parameter(description = "Genere") @RequestBody Genere genere) {

		repository.save(genere);

		return new ResponseEntity<>("New genere added", HttpStatus.OK);

	}

	@HystrixCommand(fallbackMethod = "getMoviesFallBack")
	@GetMapping(value = "/{genereName}", produces = { "application/json" })
	@Operation(description = "Get Movies", summary = "Get Movies By Genere")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	public ResponseEntity<List<Movie>> getMovies(@Parameter(description = "Genere") @PathVariable String genereName) {

		Genere genere = repository.findByName(genereName);
		List<GenereMaping> genereMappingList = genereMapingRepository.findAll();
		List<Long> movieIds = genereMappingList.stream()
				.filter(genereMaping -> genereMaping.getGenereId() == genere.getGenereId())
				.map(genereMaping -> genereMaping.getMovieId()).collect(Collectors.toList());
		List<Movie> moviesList = null;
		for (Long movieId : movieIds) {

			Movie movie = restTemplate.getForObject("http://FILM-HOUSE-SERVICE/filmhouse/movie/" + movieId, Movie.class);
			moviesList = Collections.singletonList(movie);
		}

		return ResponseEntity.ok().body(moviesList);

	}

	public ResponseEntity<List<Movie>> getMoviesFallBack(
			@Parameter(description = "Genere") @PathVariable String genereName) {
		Movie movie = new Movie();
		List<Movie> movieList = new ArrayList<>();
		movieList.add(movie);
		return ResponseEntity.ok().body(movieList);

	}
}
