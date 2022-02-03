package com.perficient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perficient.model.TollData;

public interface TollDataRepository extends JpaRepository<TollData, Long> {

    public TollData findByRegistraionNumber(String registraionNumber);

}
