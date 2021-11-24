package com.perficient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity
@Table(name = "gener")
public class Genere {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private Long genereId;
	@Column()
	@Schema(example = "Comedy", description = "The Movie Genere")
	private String name;

	public Genere() {

	}
	
	public Genere(String name) {
		this.name = name;
	}

	public Long getGenereId() {
		return genereId;
	}

	public void setGenereId(Long genereId) {
		this.genereId = genereId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
