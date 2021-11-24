package com.perficient.filmhouse.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

@Entity
public class Movie {
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
    
	@Column(nullable = false, updatable = false)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private Long movieId;
	@Column
	@Schema(example = "Iron Man", required = true)
	private String name;
	@Column
	@Schema(example = "Tony Stark", required = true)
	private String leadActor;

	@Column
	@Schema(example = "Gwyneth Paltrow", required = true)
	private String leadActress;

	@Column
	@Schema(example = "Jon Favreau", required = true)
	private String director;
	@Column
	@Schema(example = " Marvel Studios", required = true)
	private String productionCompany;
	@Column
	@Schema(example = "2008", required = true)
	private String yearOfRelease;
	
	@Column(updatable = false)
	@CreationTimestamp
	@Schema(accessMode = AccessMode.READ_ONLY)
	private Timestamp createdDate;
	
	@Column
	@Schema(example = "Action/Adventure", required = true)
	private String genere;

	public Movie() {
	}
	
	public String getGenere() {
		return genere;
	}



	public void setGenere(String genere) {
		this.genere = genere;
	}


	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProductionCompany() {
		return productionCompany;
	}

	public void setProductionCompany(String productionCompany) {
		this.productionCompany = productionCompany;
	}

	public String getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(String yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getLeadActor() {
		return leadActor;
	}

	public void setLeadActor(String leadActor) {
		this.leadActor = leadActor;
	}

	public String getLeadActress() {
		return leadActress;
	}

	public void setLeadActress(String leadActress) {
		this.leadActress = leadActress;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", name=" + name + ", leadActor=" + leadActor + ", leadActress="
				+ leadActress + ", director=" + director + ", productionCompany=" + productionCompany
				+ ", yearOfRelease=" + yearOfRelease + ", createdDate=" + createdDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdDate, director, leadActor, leadActress, movieId, name, productionCompany,
				yearOfRelease);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(createdDate, other.createdDate) && Objects.equals(director, other.director)
				&& Objects.equals(leadActor, other.leadActor) && Objects.equals(leadActress, other.leadActress)
				&& Objects.equals(movieId, other.movieId) && Objects.equals(name, other.name)
				&& Objects.equals(productionCompany, other.productionCompany)
				&& Objects.equals(yearOfRelease, other.yearOfRelease);
	}

}