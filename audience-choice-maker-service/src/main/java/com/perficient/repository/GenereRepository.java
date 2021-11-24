package com.perficient.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.perficient.model.Genere;

public interface GenereRepository extends JpaRepository<Genere, Long> {

	public Genere findByName(String name);
}
