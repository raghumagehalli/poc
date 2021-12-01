package com.perficient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perficient.model.Movie;

@Repository
public interface MovieDAO extends JpaRepository<Movie, Long> {

	public Movie findByName(String name);

}
