package com.perficient.filmhouse.dto;

import java.util.List;

import com.perficient.filmhouse.model.Movie;

public class Movies {

	private List<Movie> movies;

	public Movies() {

	}
	
	public Movies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
