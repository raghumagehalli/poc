package com.perficient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genere_movie")
public class GenereMaping {

	@Id 
	@Column
	private Long genereId;
	@Column
	private Long movieId;

	public GenereMaping() {

	}

	public GenereMaping(Long genereId, Long movieId) {
		this.genereId = genereId;
		this.movieId = movieId;
	}

	public Long getGenereId() {
		return genereId;
	}

	public void setGenereId(Long genereId) {
		this.genereId = genereId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

}
