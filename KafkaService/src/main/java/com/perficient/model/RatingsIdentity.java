package com.perficient.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class RatingsIdentity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Column(nullable = false, updatable = false)
	private Long ratingId;
	@Column(nullable = false, updatable = false)
	private Long movieId;

	public RatingsIdentity(Long ratingId, Long movieId) {
		this.ratingId = ratingId;
		this.movieId = movieId;
	}

	public RatingsIdentity() {
		// TODO Auto-generated constructor stub
	}

	public Long getRatingId() {
		return ratingId;
	}

	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

}
