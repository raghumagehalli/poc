package com.perficient.filmhouse.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.perficient.filmhouse.exception.MovieNotFoundException;

@ControllerAdvice
public class FilmHouseAdvice {
	@ResponseBody
	@ExceptionHandler(MovieNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	String movieNotFoundHandler(MovieNotFoundException ex)

	{
		return ex.getMessage();
	}

}
