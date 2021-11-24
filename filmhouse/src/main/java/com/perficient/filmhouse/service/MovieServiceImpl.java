package com.perficient.filmhouse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perficient.filmhouse.dao.MovieDAO;
import com.perficient.filmhouse.dto.Movies;
import com.perficient.filmhouse.exception.MovieNotFoundException;
import com.perficient.filmhouse.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	MovieDAO movieDao;
	List<Movie> movies = null;

	@Override
	public Movies getAllMovies() {
		movies = new ArrayList<Movie>();
		movieDao.findAll().forEach(movies::add);

		return new Movies(movies);
	}

	@Override
	public Movie getMovie(Long movieId) {

		return movieDao.findById(movieId).orElseThrow(() -> new MovieNotFoundException(String.valueOf(movieId)));
	}

	@Override
	public Movie getMovie(String name) {

		return Optional.ofNullable(movieDao.findByName(name))
				.orElseThrow(() -> new MovieNotFoundException(String.valueOf(name)));

	}

	@Override
	public Movie addMovie(Movie movie) {

		return movieDao.save(movie);
	}

	@Override
	public Movie updateMovie(Movie movie) {
		return movieDao.save(movie);

	}

	@Override
	public void deleteMovie(Long movieId) {

		movieDao.deleteById(movieId);

	}

}
