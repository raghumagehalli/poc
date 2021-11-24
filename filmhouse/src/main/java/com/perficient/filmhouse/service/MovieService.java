package com.perficient.filmhouse.service;

import java.util.List;

import com.perficient.filmhouse.dto.Movies;
import com.perficient.filmhouse.model.Movie;

public interface MovieService {

	public Movies getAllMovies();

	public Movie getMovie(Long movieId);

	public Movie getMovie(String name);

	public Movie addMovie(Movie movie);

	public Movie updateMovie(Movie movie);

	public void deleteMovie(Long movieId);

}
