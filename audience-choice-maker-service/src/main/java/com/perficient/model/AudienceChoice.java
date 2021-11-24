package com.perficient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity
@Table(name = "audience_choice")
public class AudienceChoice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private Long id;
	@Column
	@Transient
	@Schema(example = "Comedy", description = "The Movie Genere")
	private String genere;
	@JsonIgnore
	private int likes;
	@JsonIgnore
	private int dislikes;
	@Column
	@Transient
	@Schema(example = "Yes/No", description = "Your Openion on genere")
	private String doYouLike;
	@JsonIgnore
	@Column
	private Long genereId;

	public Long getGenereId() {
		return genereId;
	}

	public void setGenereId(Long genereId) {
		this.genereId = genereId;
	}

	public AudienceChoice() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getDoYouLike() {
		return doYouLike;
	}

	public void setDoYouLike(String doYouLike) {
		this.doYouLike = doYouLike;
	}

}
