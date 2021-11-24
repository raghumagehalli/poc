package com.perficient.filmhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.filmhouse.dto.Movies;
import com.perficient.filmhouse.model.Movie;
import com.perficient.filmhouse.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("filmhouse")
@Tag(name = "filmhouse")
public class FilmHouseController {

	@Autowired
	MovieService movieService;

	@Operation(description = "List All Movies", summary = "Get All Movies Available in the inventory")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listed all movies"),
			@ApiResponse(responseCode = "404", description = "resource not found") })
	@GetMapping(value = "/movies", produces = { "application/json" })
	Movies getAllMovies() {
		return movieService.getAllMovies();
	}

	@Operation(description = "List Movie By Id", summary = "Get the movie details by Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Search Successful"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	@GetMapping(value = "/movie/{id}", produces = "application/json")
	Movie getMovie(@Parameter(description = "Movie id") @PathVariable(required = true) Long id) {
		return movieService.getMovie(id);
	}

	@Operation(description = "List Movie By Name", summary = "Get the movie details by Name")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Search Successful"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	@GetMapping(value = "/movie", produces = { "application/json" })
	Movie getMovie(@Parameter(description = "Movie name") @RequestParam(value = "name", required = true) String name) {
		return movieService.getMovie(name);
	}

	@Operation(description = "Add New Movie ", summary = "Add new movie details to inventory")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Adding movie is successful "),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	@PostMapping(value = "/movie", consumes = { "application/json" }, produces = { "application/json" })
	Movie addMovie(@Parameter(description = "Movie to add") @RequestBody Movie movie) {
		return movieService.addMovie(movie);
	}

	@Operation(description = "Update Movie", summary = "Update movie details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update is successful "),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	@PutMapping(value = "/movie", consumes = { "application/json" }, produces = { "application/json" })
	Movie updateMovie(@Parameter(description = "Movie to update") @RequestBody Movie movie) {
		return movieService.addMovie(movie);
	}

	@Operation(description = "Delete Movie", summary = "Remove the movie from inventory")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully removed the movie"),
			@ApiResponse(responseCode = "400", description = "invalid input, object invalid") })
	@DeleteMapping("/movie/{id}")
	void deleteMovie(@Parameter(description = "Movie id") @PathVariable(required = true) Long id) {
		movieService.deleteMovie(id);
	}

}
