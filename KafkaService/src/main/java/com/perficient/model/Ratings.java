package com.perficient.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Ratings {
	@EmbeddedId
	private RatingsIdentity ratingsIdentity;

	@Column
	private String rating;

	@Column
	private String movieName;

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public RatingsIdentity getRatingsIdentity() {
		return ratingsIdentity;
	}

	public void setRatingsIdentity(RatingsIdentity ratingsIdentity) {
		this.ratingsIdentity = ratingsIdentity;
	}

}
