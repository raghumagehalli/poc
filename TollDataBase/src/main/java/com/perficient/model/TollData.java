package com.perficient.model;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class TollData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(name = "vehicleType")
	private String vehicleType;
	@Column(name="tollPrice")
	private int tollPrice;
	@Column(name="registartionNumber")
	private String registraionNumber;
	@Column(name="tollId")
	private String tollId;
	@Column(name="enteredTime")
	@CreationTimestamp
	private Timestamp enteredTime;

	public TollData() {
		super();
	}

	public TollData(String vehicleType, int tollPrice, String registraionNumber, String tollId) {
		super();
		this.vehicleType = vehicleType;
		this.tollPrice = tollPrice;	
		this.registraionNumber = registraionNumber;
		this.tollId = tollId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getTollPrice() {
		return tollPrice;
	}

	public void setTollPrice(int tollPrice) {
		this.tollPrice = tollPrice;
	}

	public String getRegistraionNumber() {
		return registraionNumber;
	}

	public void setRegistraionNumber(String registraionNumber) {
		this.registraionNumber = registraionNumber;
	}

	public String getTollId() {
		return tollId;
	}

	public void setTollId(String tollId) {
		this.tollId = tollId;
	}

	public Timestamp getEnteredTime() {
		return enteredTime;
	}

	public void setEnteredTime(Timestamp enteredTime) {
		this.enteredTime = enteredTime;
	}

}
