package com.perficient.filmhouse.exception;

public class MovieNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MovieNotFoundException(String value) {
		super("Could not find the movie " + value);
	}
}
