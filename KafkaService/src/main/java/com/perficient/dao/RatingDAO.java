package com.perficient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perficient.model.Ratings;
import com.perficient.model.RatingsIdentity;

@Repository
public interface RatingDAO extends JpaRepository<Ratings, RatingsIdentity> {

	
}
